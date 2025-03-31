package mipt.app.message_service.strategy;

import mipt.app.message_service.dto.MessageType;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEmailStrategy implements EmailContentStrategy {
  @Override
  public MessageType getType() {
    return MessageType.REGISTRATION;
  }

  @Override
  public String getSubject() {
    return "Welcome to Second Memory";
  }

  @Override
  public String getText(String name) {
    return "Welcome aboard, " + name + "!";
  }
}
