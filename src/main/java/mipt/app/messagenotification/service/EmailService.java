package mipt.app.messagenotification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.app.messagenotification.dto.MessageDto;
import mipt.app.messagenotification.dto.MessageType;
import mipt.app.messagenotification.email.EmailContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String fromEmail;

  public MimeMessageHelper sendEmail(MessageDto parsedMessage) throws MessagingException {
    EmailContent content = MessageType.getStrategy(parsedMessage.getType());
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(fromEmail);
    helper.setTo(parsedMessage.getEmail());
    helper.setSubject(content.getSubject());

    try (var inputStream =
        Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/content.html"))) {
      String htmlContent =
          new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
              .replace("${content-text}", content.getText(parsedMessage.getUsername()));
      helper.setText(htmlContent, true);
    } catch (IOException e) {
      e.getMessage();
    }

    helper.addInline("SecondMemory.png", new ClassPathResource("templates/SecondMemory.png"));

    emailSender.send(message);
    log.debug("{} message was send to {}", parsedMessage.getType(), parsedMessage.getEmail());
    return helper;
  }
}
