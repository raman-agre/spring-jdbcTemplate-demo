package com.example.jdbcTemplate.repository;

import com.example.jdbcTemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //using custom row mapper
    public User getUserDetails(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
          User user =   new User();
          user.setId(rs.getInt("id"));
          user.setName(rs.getString("name"));
          user.setEmail(rs.getString("email"));
          user.setCity(rs.getString("city"));
          return user;
        }, id
        );
    }

    //using bean property row mapper
    public User getUserDetails2(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserList(){
        String sql = "SELECT & FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

}
