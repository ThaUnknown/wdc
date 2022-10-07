package com.thaunknown.index.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

@RestController
@SpringBootApplication
public class ApiApplication {
  static JsonElement json;
  static Gson gson = new Gson();

  public static void main(String[] args) throws FileNotFoundException, IOException {
    String path = "D:/Webdevelopment/spring/api/src/main/resources/export.json";
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

    json = gson.fromJson(bufferedReader, JsonElement.class);
    bufferedReader.close();
    SpringApplication.run(ApiApplication.class, args);
  }

  @GetMapping("/")
  String root() {
    return gson.toJson(json);
  }

  @GetMapping("/{type}")
  String collectionType(@PathVariable String type) {
    return gson.toJson(json.getAsJsonObject().get(type));
  }

  @GetMapping("/{type}/{id}")
  String collectionItem(@PathVariable String type, @PathVariable String id) {
    JsonArray collectionType = json.getAsJsonObject().get(type + 's').getAsJsonArray();
    return gson.toJson(getById(collectionType, id));
  }

  JsonElement getById(JsonArray items, String id) {
    for (JsonElement item : items) {
      if (item.getAsJsonObject().get("_id").getAsString().equals(id)) {
        return item;
      }
    }
    return null;
  }
}
