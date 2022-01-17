package com.mailproject.mailsending;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
public class MailsendingApplication {
	@Autowired
    private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(MailsendingApplication.class, args);
	}

	void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("ksaibharath8329@gmail.com");

        msg.setSubject("Testing from Spring Boot Application through java");
        msg.setText("Hello, This is Sai Bharath Kondepati");

        javaMailSender.send(msg);

    }
	
	void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("ksaibharath8329@gmail.com");

        helper.setSubject("Testing from Spring Boot Application through java");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

		// hard coded a file path
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\saibh\\OneDrive\\Pictures\\Screenshots\\WallPaper.png"));

        helper.addAttachment("WallPaper.png", file);

        javaMailSender.send(msg);

    }	
}
