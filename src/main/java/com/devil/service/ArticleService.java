package com.devil.service;

import java.util.List;
import com.devil.domain.Article;

public interface ArticleService {
  int add(Article article) throws Exception;
  List<Article> list (String keyword) throws Exception;
  Article get(int no) throws Exception;
  int update(Article article) throws Exception;
  int delete(int no) throws Exception;
}
