package com.devil.web.admin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Article;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;
import com.devil.service.CommentService;
import com.devil.service.TagService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  ArticleService articleService;
  @Autowired
  TagService tagService;
  @Autowired
  BookmarkService bookmarkService;
  @Autowired
  UserService userService;
  @Autowired
  CommentService commentService;

  @RequestMapping("list")
  public ModelAndView list(String keyword, String keywordTitle, String keywordWriter,
      String keywordTag, Integer tagNo) throws Exception {

    ModelAndView mv = new ModelAndView();

    if (keyword != null) {
      mv.addObject("articles", articleService.list(keyword));

    } else if (keywordTitle != null) {
      HashMap<String, Object> keywordMap = new HashMap<>();
      keywordMap.put("title", keywordTitle);
      keywordMap.put("writer", keywordWriter);
      keywordMap.put("tag", keywordTag);

      mv.addObject("articles", articleService.list(keywordMap));

    } else if (tagNo != null) {
      mv.addObject("tag", tagService.get(tagNo));
      mv.addObject("articles", articleService.listByTagNo(tagNo));
    } else {
      mv.addObject("articles", articleService.list());
    }

    mv.setViewName("/adminJsp/article/list.jsp");
    return mv;
  }


  @RequestMapping("/detail")
  public ModelAndView detail(int no, HttpSession session, HttpServletRequest request) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("article", article);
    mv.addObject("tags", article.getTags());
    mv.addObject("comments", commentService.getByArticleNo(no));

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", ((User) session.getAttribute("loginUser")).getNo());
    map.put("articleNo", no);
    if (bookmarkService.get(map) != null) {
      request.setAttribute("bookmarked", true);
    } else {
      request.setAttribute("bookmarked", false);
    }

    mv.setViewName("/adminJsp/article/detail.jsp");
    return mv;
  }

  @RequestMapping("/inactivate")
  public String inactivate(int no) throws Exception {
    if (articleService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/detail?no=" + no;
  }

  @RequestMapping("/activate")
  public String activate(int no) throws Exception {
    if (articleService.undelete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/detail?no=" + no;
  }

}
