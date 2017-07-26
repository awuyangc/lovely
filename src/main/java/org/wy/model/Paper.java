package org.wy.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/8.
 */
public class Paper {
    private String id;
    private String user_open_id;
    private String question_id;
    private String user_answer;
    private String score;
    private String error_question;
    private String error_answer;
    private String right_answer;
    private Date create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_open_id() {
        return user_open_id;
    }

    public void setUser_open_id(String user_open_id) {
        this.user_open_id = user_open_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getError_question() {
        return error_question;
    }

    public void setError_question(String error_question) {
        this.error_question = error_question;
    }

    public String getError_answer() {
        return error_answer;
    }

    public void setError_answer(String error_answer) {
        this.error_answer = error_answer;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
