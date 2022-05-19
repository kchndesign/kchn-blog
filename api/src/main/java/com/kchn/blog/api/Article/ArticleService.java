package com.kchn.blog.api.Article;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
     * 
     * @return List<Article>
     */
    public List<Article> getAll() {
        return this.repository.findAll();
    }
    
    /**
     * Gets an article by an id.
     * 
     * @param id
     * @return Article
     */
    public Article getById(Long id) {
        
        Optional<Article> maybeArticle = 
            this.repository.findById(id);
        
        maybeArticle.ifPresentOrElse(value -> {}, () -> {
            throw new CustomApiException(
                HttpStatus.NOT_FOUND,
                "Specified record " + id + " not found");
        });
        
        return maybeArticle.get();
    }
    
    /**
     * Gets an article by a url string. 
     * 
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
        
        Optional<Article> maybeArticle = 
            this.repository.findArticleByUrl(url);
        
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
     * 
     * @param title will be processed into a url
     * @return url as string
     */
    private String generateUniqueUrl(String title) {
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
        
        String url = shortTitle.replaceAll(" ", "-").toLowerCase();
        
        Optional<Article> maybeArticle = 
            this.repository.findArticleByUrl(url);
        
//        While the url exists in the repository, generate random string to concat onto the end and re-search.
        while (!maybeArticle.isEmpty()) {
            String random = RandomString.make(5);
            url = shortTitle.concat("-" + random);
            maybeArticle = this.repository.findArticleByUrl(url);
        }
        
        return url;
    }
    
    /**
     * Generate meta description
     * 
     * Takes body input and filters out titles 
     * 
     * @param body
     * @return
     */
    private Optional<String> generateMetaDesc(String body) {
        /*
         * Get an array of all match groups.
         * For each array item, trim of whitespace and see if it still contains text.
         * Save the first one that isn't empty after trimming
         * 
         * Limit that string length to 100 characters.
         * Return.
         */
        
        String regex = "^[^#]+$";
        String firstParagraph = null;
        Matcher matcher = Pattern
            .compile(regex, Pattern.MULTILINE)
            .matcher(body);
        while(matcher.find()) {
            String currentGroup = matcher.group().strip();
            if (currentGroup.length() > 0) {
                firstParagraph = currentGroup;
                break;
            }
        }
        
        if (firstParagraph == null ) {
            return Optional.ofNullable(null);
        }
        
        return Optional.of(firstParagraph.substring(0, 100));
    }
    
    /**
     * Creates a new article based on the incoming request body.
     * 
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
         *      if no meta desc: generate
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
        String bodyKicker = incomingBody.getKicker();
        
        if (bodyByline != null && bodyByline.length() > 0) {
            article.setByline(bodyByline);
        }
        
        if (bodyMetaTitle != null && bodyMetaTitle.length() > 0) {
            article.setMetaTitle(bodyMetaTitle);
        }
        
//        generate meta description to be used by 
        if (bodyMetaDesc != null && bodyMetaDesc.length() > 0 ) {
            article.setMetaDesc(bodyMetaDesc);
        } else {
            this.generateMetaDesc(article.getContent())
                .ifPresent(value -> article.setMetaDesc(value));
        }
        
        if (bodyKicker != null && bodyKicker.length() > 0) {
            article.setKicker(bodyKicker);
        }
        
//        Set date
        article.setPublished(LocalDate.now());
        
//        Set url
        article.setUrl(this.generateUniqueUrl(article.getTitle()));
        
        return this.repository.save(article);
    }
    
    /**
     * Update article by specifying the article id and fields in the request body.
     * 
     * @param id
     * @param incomingBody
     * @return updated article
     */
    public Article update(Long id, ArticlePatchDTO incomingBody) {
        
        /*
         * Every field is optional. 
         * I don't think there's anything wrong with an empty patch request?
         * 
         * Create new empty article.
         * Throw if article is not found.
         * Else for each possible field,
         *      If field is valid, add to article.
         *      if not do nothing.
         *      
         * Lastly set the last edit to the current time.
         */
        
        Optional<Article> maybeArticle = 
            this.repository.findById(id);
        
        maybeArticle.ifPresentOrElse(value -> {}, () -> {
            throw new CustomApiException(
                HttpStatus.NOT_FOUND,
                "Specified record " + id + " not found");
        });
        
        Article article = maybeArticle.get();
        
        String bodyTitle = incomingBody.getTitle();
        String bodyByline = incomingBody.getByline();
        String bodyKicker = incomingBody.getKicker();
        String bodyMetaTitle = incomingBody.getMetaTitle();
        String bodyMetaDesc = incomingBody.getMetaDesc();
        String bodyAuthor = incomingBody.getAuthor();
        
        if (bodyTitle != null && bodyTitle.length() > 0) {
            article.setTitle(bodyTitle);
        }
        
        if (bodyByline != null && bodyByline.length() > 0) {
            article.setByline(bodyByline);
        }
        
        if(bodyKicker != null && bodyKicker.length() > 0) {
            article.setKicker(bodyKicker);
        }
        
        if(bodyMetaTitle != null && bodyMetaTitle.length() > 0) {
            article.setMetaTitle(bodyMetaTitle);
        }
        
        if(bodyMetaDesc != null && bodyMetaDesc.length() > 0) {
            article.setMetaDesc(bodyMetaDesc);
        }
        
        if(bodyAuthor != null && bodyAuthor.length() > 0) {
            article.setAuthor(bodyAuthor);
        }
        
        article.setLastEdit(LocalDate.now());
        
        return repository.save(article);
    }
    
    /**
     * Checks if article exists then deletes it.
     * 
     * @param id
     */
    public void delete(Long id) {
        
        /*
         * Delete article
         * 
         * See if article exists.
         * If not throw,
         * 
         * If yes, delete.
         */
        
        if (this.repository.findById(id).isEmpty()) {
            throw new CustomApiException(
                HttpStatus.NOT_FOUND,
                "Specified record " + id + " not found");
        }
        
        this.repository.deleteById(id);
    }
    
}
