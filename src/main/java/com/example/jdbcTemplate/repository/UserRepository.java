package com.example.jdbcTemplate.repository;

import com.example.jdbcTemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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


    //List of rows of maps of users with key value [{"city": "New York", "email": "johndoe@gmail.com","id": 1,"name": "John Doe"},
    // {"city": "Scranton","email": "miker@gmail.com","id": 2,"name": "Mike Ray"}]
    public List<User> getUserList(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //not recommended
    public List<Map<String, Object>> getUserListMap(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.queryForList(sql);
    }

    public int saveByNamedParameter(User user){
        String sql = "INSERT INTO users(name, email, city) VALUES(:userName, :userEmail, :userCity)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userName", user.getName())
                .addValue("userEmail", user.getEmail())
                .addValue("userCity", user.getCity());

        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public int deleteUser(int id){
        String sql = "DELETE FROM user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int update(int id, String city) {
        String sql = "UPDATE users SET city = :city WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("city", city);

        return namedParameterJdbcTemplate.update(sql, params);
    }


}
