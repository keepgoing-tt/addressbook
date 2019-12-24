package com.java.addressbook.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.addressbook.entity.User;
import com.java.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
@ResponseBody
public class UserController {
    @Autowired
    private UserService  userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody JSONObject data){
        String userName = data.getString("userName");
        String password = data.getString("password");
        JSONObject rs = new JSONObject();
        Boolean isExist;
        //判断账号是否存在
        isExist = userService.queryUser(userName);
        if (isExist){
            rs.put("status",2);
            return rs.toString();
        }else {
            Boolean success = userService.register(userName, password);
            if (success) {
                rs.put("status", 1);
            } else {
                rs.put("status", 0);
            }
        }
        return rs.toString();
    }


    @RequestMapping(value = "/adminregister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminregister(@RequestBody JSONObject data){
        String userName = data.getString("userName");
        String password = data.getString("password");
        JSONObject rs = new JSONObject();
        Boolean isExist;
        //判断账号是否存在
        isExist = userService.adminqueryUser(userName);
        if (isExist){
            rs.put("status",2);
            return rs.toString();
        }else {
            Boolean success = userService.adminregister(userName, password);
            if (success) {
                rs.put("status", 1);
            } else {
                rs.put("status", 0);
            }
        }
        return rs.toString();
    }

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String adminlogin(@RequestBody JSONObject data, HttpSession session){
        JSONObject rs = new JSONObject();
        Boolean isExist = null;
        String userName = data.getString("userName");
        //判断账号是否存在
        isExist = userService.adminqueryUser(userName);
        if (!isExist){
            rs.put("status",3);
            return rs.toString();
        }
        String password = data.getString("password");
        //判断账号密码是否正确
        isExist = userService.adminqueryUser(userName,password);
        if(!isExist){
            rs.put("status",4);
        }else {
          //  Integer status = userService.getApprovalStatusByUserName(userName);
            //登入成功时修改
            rs.put("status",1);
        }
        return rs.toString();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject data, HttpSession session){
        JSONObject rs = new JSONObject();
        Boolean isExist = null;
        String userName = data.getString("userName");
        //判断账号是否存在
        isExist = userService.queryUser(userName);
        if (!isExist){
            rs.put("status",3);
            return rs.toString();
        }
        String password = data.getString("password");
        //判断账号密码是否正确
        isExist = userService.queryUser(userName,password);
        if(!isExist){
            rs.put("status",4);
        }else {
            Integer status = userService.getApprovalStatusByUserName(userName);
            //登入成功时修改
            if (status == 1){
                session.setAttribute("userName",userName);
                userService.loginSuccess(userName);
            }
            rs.put("status",status);
        }
        return rs.toString();
    }

    @RequestMapping(value = "/setUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String setUser(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = (String) session.getAttribute("userName");
        if(userName == null){
            rs.put("status",2);
            return rs.toString();
        }
        User user = new User();
        user.setUserName(userName);
        user.setName(data.getString("name"));
        user.setBirth(data.getString("birth"));
        user.setProfession(data.getString("profession"));
        user.setClassName(data.getString("className"));
        user.setEntranceTime(data.getString("entranceTime"));
        user.setLeaveTime(data.getString("leaveTime"));
        user.setEmploymentUnit(data.getString("employmentUnit"));
        user.setLocation(data.getString("location"));
        user.setPhoneNumber(data.getString("phoneNumber"));
        user.setEmail(data.getString("email"));
        user.setDescription(data.getString("description"));
        Boolean success = userService.setUserByUserName(user);
        if (success){
            rs.put("status",1);
        }else {
            rs.put("status",2);
        }
        return rs.toString();
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getUserList(HttpSession session){
        JSONArray rs = new JSONArray();
        ArrayList<User> list = new ArrayList<>();
            list = userService.getUserList();
            list.forEach(user ->  {
                JSONObject tmp = new JSONObject();
                tmp.put("userName",user.getUserName());
                tmp.put("name",user.getName());
                tmp.put("class",user.getClassName());
                tmp.put("approvalStatus",user.getApprovalStatus());
                rs.add(tmp);
            });
            return rs.toString();
    }

    @RequestMapping(value = "/getUserInformation",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String getUserInformation(HttpSession session){
        JSONArray rs=new JSONArray();
        ArrayList<User> list=new ArrayList<>();
        list=userService.getUserInformation();
        list.forEach(user -> {
            JSONObject tmp=new JSONObject();
            tmp.put("userName",user.getUserName());
            tmp.put("name",user.getName());
            tmp.put("lastLogin",user.getLastLogin());;
            tmp.put("loginTimes",user.getLoginTimes());
            tmp.put("phone",user.getPhoneNumber());
            tmp.put("email",user.getEmail());
            tmp.put("profession",user.getProfession());
            tmp.put("className",user.getClassName());
            tmp.put("entranceTime",user.getEntranceTime());
            tmp.put("leaveTime",user.getLeaveTime());
            tmp.put("employmentUnit",user.getEmploymentUnit());
            tmp.put("location",user.getLocation());
            rs.add(tmp);
        });
        return rs.toString();
    }

    @RequestMapping(value = "/findUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray findUser(@RequestBody JSONObject data){
        String userName = data.getString("user");
        JSONArray rs=new JSONArray();
        ArrayList<User> list=new ArrayList<>();
        list=userService.getfindUser(userName);
        list.forEach(user -> {
            JSONObject tmp=new JSONObject();
            tmp.put("userName",user.getUserName());tmp.put("name",user.getName());
            tmp.put("phone",user.getPhoneNumber());tmp.put("email",user.getEmail());tmp.put("profession",user.getProfession());
            tmp.put("className",user.getClassName());
            tmp.put("entranceTime",user.getEntranceTime());
            tmp.put("leaveTime",user.getLeaveTime());
            tmp.put("employmentUnit",user.getEmploymentUnit());tmp.put("location",user.getLocation());
            rs.add(tmp);
        });
        return  rs;
    }

    @RequestMapping(value = "/getUserByUserName",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getUserByUserName(HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = (String) session.getAttribute("userName");
        if (userName != null){
            User user = userService.getUserByUserName(userName);
            rs.put("name",user.getName());
            rs.put("birth",user.getBirth());
            rs.put("profession",user.getProfession());
            rs.put("className",user.getClassName());
            rs.put("entranceTime",user.getEntranceTime());
            rs.put("leaveTime",user.getLeaveTime());
            rs.put("employmentUnit",user.getEmploymentUnit());
            rs.put("location",user.getLocation());
            rs.put("phoneNumber",user.getPhoneNumber());
            rs.put("email",user.getEmail());
            rs.put("lastLogin",user.getLastLogin());
            rs.put("loginTimes",user.getLoginTimes());
            rs.put("description",user.getDescription());
        }
        return rs.toString();
    }

    @RequestMapping(value = "/examineAccount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String examineAccount(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = data.getString("userName");
        if (userName == null){
            rs.put("status",2);
        }else {
            Boolean success = userService.setApprovalStatus(userName,1);
            if (success){
                rs.put("status",1);
            }else {
                rs.put("status",0);
            }
        }
        return rs.toString();
    }

    @RequestMapping(value = "/disabledAccount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String disabledAccount(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = data.getString("userName");
        if (userName == null){
            return rs.toString();
        }else {
            Boolean success = userService.setApprovalStatus(userName,2);
            if (success){
                rs.put("status",1);
            }else {
                rs.put("status",0);
            }
        }
        return rs.toString();
    }

    @RequestMapping(value = "/deleteAccount",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteAccount(@RequestBody JSONObject data,HttpSession session){
        JSONObject rs = new JSONObject();
        String userName = data.getString("userName");
        if (userName == null) {
            return rs.toString();
        }else {
            Boolean success=userService.deleteUserAccount(userName);
            if(success) {
                rs.put("status",1);
            }else {
                rs.put("status",0);
            }
        }
        return rs.toString();
    }
}
