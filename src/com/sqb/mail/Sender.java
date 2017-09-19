package com.sqb.mail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sender implements Runnable {

    Properties props;
    Session session;
    MimeMessage msg;
    
    public Sender() {  
        System.out.println("constructor...");  
        props = new Properties();  
        props.put("mail.smtp.host", "smtp.126.com");//smtp服务器  
        session = Session.getInstance(props, null);  
        msg = new MimeMessage(session);  
        try {  
            msg.setFrom("abc@126.com");  
            msg.setRecipients(Message.RecipientType.TO, "abc@qq.com");//收件地址  
            msg.setSubject("JavaMail hello world example");  
            msg.setSentDate(new Date());  
            String filename = "C:\\Users\\asus\\Desktop\\a.html";//html文件地址  
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));// 解决读取中文乱码  
            String line = null;  
            StringBuffer sb = new StringBuffer();  
            while ((line = br.readLine()) != null) {  
                sb.append(line);//拼接到stringBuffer  
                sb.append("\n");//按理说可以不用换行都可以解析html  
            }  
//            sb.append("xxx");
            br.close();  
            BodyPart bodyPart = new MimeBodyPart();//BodyPart是邮件内容的承载体，可以是文件，图片，附件等...  
            bodyPart.setContent(sb.toString(), "text/html;charset=UTF-8");//设置页面的编码值  
            Multipart multiPart = new MimeMultipart();//Multipart又是BodyPart的承载体，一个multiPart可以包含多个BodyPart  
            multiPart.addBodyPart(bodyPart);//将bodyPart添加到multiPart  
            msg.setContent(multiPart);//将MultiPart设为邮件内容主体msg的  
            msg.saveChanges();//大概需要这么一下  
            // msg.setText("Hello, world!\n");//setContent涵盖了它的作用  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public void send() {//测试方便，单独将发送过程分离出来了  
        try {  
            Transport.send(msg, "abc@126.com", "abc");//发件人的邮箱地址和密码  
            System.out.println("sent success!");  
        } catch (MessagingException mex) {  
            System.out.println(new Date() + " send failed, exception: " + mex);  
        }  
    }  
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String[] args) {
        Sender sender = new Sender();
        sender.send();
    }

}
