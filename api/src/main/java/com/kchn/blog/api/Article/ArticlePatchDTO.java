package com.kchn.blog.api.Article;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotEmpty;

/**
 * This class describes the body of incoming Patch requests used to update articles.
 * This contains all of the user specify-able fields for updated articles
 * @author kevin
 *
 */

public class ArticlePatchDTO {

    private String title;
    
    private String byline;

    private String content;
    
    private String kicker;
    
    private String metaTitle;
    
    private String metaDesc;
    
    private String author;
    
    public ArticlePatchDTO() {
        super();
    }

    public ArticlePatchDTO(String title, String byline, String content,
        String kicker, String metaTitle, String metaDesc, String author) {
        super();
        this.title = title;
        this.byline = byline;
        this.content = content;
        this.kicker = kicker;
        this.metaTitle = metaTitle;
        this.metaDesc = metaDesc;
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getContent() {
        return content;
    }

    public String getKicker() {
        return kicker;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}
