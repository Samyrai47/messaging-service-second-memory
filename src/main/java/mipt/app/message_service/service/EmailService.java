package mipt.app.message_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.app.message_service.dto.MessageType;
import mipt.app.message_service.factory.EmailStrategyFactory;
import mipt.app.message_service.strategy.EmailContentStrategy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;
  private final EmailStrategyFactory emailStrategyFactory;

  public void sendEmail(String to, String username, MessageType type) {
    EmailContentStrategy strategy = emailStrategyFactory.getStrategy(type);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("${spring.mail.username}");
    message.setTo(to);
    message.setSubject(strategy.getSubject());
    message.setText(strategy.getText(username));
    emailSender.send(message);
    log.info("{} message was send to {}", type, to);
  }
}
