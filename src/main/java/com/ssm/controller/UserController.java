package com.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.controller.utils.SendMessage;
import com.ssm.controller.utils.mail;
import com.ssm.controller.utils.random;
import com.ssm.model.Account;
import com.ssm.model.GeoCoordMap;
import com.ssm.model.User;
import com.ssm.model.migrate;
import com.ssm.service.AccountService;
import com.ssm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private AccountService accountService;

    @RequestMapping("/showUser.do")
    public void  selectUser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user =this.userService.selectUser(userId);
        ObjectMapper mapper =new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }

    @RequestMapping(value = "/hello")
        public String hello(){
        System.out.println("work");
        return "index";

    }
    @RequestMapping(value="/display")
    @ResponseBody
      public User display(String role){
         User user =this.userService.roleDisplay(role);
         return user;
    }

    @RequestMapping(value="/login")
    @ResponseBody
     public Boolean login(String user,String password1,String email){
        String name=user;
        String Prepassword=password1;
        String Email=email;
        Boolean flag=false;
        Account account=new Account();
        account.setUsername(name);
        account.setPassword(Prepassword);
        account.setEmail(Email);
        String randomcode= random.getUUIDUtils();//生成任意字符串
        account.setRandomcode(randomcode);
        account.setState(0);
        if (account.getUsername()!=null){
            flag=accountService.addUser(account);
        }
        return mail.sendEmail(randomcode,email);
     }

    @RequestMapping(value="/isLogin")
    @ResponseBody
    public Boolean isLogin(String userName,String pass) {
        Boolean flag = false;
        Account account=new Account();
        String username=userName;
        account=accountService.checkIn(username);
        if (account!=null) {
            flag = pass.equals(account.getPassword());
        }
         return flag;
    }

    @RequestMapping(value="/active")
    public ModelAndView active(String randomcode){
         accountService.setState(randomcode);
         ModelAndView modelAndView=new ModelAndView("redirect:/Views/index.html");//springMvc重定向
         return modelAndView;

    }

    @RequestMapping(value="/findPasswords")
    public ModelAndView findPasswords(){
        try {
            String passwords=accountService.findpassWords("vvvvv");
            SendMessage.sendMessage(passwords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView=new ModelAndView("redirect:/Views/index.html");
        return modelAndView;
    }


    @RequestMapping(value="getMapInfo")
    @ResponseBody
    public Map<String,Object> getMapInfo(){
         Map<String,Object>map=new HashMap<>();
         List<GeoCoordMap>geoCoordMaps=new ArrayList<>();
         geoCoordMaps=accountService.getMapInfo();
         for (GeoCoordMap mapone:geoCoordMaps){
            String city=mapone.getCityName();
            String cityLocation=mapone.getCityLocation();
            map.put(city,cityLocation.replace("\"",""));
            map.putAll(map);
         }
          System.out.println(map);

          return map;
    }

    @RequestMapping(value = "updateMapInfo")
    @ResponseBody
    public void updateMapInfo(){
        Map<String,Object>map=new HashMap<>();
        List<GeoCoordMap>geoCoordMaps=accountService.getMapInfo();
        for (GeoCoordMap mapone:geoCoordMaps){
            String cityName=mapone.getCityName();
            String cityLocation="["+mapone.getCityLocation()+"]";
            accountService.update(cityName,cityLocation);
        }
    }

    @RequestMapping(value ="selectBjInfo")
    @ResponseBody
    public  List<migrate>selectBjInfo(){
        List<migrate>bj=accountService.selectBjInfo();
        migrate[] arr=new migrate[]{};
      return null;
    }

}
