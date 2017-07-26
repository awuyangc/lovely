package org.wy.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.wy.model.Paper;
import org.wy.model.Question;
import org.wy.model.User;
import org.wy.model.Vote;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wuyang on 2017/3/20.
 */
@Service
public class CoreService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Question getQuestion(String question_id){
        String sql="select * from question where id=?";
        Question question= jdbcTemplate.queryForObject(sql,new Object[]{question_id},new BeanPropertyRowMapper<Question>(Question.class));
        return question;
    }

    public List<Question> getQuestionList(int total){
        String sql="select * from question where isDelete=0 and flag=0 order by rand() limit ? ";
        List questionList= jdbcTemplate.queryForList(sql,new Object[]{total});
        return questionList;
    }

    public List<Question> getQuestionListForVote(String open_id,int total){
        //String sql="select * from question where isDelete=0 and flag=1 order by id limit ? ";
        String sql="select * from question  where isDelete=0 and flag=1 and id not in(select question_id from vote  where open_id=?) order by id limit ?";
        List questionList= jdbcTemplate.queryForList(sql,new Object[]{open_id,total});
        return questionList;
    }

    public List<Question> getQuestionListAll(){
        String sql="select * from question order by id ";
        List questionList= jdbcTemplate.queryForList(sql);
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

    public List<Vote> getVoteListAll(){
        String sql="select c.id as question_id,c.imgUrl,MAX(answerA) answerA,MAX(answerB) answerB,MAX(answerC) answerC,MAX(answerD) answerD,MAX(answerE) answerE\n" +
                "from(\n" +
                "select a.id,a.imgUrl,\n" +
                "CASE b.answer WHEN 'A' THEN count(b.answer) ELSE 0 END  answerA,\n" +
                "CASE b.answer WHEN 'B' THEN count(b.answer) ELSE 0 END  answerB,\n" +
                "CASE b.answer WHEN 'C' THEN count(b.answer) ELSE 0 END  answerC,\n" +
                "CASE b.answer WHEN 'D' THEN count(b.answer) ELSE 0 END  answerD,\n" +
                "CASE b.answer WHEN 'E' THEN count(b.answer) ELSE 0 END  answerE \n" +
                "from question a left join vote b on b.question_id=a.id where isDelete=0 and flag=1 \n" +
                "GROUP BY a.id,b.answer  order by a.createtime desc\n" +
                ") c GROUP BY c.id order by c.id";
        List voteList= jdbcTemplate.queryForList(sql);
        return voteList;
    }

    public int updateQuestionFlag(String question_id,String answer){
        String sql="update question set answer='"+answer+"',flag=0  where id="+question_id;
        int[] result=jdbcTemplate.batchUpdate(sql);
        return result[0];
    }



    public int addUser(User user){
        String sql="insert into user(user_id,user_password,user_nickname,create_time,roles) values(?,md5(?),?,now(),0)";
        return jdbcTemplate.update(sql, new Object[] {user.getUser_id(),user.getUser_password(),user.getUser_nickname()});
    }

    public Paper setPaperInfo(String question_id,String user_answer,String open_id){
        Paper paper =new Paper();
        String sql="select answer from question where id in ("+question_id+") order by field(id,"+question_id+")";
        List<Map<String,Object>> right_answer_all=jdbcTemplate.queryForList(sql);
        //用户答案和正确答案比对，记录下错误的题号，和错误题号对应的正确答案
        String[] arrayQuestion_id=question_id.split(",");
        String[] arrayUser_answer=user_answer.split(",");
        String[] arrayRight_answer_all=new String[right_answer_all.size()];
        for(int i=0;i<right_answer_all.size();i++){
            Map<String,Object> map=right_answer_all.get(i);
            arrayRight_answer_all[i]=map.get("answer").toString();
        }
        ArrayList<String> errorQuestion=new ArrayList<String>();
        ArrayList<String> errorAnswer=new ArrayList<String>();
        ArrayList<String> rightAnswer=new ArrayList<String>();
        for (int i=0;i<arrayQuestion_id.length;i++) {
            if(!arrayUser_answer[i].equals(arrayRight_answer_all[i])){
                errorQuestion.add(arrayQuestion_id[i]);
                errorAnswer.add(arrayUser_answer[i]);
                rightAnswer.add(arrayRight_answer_all[i]);
            }
        }
        String score=(arrayQuestion_id.length-errorQuestion.size())+"";
        paper.setUser_open_id(open_id);
        paper.setQuestion_id(question_id);
        paper.setUser_answer(user_answer);
        paper.setScore(score);
        paper.setError_question(listToString(errorQuestion));
        paper.setError_answer(listToString(errorAnswer));
        paper.setRight_answer(listToString(rightAnswer));
        return paper;
    }

    public Paper getPaper(String id){
        String sql="select * from paper where user_open_id=? ORDER BY create_time desc limit 1";
        /*
        Paper paper= jdbcTemplate.queryForObject(sql,new RowMapper<Paper>(){
            @Override
            public Paper mapRow(ResultSet rs, int rowNum) throws SQLException{
                Paper paper = new Paper();
                paper.setId(rs.getString("id"));
                paper.setUser_open_id(rs.getString("user_open_id"));
                paper.setQuestion_id(rs.getString("question_id"));
                paper.setUser_answer(rs.getString("user_answer"));
                paper.setScore(rs.getString("score"));
                paper.setError_question(rs.getString("error_question"));
                paper.setError_answer(rs.getString("error_answer"));
                paper.setRight_answer(rs.getString("right_answer"));
                paper.setCreate_time(rs.getDate("create_time"));
                return paper;
            }
        }, id);
        */
        Paper paper= jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<Paper>(Paper.class));
        return paper;
    }

    public int savePaper(Paper paper){
        String sql="insert into paper(user_open_id,question_id,user_answer,score,error_question,error_answer,right_answer,create_time) values(?,?,?,?,?,?,?,now())";
        return jdbcTemplate.update(sql, new Object[] {paper.getUser_open_id(),paper.getQuestion_id(),paper.getUser_answer(),paper.getScore(),paper.getError_question(),paper.getError_answer(),paper.getRight_answer()});
    }

    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }

    //获取错误题集合
    public List<Question> getErrQuestionList(String question_id){

        String sql="select * from question where id in ("+question_id+") order by field(id,"+question_id+")";
        List questionList= jdbcTemplate.queryForList(sql);
        return questionList;
    }

    public int saveOtherAnswer(String paper_id,String question_id,String otherAnswer,String reason){

        String sql="insert into otheranswer(paper_id,question_id,otherAnswer,reason,createtime) values(?,?,?,?,now())";
        return jdbcTemplate.update(sql, new Object[] {paper_id,question_id,otherAnswer,reason});
    }

    public int saveVoteQuestion(String open_id,String question_id,String voteAnswer){

        String sql="insert into vote(open_id,question_id,answer,createtime) values(?,?,?,now())";
        return jdbcTemplate.update(sql, new Object[] {open_id,question_id,voteAnswer});
    }

    public List<Map<String,Object>> getVoteQuestion(String question_id){

        String sql="select * from vote where question_id ="+question_id+" ORDER BY createtime DESC";
        List voteList= jdbcTemplate.queryForList(sql);
        return voteList;
    }
}

