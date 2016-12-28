package com.pb.xc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

/*@Repository("mailService")*/
public class MailService {
	private JavaMailSender mailSender;
	private String from;
	private String subject;
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void sendMail(String address, String path) throws Exception {
		SimpleMailMessage smm = new SimpleMailMessage();
	    // 设定邮件参数
	    smm.setFrom(from);
	    smm.setTo(address);
	    smm.setSubject(subject);
	    smm.setText("请查收您的密码 :    "
	    		+ path);
	    // 发送邮件
	    mailSender.send(smm);
	}
}
