package com.thaunknown.index.api;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<Users> {
  public Users mapRow (ResultSet rs, int row) throws SQLException {
    Users users = new Users();
    users.setId(rs.getInt("id"));
    users.setLogin(rs.getString("login"));
    users.setPassword(rs.getString("password"));
    return users;
  }
}
