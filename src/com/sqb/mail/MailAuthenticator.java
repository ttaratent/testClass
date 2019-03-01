package com.sqb.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

    private String user;
    private String pwd;
    
    public MailAuthenticator(String user, String pwd) {
        super();
        this.user = user;
        this.pwd = pwd;
    }
    
    @Override 
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(user, pwd);  
    }  
}
