package com.devil.web.app;

import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.NotificationService;

@Controller
@RequestMapping("/notification")
@SessionAttributes("loginUser")
public class NotificationController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  NotificationService notificationService;

  @GetMapping("/list")
  public void list(@ModelAttribute User loginUser, Model model) throws Exception {
    List<Notification> notificationList = notificationService.list(loginUser);
    model.addAttribute("notificationList", notificationList);
  }
}
