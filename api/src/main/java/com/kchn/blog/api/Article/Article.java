package com.kchn.blog.api.Article;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * This Article class describes both the schema of the database we use and the object we pass around called article.
 * @author kevin
 *
 */
@Entity
public class Article {

    /**
     * ID to be used by the backend and the database.
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String byline;
    
    /**
     * Long string of markdown content.
     */
    @Lob
    @Column(name = "content", columnDefinition = "LONGTEXT NOT NULL")
    private String content;
    
    /**
     * I intend to make each URL unique and serve as a way to fetch individual articles.
     * [backend-url]/api/articles/this-url for example would be fetched from this frontend url: 
     * blog.[frontend-url]/this-url
     */
    private String url;
    
    private String kicker;
    
    private String metaTitle;
    
    private String metaDesc;
    
    private LocalDate published;
    
    private LocalDate lastEdit;
    
    private String author;

    /**
     * Create empty article
     */
    public Article() {
    }
    
    
    /**
     * Create article with only mandatory Post fields.
     * @param title
     * @param content
     * @param author
     */
    public Article(String title, String content, String author) {
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
    public Article(Long id, String title, String byline, String content,
        String url, String kicker, String metaTitle, String metaDesc, LocalDate published, LocalDate lastEdit) {
        this.id = id;
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

    public Long getId() {
        return id;
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


    public void setId(Long id) {
        this.id = id;
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
