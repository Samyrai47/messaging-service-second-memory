package mipt.app.message_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.app.message_service.dto.DtoMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {
  private final ObjectMapper objectMapper;
  private final EmailService emailService;

  @KafkaListener(topics = {"${topic-to-consume-message}"})
  public void consumeMessage(String message) throws JsonProcessingException {
    DtoMessage parsedMessage = objectMapper.readValue(message, DtoMessage.class);
    log.info("Retrieved message {}", message);
    emailService.sendEmail(
        parsedMessage.getEmail(), parsedMessage.getUsername(), parsedMessage.getType());
  }
}
