package com.master4.converter;

import com.master4.entities.Article;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ArticleConverter implements Converter<List<String>, Object> {
    @Override
    public List<Article> convert(List<String> source) {
        List<Article> listArticle=new ArrayList<>();
        if(source.isEmpty()) {
            return null;
        }else{
            source.forEach(a->listArticle.add(new Article(Long.parseLong(a))));
            return listArticle;
        }
    }
}
