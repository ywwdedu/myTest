package com.ssm.controller.utils;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class mail {

    public static boolean sendEmail (String randomcode,String to){
        boolean flag=false;
        try{
            Properties pros=new Properties();
            pros.setProperty("mail.smtp.auth","true");//发送服务器需要身份验证
            pros.setProperty("mail.host","smtp.qq.com"); //发送邮件服务器的主机名
            pros.setProperty("mail.transport.protocol","smtp"); //发送邮件协议
            MailSSLSocketFactory msf=new MailSSLSocketFactory();//开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
            msf.setTrustAllHosts(true);
            //开启ssl加密（并不是所有的邮箱服务器都需要，但是qq邮箱服务器是必须的）
            pros.put("mail.smtp.ssl.enable","true");
            pros.put("mail.smtp.ssl.socketFactory","msf");
            Session session=Session.getInstance(pros,new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //用户名密码验证（取得的授权码）
                    return new PasswordAuthentication("849382423@qq.com","dxkcwprqepodbbfe");
                }
            });

            Message message=new MimeMessage(session);
            message.setSubject("激活邮件");
            message.setText("余威爸爸开发的系统发给你的简单邮件"+randomcode);
            message.setContent("<h3>点击链接进行激活</h3><h4>yw爸爸开发的系统发给你的简单邮件</h4><h3><a href='http://172.16.112.20:8080:/user/active?randomcode="+randomcode+"'>http://172.16.112.20:8080:/user/active?randomcode=\"+randomcode+\"</a></h3>","text/html;charset=UTF-8");
            message.setFrom(new InternetAddress("849382423@qq.com"));//发件人地址
            Transport transport=session.getTransport();//开启通信
            transport.connect();//开启连接
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});//设置收件人
            transport.close();
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
