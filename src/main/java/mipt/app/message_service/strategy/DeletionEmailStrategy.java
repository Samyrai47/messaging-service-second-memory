package mipt.app.message_service.strategy;

import mipt.app.message_service.dto.MessageType;
import org.springframework.stereotype.Component;

@Component
public class DeletionEmailStrategy implements EmailContentStrategy {
  @Override
  public MessageType getType() {
    return MessageType.DELETION;
  }

  @Override
  public String getSubject() {
    return "We’re sad to see you go";
  }

  @Override
  public String getText(String name) {
    return "It’s never easy to say goodbye, but we respect your decision, " + name + ".";
  }
}
