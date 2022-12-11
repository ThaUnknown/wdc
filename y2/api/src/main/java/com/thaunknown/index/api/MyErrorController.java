package com.thaunknown.index.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
  @RequestMapping("/error")
  public String error() {
    return "Invalid Path";
  }

  public String getErrorPath() {
    return "/error";
  }
}