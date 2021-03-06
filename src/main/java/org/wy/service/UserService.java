package org.wy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.wy.model.Role;
import org.wy.model.User;

import javax.annotation.Resource;
import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuyang on 2017/3/20.
 */
@Service
public class UserService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public User getUserById(String userId){
        String sql="SELECT * from user where user_id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setUser_nickname(rs.getString("user_nickname"));
                user.setUser_password(rs.getString("user_password"));
                user.setRoles( rs.getString("roles"));
                return user;
            }
        }, userId);
    }

    public int addUser(User user){
        String sql="insert into user(user_id,user_password,user_nickname,create_time,roles) values(?,md5(?),?,now(),0)";
        return jdbcTemplate.update(sql, new Object[] {user.getUser_id(),user.getUser_password(),user.getUser_nickname()});
    }
}
