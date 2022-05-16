package com.kchn.blog.api.Article;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This interface creates a jparepository. It also adds custom queris with query annotations below.
 * 
 * We are also adding custom utilities and methods by extending an ArticleRepositoryUtils interface which is implemented by the ArticleRepositoryUtilsImpl class.
 * 
 * NOTE: that if you want to do this, the most important part of the class name that corresponds to the fragment interface is the Impl postfix.
 * 
 * @author kevin
 *
 */

public interface ArticleRepositoryInterface extends JpaRepository<Article, Long>, ArticleRepositoryUtils{

    @Query(value = "SELECT a FROM Article a WHERE a.url = ?1")
    Optional<Article> findArticleByUrl(String url);
    
}
