package org.wy.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.wy.model.Question;
import org.wy.model.User;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuyang on 2017/3/20.
 */
@Service
public class CoreService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public List<Question> getQuestionList(int total){
        String sql="SELECT * from question limit ?";
        System.out.print(sql);
        List questionList= jdbcTemplate.queryForList(sql,new Object[]{total});
        return questionList;
        /*
        return jdbcTemplate.queryForList(sql, new RowMapper<Question>(){
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getString("id"));
                question.setImgUrl(rs.getString("imgUrl"));
                question.setChkA(rs.getString("chkA"));
                question.setChkB(rs.getString("chkB"));
                question.setChkC(rs.getString("chkC"));
                question.setChkD(rs.getString("chkD"));
                question.setChkE(rs.getString("chkE"));
                question.setChkF(rs.getString("chkF"));
                question.setAnswer(rs.getString("answer"));
                question.setIsDelete(rs.getString("isDelete"));
                return question;
            }
        }, total);
        */
    }

    public int addUser(User user){
        String sql="insert into user(user_id,user_password,user_nickname,create_time,roles) values(?,md5(?),?,now(),0)";
        return jdbcTemplate.update(sql, new Object[] {user.getUser_id(),user.getUser_password(),user.getUser_nickname()});
    }
}
