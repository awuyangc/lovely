package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.model.*;
import org.wy.service.CoreService;
import org.wy.util.WeiXinUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/core")
public class CoreController {
    @Resource
    private CoreService coreService;

    //获取题目
    //抽取题目
    @RequestMapping(value="/getQuestion")
    @ResponseBody
    public Question getQuestion(HttpSession session, String question_id ) throws Exception{
        Question question=coreService.getQuestion(question_id);
        return question;
    }


    //抽取题目
    @RequestMapping(value="/getQuestionList")
    @ResponseBody
    public List<Question> getQuestionList(HttpSession session, int total ) throws Exception{
        List<Question> questionList=coreService.getQuestionList(total);
        return questionList;
    }

    //抽取投票题目
    @RequestMapping(value="/getQuestionListForVote")
    @ResponseBody
    public List<Question> getQuestionListForVote(HttpSession session, int total) throws Exception{
        SNSUserInfo suser=(SNSUserInfo) session.getAttribute("suser");
        List<Question> questionList=coreService.getQuestionListForVote(suser.getOpenid(),total);
        return questionList;
    }

    //抽取投票题目—微信端
    @RequestMapping(value="/getQuestionListForVoteWX")
    @ResponseBody
    public List<Question> getQuestionListForVoteWX(HttpSession session, int total,String openid) throws Exception{
        List<Question> questionList=coreService.getQuestionListForVote(openid,total);
        return questionList;
    }


    //获取所有题目
    @RequestMapping(value="/getQuestionListAll")
    @ResponseBody
    public BaseJsonData getQuestionListAll(HttpSession session) throws Exception{
        List<Question> questionList=coreService.getQuestionListAll();
        BaseJsonData baseJsonData=new BaseJsonData();
        baseJsonData.setData(questionList);
        return baseJsonData;
    }



    //获取所有待解决题目的投票情况
    @RequestMapping(value="/getVoteListAll")
    @ResponseBody
    public BaseJsonData getVoteListAll(HttpSession session) throws Exception{
        List<Vote> voteList=coreService.getVoteListAll();
        BaseJsonData baseJsonData=new BaseJsonData();
        baseJsonData.setData(voteList);
        return baseJsonData;
    }

    //保存
    @RequestMapping(value="/saveUserQuestion")
    @ResponseBody
    public Paper saveUserQuestion(HttpSession session, String question_id,String user_answer) throws Exception{
        SNSUserInfo suser=(SNSUserInfo) session.getAttribute("suser");
        Paper paper=coreService.setPaperInfo(question_id,user_answer,suser.getOpenid());
        int saveCount=coreService.savePaper(paper);
        return paper;
    }

    //保存
    @RequestMapping(value="/saveUserQuestionForWx")
    @ResponseBody
    public Paper saveUserQuestionForWx(HttpSession session, String question_id,String user_answer,String openid) throws Exception{
        Paper paper=coreService.setPaperInfo(question_id,user_answer,openid);
        int saveCount=coreService.savePaper(paper);
        return paper;
    }


    //保存
    @RequestMapping(value="/updateQuestionFlag")
    @ResponseBody
    public int updateQuestionFlag(HttpSession session, String question_id,String answer) throws Exception{
        int result=coreService.updateQuestionFlag(question_id,answer);
        return result;
    }

    //游客登录
    @RequestMapping(value="/loginYk")
    public String loginYk(HttpSession session,HttpServletRequest request) throws Exception{
        String redirect_uris="http://awuyangc.iask.in/wechat/oauth.action";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeiXinUtil.getAPPID()+"&redirect_uri="+redirect_uris+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        //snsapi_userinfo
        //snsapi_base
        System.out.println(url);
        return "redirect:"+url;
    }

    //显示得分
    @RequestMapping(value="/resultScore")
    @ResponseBody
    public Paper resultScore(HttpSession session,HttpServletRequest request) throws Exception{
        SNSUserInfo suser=(SNSUserInfo) session.getAttribute("suser");
        Paper paper=coreService.getPaper(suser.getOpenid());
        return paper; //转发到指定的url
    }

    //显示得分
    @RequestMapping(value="/resultScoreForWX")
    @ResponseBody
    public Paper resultScoreForWX(HttpSession session,HttpServletRequest request,String openid) throws Exception{
        Paper paper=coreService.getPaper(openid);
        return paper; //转发到指定的url
    }


    //查看详细答题情况（错误题集合）
    @RequestMapping(value="/resultDetail")
    @ResponseBody
    public  List<Question> resultDetail(HttpSession session,HttpServletRequest request,String question_id) throws Exception{
        List<Question> questionList=coreService.getErrQuestionList(question_id);
        return questionList; //转发到指定的url
    }

    //保存用户异议信息
    @RequestMapping(value="/saveOtherAnswer")
    @ResponseBody
    public  int saveOtherAnswer(HttpSession session,HttpServletRequest request,String paper_id,String question_id,String otherAnswer,String reason) throws Exception{
        int result=coreService.saveOtherAnswer(paper_id,question_id,otherAnswer,reason);
        return result; //转发到指定的url
    }

    //保存用户投票信息
    @RequestMapping(value="/saveVoteQuestion")
    @ResponseBody
    public  int saveVoteQuestion(HttpSession session,HttpServletRequest request,String question_id,String voteAnswer) throws Exception{
        SNSUserInfo suser=(SNSUserInfo) session.getAttribute("suser");
        String open_id=suser.getOpenid();
        //String open_id="onYNHtx1ajnySMqyWzbB0I_bzNG8";
        int result=coreService.saveVoteQuestion(open_id,question_id,voteAnswer);
        return result; //转发到指定的url
    }

    //保存用户投票信息
    @RequestMapping(value="/saveVoteQuestionForWX")
    @ResponseBody
    public  int saveVoteQuestionForWX(HttpSession session,HttpServletRequest request,String question_id,String voteAnswer,String openid) throws Exception{
        int result=coreService.saveVoteQuestion(openid,question_id,voteAnswer);
        return result; //转发到指定的url
    }


    //获取投票结果
    @RequestMapping(value="/getVoteQuestion")
    @ResponseBody
    public  Map<String, Object> getVoteQuestion(HttpSession session,HttpServletRequest request,String question_id) throws Exception{
        List<Map<String,Object>> voteList=coreService.getVoteQuestion(question_id);
        Map map=new HashMap();
        map.put("A",0);
        map.put("B",0);
        map.put("C",0);
        map.put("D",0);
        map.put("E",0);
        for(int i=0;i<voteList.size();i++){
            String answer=voteList.get(i).get("answer").toString();
            int m=(Integer)map.get(answer)+1;
            map.put(answer,m);
        }
        return map; //转发到指定的url
    }
}
