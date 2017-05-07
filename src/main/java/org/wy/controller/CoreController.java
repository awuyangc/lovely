package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.model.Question;
import org.wy.service.CoreService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/core")
public class CoreController {
    @Resource
    private CoreService coreService;
    //抽取题目
    @RequestMapping(value="/getQuestionList")
    @ResponseBody
    public List<Question> register(HttpSession session, int total ) throws Exception{
        List<Question> questionList=coreService.getQuestionList(total);
        return questionList;
    }
}
