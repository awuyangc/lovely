package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/12.
 */
@Controller
@RequestMapping("/admin")
public class ForwardAdminController {

    @RequestMapping("/login")
    public String toPCLogin(){
        return "system/login";
    }

    @RequestMapping("/index")
    public String toAdminIndex(){
        return "system/index";
    }

    @RequestMapping("/manage")
    public String toQuestionManage(){
        return "system/manage";
    }

    @RequestMapping("/vote")
    public String toVote(){
        return "system/vote";
    }

    @RequestMapping("/instruction")
    public String toInstruction(){
        return "system/instruction";
    }

    @RequestMapping("/iecheck")
    public String toIndex(){
        return "system/iecheck";
    }
}
