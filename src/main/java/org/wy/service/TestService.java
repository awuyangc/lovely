package org.wy.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */
@Service
public class TestService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public int insertTestData(){
        StringBuffer sqlParam=new StringBuffer();
        String[] answerArray=new String[]{"A","B","C","D","E","F"};
        for(int i=0;i<18017;i++){
            int rand=(int)(Math.random()*(5+1));
            if(i==0) {
                sqlParam.append("('" + i + ".jpg','选项A','选项B','选项C','选项D','选项E','选项F','" + answerArray[rand] + "',0)");
            }
            else{
                sqlParam.append(",('" + i + ".jpg','选项A','选项B','选项C','选项D','选项E','选项F','" + answerArray[rand] + "',0)");
            }
        }
        String sql="insert into question(imgUrl,chkA,chkB,chkC,chkD,chkE,chkF,answer,isDelete) VALUES"+sqlParam.toString();;
        int result= jdbcTemplate.update(sql);
        return result;
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
}
