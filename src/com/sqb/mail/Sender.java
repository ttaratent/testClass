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
        props.put("mail.smtp.host", "smtp.126.com");//smtp������  
        session = Session.getInstance(props, null);  
        msg = new MimeMessage(session);  
        try {  
            msg.setFrom("abc@126.com");  
            msg.setRecipients(Message.RecipientType.TO, "abc@qq.com");//�ռ���ַ  
            msg.setSubject("JavaMail hello world example");  
            msg.setSentDate(new Date());  
            String filename = "C:\\Users\\asus\\Desktop\\a.html";//html�ļ���ַ  
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));// �����ȡ��������  
            String line = null;  
            StringBuffer sb = new StringBuffer();  
            while ((line = br.readLine()) != null) {  
                sb.append(line);//ƴ�ӵ�stringBuffer  
                sb.append("\n");//����˵���Բ��û��ж����Խ���html  
            }  
//            sb.append("xxx");
            br.close();  
            BodyPart bodyPart = new MimeBodyPart();//BodyPart���ʼ����ݵĳ����壬�������ļ���ͼƬ��������...  
            bodyPart.setContent(sb.toString(), "text/html;charset=UTF-8");//����ҳ��ı���ֵ  
            Multipart multiPart = new MimeMultipart();//Multipart����BodyPart�ĳ����壬һ��multiPart���԰������BodyPart  
            multiPart.addBodyPart(bodyPart);//��bodyPart��ӵ�multiPart  
            msg.setContent(multiPart);//��MultiPart��Ϊ�ʼ���������msg��  
            msg.saveChanges();//�����Ҫ��ôһ��  
            // msg.setText("Hello, world!\n");//setContent��������������  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public void send() {//���Է��㣬���������͹��̷��������  
        try {  
            Transport.send(msg, "abc@126.com", "abc");//�����˵������ַ������  
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
