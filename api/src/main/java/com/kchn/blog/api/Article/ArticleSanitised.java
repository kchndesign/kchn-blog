package com.kchn.blog.api.Article;

import java.time.LocalDate;

/**
 * This class describes the sanitised article that will be sent to users who search by
 * @author kevin
 *
 */

public class ArticleSanitised {

    private String title;
    
    private String byline;
    
    private String content;
    
    private String url;
    
    private String kicker;
    
    private String metaTitle;
    
    private String metaDesc;
    
    private LocalDate published;
    
    private LocalDate lastEdit;
    
    private String author;

    public ArticleSanitised() {
    }
    
    
    /**
     * Create article with only mandatory fields.
     * @param title
     * @param content
     * @param author
     */
    public ArticleSanitised(String title, String content, String author) {
        super();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * Create Full Article.
     * @param id
     * @param title
     * @param byline
     * @param content
     * @param url
     * @param metaTitle
     * @param metaDesc
     * @param published
     * @param lastEdit
     */
    public ArticleSanitised( String title, String byline, String content,
        String url, String kicker, String metaTitle, String metaDesc, LocalDate published, LocalDate lastEdit) {
        this.title = title;
        this.byline = byline;
        this.content = content;
        this.url = url;
        this.kicker = kicker;
        this.metaTitle = metaTitle;
        this.metaDesc = metaDesc;
        this.published = published;
        this.lastEdit = lastEdit;
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

    public String getUrl() {
        return url;
    }

    public String getKicker() {
        return kicker;
    }


    public void setKicker(String kicker) {
        this.kicker = kicker;
    }


    public String getMetaTitle() {
        return metaTitle;
    }

    public String getMetaDesc() {
        return metaDesc;
    }
    
    public LocalDate getPublished() {
        return published;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }
    
    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

        
}
