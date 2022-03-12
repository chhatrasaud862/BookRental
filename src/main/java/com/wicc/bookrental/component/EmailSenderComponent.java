package com.wicc.bookrental.component;

import com.wicc.bookrental.dto.AuthorDto;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderComponent {

    public String sendEmail(AuthorDto authorDto)  {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication("saudchhatra25@gmail.com","9803238101c");
            email.setSSLOnConnect(true);
            email.setFrom("saudchhatra25@gmail.com");
            email.setSubject(authorDto.getName());
            email.setMsg("congratulation your are added as author");
            email.addTo(authorDto.getEmail());
            email.send();
            return ("Email send successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
            return ("failed to send email");
        }

    }
}
