package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    //Des methodes de recherche par attributs ( IgnoreCase pour ignorer la majuscule yaani k bch ylawej k ndakahlo para
    // miniscule wella majuscule o yalkah f DB peut n'importe el type mteoo bch yrajaaholi
    // Article findByCodeArticleIgnoreCase(String codeArticle);

        Optional<Article> findArticleByCodeArticle(String codeArticle);

    //CustomQuary
  //  @Query("select a from article where codearticle = :code and designation = :designation")
   // List<Article> findByCustomQuery(@Param("code") String c, @Param("designation") String s);
}
