package com.devil.web.app;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  ArticleService articleService;
  @Autowired
  CommentService commentService;
  @Autowired
  UserService userService;

  @RequestMapping(value = "/add", method=RequestMethod.POST)
  public String add(int arno, int step, int momno, String content, HttpSession session) throws Exception {

    Comment comment = new Comment();

    User user = (User) session.getAttribute("loginUser");
    System.out.println(user.getName());
    System.out.println(user.getNo());
    //System.out.println(user.getNickname());

    comment.setWriter(user);

    System.out.println(comment.getWriter().getName());

    comment.setArticleNo(arno);
    comment.setStep(step);
    comment.setMotherNo(momno);
    comment.setContent(content);
    commentService.add(comment);

    return "redirect:../article/detail?no=" + comment.getArticleNo();
  }

  // article/detail에서 더이상 /comment/list를 직접 경유하지 않음
  // 추후에도 사용하지 않는다면 코드 삭제 예정
  @RequestMapping("/list")
  public ModelAndView list(int no) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("article", articleService.get(no));
    mv.addObject("comments", commentService.getByArticleNo(no));
    mv.setViewName("/appJsp/comment/list.jsp");

    return mv;
  }

  @RequestMapping("/writtenList")
  public ModelAndView list(User user, HttpSession session) throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("user", userService.get(params));
    mv.addObject("commentList", commentService.listByWriter(user));
    mv.setViewName("/appJsp/comment/writtenList.jsp");
    return mv;
  }

  @RequestMapping("/update")
  public String update(Comment comment) throws Exception {
    if (commentService.update(comment) == 0) {
      throw new Exception("해당 댓글이 없습니다.");
    }

    return "redirect:../article/detail?no=" + comment.getArticleNo();
  }

  @RequestMapping("/delete")
  public String delete(int no, int articleNo) throws Exception {
    if (commentService.delete(no) == 0) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    return "redirect:../article/detail?no=" + articleNo;
  }

}
