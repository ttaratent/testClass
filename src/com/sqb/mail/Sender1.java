package com.sqb.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sender1 implements Runnable {

    Properties props;
    Session session;
    MimeMessage msg;
    
    public Sender1() {  
        System.out.println("constructor...");  
        props = new Properties();  
        props.put("mail.smtp.host", "smtp.126.com");//smtp服务器  
        Authenticator authenticator = new MailAuthenticator("abc@126.com", "abc");
        session = Session.getInstance(props, authenticator);  
        msg = new MimeMessage(session);  
        try {  
            // 群发邮件
            Address[] ads = new InternetAddress[5]; 
            for(int i = 0; i < 5; i++) {
                ads[i] = new InternetAddress("abc"+i+"@qq.com");
            }
            // msg.setFrom("abc@126.com");
            
            // 实现群发
            // 抄送额外添加Recipents其中第一个参数为Message.RecipientType.CC
            msg.setRecipients(Message.RecipientType.TO, ads);//收件地址  
            msg.setSubject("JavaMail hello world example");  
            msg.setSentDate(new Date());  
            String filename = "C:\\Users\\asus\\Desktop\\a.html";//html文件地址  
            String line = null;  
            StringBuffer sb = new StringBuffer(); 
            if (new File(filename).exists()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));// 解决读取中文乱码  
            while ((line = br.readLine()) != null) {  
                sb.append(line);//拼接到stringBuffer  
                sb.append("\n");//按理说可以不用换行都可以解析html  
            }  
            br.close();  
            }
//            sb.append("xxx");
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
            Transport.send(msg);//发件人的邮箱地址和密码
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
        Sender1 sender = new Sender1();
        sender.send();
    }

}
