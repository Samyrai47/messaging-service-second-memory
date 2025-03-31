package mipt.app.message_service.strategy;

import mipt.app.message_service.dto.MessageType;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEmailStrategy implements EmailContentStrategy {
  @Override
  public MessageType getType() {
    return MessageType.AUTHENTICATION;
  }

  @Override
  public String getSubject() {
    return "Login into Second Memory";
  }

  @Override
  public String getText(String name) {
    return "Dear " + name + ", we detected a login into your account.";
  }
}
