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
        props.put("mail.smtp.host", "smtp.126.com");//smtp������  
        Authenticator authenticator = new MailAuthenticator("abc@126.com", "abc");
        session = Session.getInstance(props, authenticator);  
        msg = new MimeMessage(session);  
        try {  
            // Ⱥ���ʼ�
            Address[] ads = new InternetAddress[5]; 
            for(int i = 0; i < 5; i++) {
                ads[i] = new InternetAddress("abc"+i+"@qq.com");
            }
            // msg.setFrom("abc@126.com");
            
            // ʵ��Ⱥ��
            // ���Ͷ������Recipents���е�һ������ΪMessage.RecipientType.CC
            msg.setRecipients(Message.RecipientType.TO, ads);//�ռ���ַ  
            msg.setSubject("JavaMail hello world example");  
            msg.setSentDate(new Date());  
            String filename = "C:\\Users\\asus\\Desktop\\a.html";//html�ļ���ַ  
            String line = null;  
            StringBuffer sb = new StringBuffer(); 
            if (new File(filename).exists()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));// �����ȡ��������  
            while ((line = br.readLine()) != null) {  
                sb.append(line);//ƴ�ӵ�stringBuffer  
                sb.append("\n");//����˵���Բ��û��ж����Խ���html  
            }  
            br.close();  
            }
//            sb.append("xxx");
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
            Transport.send(msg);//�����˵������ַ������
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
