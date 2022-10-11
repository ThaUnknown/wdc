package com.thaunknown.index.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@SpringBootApplication
public class ApiApplication {
  static Gson gson = new Gson();

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
    JsonElement json = gson.fromJson(Files.readString(ResourceUtils.getFile("classpath:" + type + ".json").toPath()), JsonElement.class);
    JsonElement element = json.getAsJsonObject().get(id);
    return gson.toJson(element);
  }
}
