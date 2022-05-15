package com.kchn.blog.api.Article;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller layer for articles. It should handle only receiving and giving replies to outside requests.
 * @author kevin
 *
 */

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {
    
    @Autowired
    public ArticleService service;

    /**
     * Gets all articles in the database
     * 
     * @return list of articles
     */
    @GetMapping
    public ResponseEntity<List<Article>> get() {
        return new ResponseEntity<List<Article>>(this.service.getAll(), HttpStatus.OK);
    }
    
    /**
     * Gets article by url.
     * 
     * @param url
     * @return Only article content and no behind the scenes details.
     */
    @GetMapping(value = "/url/{url}")
    public ResponseEntity<Article> getByUrl(@PathVariable String url) {
        return new ResponseEntity<Article>(this.service.getByUrl(url), HttpStatus.OK);
    }
    
    /**
     * Creates an article.
     * 
     * @return
     */
    @PostMapping
    public ResponseEntity<Article> create(@Valid @RequestBody ArticlePostDTO incomingBody) {
        return new ResponseEntity<Article>(this.service.create(incomingBody), HttpStatus.OK);
    }
    
    /**
     * Updates existing article
     * 
     * Navigate to /articles/{article_id}
     * And in the patch request, include any fields to be updated in the body.
     * @param id
     * @param incomingBody
     * @return updated article.
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticlePatchDTO incomingBody){
        return new ResponseEntity<Article>(this.service.update(id, incomingBody), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
//    @GetMapping(value = "/test/{title}")
//    public String test(@PathVariable String title ) {
//        return this.service.generateUniqueUrl(title);
//    }
}
