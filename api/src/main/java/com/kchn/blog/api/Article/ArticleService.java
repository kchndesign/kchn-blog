package com.kchn.blog.api.Article;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.apache.catalina.startup.ListenerCreateRule.OptionalListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kchn.blog.api.Exceptions.CustomApiException;

import net.bytebuddy.utility.RandomString;

/**
 * This is the service layer for articles. It handles business logic like the processing of requests. 
 * @author kevin
 *
 */

@Service
public class ArticleService {
    
    @Autowired
    public ArticleRepositoryInterface repository;

    /**
     * Gets All articles in the database
     * @return List<Article>
     */
    public List<Article> getAll() {
        return this.repository.findAll();
    }
    
    /**
     * Gets an article by a url string. 
     * @param url
     * @return Article
     * @throws CustomException 404 if not found.
     */
    public Article getByUrl(String url) {
        
        /*
         * Use custom findArticleByUrl from repository.
         * If article is not found,
         * Build custom exception and throw it.
         * If article is found, return it
         */
        
        Optional<Article> maybeArticle = Optional.ofNullable(this.repository.findArticleByUrl(url));
        
        if (maybeArticle.isEmpty()) {
            throw new CustomApiException(
                HttpStatus.NOT_FOUND, 
                "Could not find article with this url", 
                url);
        }
        return maybeArticle.get();
    }
    
    /**
     * Generates a url that is guaranteed to be unique in the database.
     * @param title will be processed into a url
     * @return url as string
     */
    public String generateUniqueUrl(String title) {
        /*
         * Limit title to 6ish words
         * Replace spaces with dash
         * Search repository for such string
         * If the string exists, add a random string and search again
         */
        
        Pattern titlePattern = Pattern.compile("^\\w+ ?\\w* ?\\w* ?\\w* ?\\w* ?\\w*");
        Matcher titleMatcher = titlePattern.matcher(title);
        titleMatcher.find();
        String shortTitle = titleMatcher.group(0);
        
        String url = shortTitle.replaceAll(" ", "-");
        
        Optional<Article> maybeArticle = Optional.ofNullable(this.repository.findArticleByUrl(url));
        
//        While the url exists in the repository, generate random string to concat onto the end and re-search.
        while (!maybeArticle.isEmpty()) {
            String random = RandomString.make(5);
            url = shortTitle.concat("-" + random);
            maybeArticle = Optional.ofNullable(this.repository.findArticleByUrl(url));
        }
        
        return url;
    }
    
    /**
     * Creates a new article based on the incoming request body.
     * @param incomingBody 
     * @return Article
     */
    public Article create(ArticlePostDTO incomingBody) {
        /*
         * Create new article with mandatory body fields.
         * Check each optional field for null or invalid (empty)
         * Populate non-input fields:
         *      published date: date now;
         *      url: use private util.
         */
        
//        Set mandatory
        Article article = new Article(
            incomingBody.getTitle(), 
            incomingBody.getContent(), 
            incomingBody.getAuthor());
        
//        Set optional fields
        String bodyByline = incomingBody.getByline();
        String bodyMetaTitle = incomingBody.getMetaTitle();
        String bodyMetaDesc = incomingBody.getMetaDesc();
        
        if (bodyByline != null && bodyByline.length() > 0) {
            article.setByline(bodyByline);
        }
        
        if (bodyMetaTitle != null && bodyMetaTitle.length() > 0) {
            article.setMetaTitle(bodyMetaTitle);
        }
        
        if (bodyMetaDesc != null && bodyMetaDesc.length() > 0 ) {
            article.setMetaDesc(bodyMetaDesc);
        }
        
//        Set date
        article.setPublished(LocalDate.now());
        
//        Set url
        article.setUrl(this.generateUniqueUrl(article.getTitle()));
        
        return this.repository.save(article);
    }
    
}
