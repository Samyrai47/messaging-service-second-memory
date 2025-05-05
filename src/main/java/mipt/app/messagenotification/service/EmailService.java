package mipt.app.messagenotification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.app.messagenotification.dto.MessageDto;
import mipt.app.messagenotification.dto.MessageType;
import mipt.app.messagenotification.email.EmailContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String fromEmail;

  public SimpleMailMessage sendEmail(MessageDto parsedMessage) {
    EmailContent content = MessageType.getStrategy(parsedMessage.getType());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(fromEmail);
    message.setTo(parsedMessage.getEmail());
    message.setSubject(content.getSubject());
    message.setText(content.getText(parsedMessage.getUsername()));
    emailSender.send(message);
    log.debug("{} message was send to {}", parsedMessage.getType(), parsedMessage.getEmail());
    return message;
  }
}
