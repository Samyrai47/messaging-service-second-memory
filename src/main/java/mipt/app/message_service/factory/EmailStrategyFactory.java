package mipt.app.message_service.factory;

import java.util.EnumMap;
import java.util.Map;
import mipt.app.message_service.dto.MessageType;
import mipt.app.message_service.strategy.AuthenticationEmailStrategy;
import mipt.app.message_service.strategy.DeletionEmailStrategy;
import mipt.app.message_service.strategy.EmailContentStrategy;
import mipt.app.message_service.strategy.RegistrationEmailStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailStrategyFactory {
  private final Map<MessageType, EmailContentStrategy> strategies =
      new EnumMap<>(MessageType.class);

  @Autowired
  public EmailStrategyFactory(
      AuthenticationEmailStrategy authenticationStrategy,
      RegistrationEmailStrategy registrationStrategy,
      DeletionEmailStrategy deletionStrategy) {
    strategies.put(MessageType.AUTHENTICATION, authenticationStrategy);
    strategies.put(MessageType.REGISTRATION, registrationStrategy);
    strategies.put(MessageType.DELETION, deletionStrategy);
  }

  public EmailContentStrategy getStrategy(MessageType type) {
    return strategies.get(type);
  }
}
