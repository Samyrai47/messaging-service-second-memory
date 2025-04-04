package mipt.app.messagenotification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.app.messagenotification.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.mail.MailException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaEventsService {
  private final ObjectMapper objectMapper;
  private final EmailService emailService;

  @RetryableTopic(
      backoff = @Backoff(value = 3000L),
      attempts = "3",
      dltStrategy = DltStrategy.FAIL_ON_ERROR,
      include = MailException.class)
  @KafkaListener(topics = {"${topic-to-consume-message}"})
  public void consumeMessage(String message) {
    try {
      MessageDto parsedMessage = objectMapper.readValue(message, MessageDto.class);
      log.debug("Retrieved message {}", message);
      emailService.sendEmail(parsedMessage);
    } catch (JsonProcessingException exception) {
      log.error("Error occurred when parsing kafka message", exception);
    }
  }
}
