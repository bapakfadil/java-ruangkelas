package com.registration.course.serverapp.api.email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.registration.course.serverapp.api.dto.request.EmailRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailSenderService {

  private JavaMailSender mailSender;

  private final ResourceLoader resourceLoader;

  public EmailRequest sendEmailTemplate(EmailRequest emailRequest, Boolean lulus) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());

      // ambil file html template
      helper.setText(this.getHtmlContent(lulus), true);

      mailSender.send(message);
    } catch (Exception e) {
      throw new IllegalStateException("Email false to send!!!");
    }
    return emailRequest;
  }

  public String getHtmlContent(Boolean lulus) throws IOException {
    if (lulus) {
      Resource resource = resourceLoader.getResource("classpath:static/emailtemplate-lulus.html");
      Path filePath = ResourceUtils.getFile(resource.getURI()).toPath();
      String htmlContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
      return htmlContent;
    } else {
      Resource resource = resourceLoader.getResource("classpath:static/emailtemplate-gagal.html");
      Path filePath = ResourceUtils.getFile(resource.getURI()).toPath();
      String htmlContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
      return htmlContent;
    }
  }
}
