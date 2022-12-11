package com.thaunknown.index.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@SpringBootApplication
public class ApiApplication {
  static Gson gson = new Gson();

  @Autowired
  JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  @GetMapping("/")
  String root() {
    return "No path specified. Try: collections, columns, items, sections. \n Eg: /columns or /items/{slug}";
  }

  @GetMapping("/{type}")
  String collectionType(@PathVariable String type) throws FileNotFoundException, IOException {
    return Files.readString(ResourceUtils.getFile("classpath:" + type + ".json").toPath());
  }

  @GetMapping("/{type}/{id}")
  String collectionItem(@PathVariable String type, @PathVariable String id) throws FileNotFoundException, IOException {
    JsonElement json = gson.fromJson(Files.readString(ResourceUtils.getFile("classpath:" + type + ".json").toPath()),
        JsonElement.class);
    JsonElement element = json.getAsJsonObject().get(id);
    return gson.toJson(element);
  }

  @PostMapping("/login")
  String login(HttpSession session, @RequestParam(value = "username") String login,
      @RequestParam(value = "password") String password) {
    String SQL = "select * from users";
    List<Users> users = jdbcTemplate.query(SQL, new UserMapper());

    for (Users user : users) {
      if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
        session.setAttribute("login", login);
        return "true";
      }
    }

    return "false";
  }

  @PostMapping("/create")
  String createUser(HttpSession session, @RequestParam(value = "username") String login,
      @RequestParam(value = "password") String password) {

    if (session.getAttribute(login) != null)
      return "false";
    // de-dupe
    String SQL = "select * from users";
    List<Users> users = jdbcTemplate.query(SQL, new UserMapper());

    for (Users user : users) {
      if (login.equals(user.getLogin()))
        return "false";
    }

    // create
    String query = "INSERT INTO users(id, login, password) values(?,?,?)";
    jdbcTemplate.update(query, users.size(), login, password);
    return "true";
  }
}
