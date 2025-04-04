package mipt.app.messagenotification.factory;

import java.util.EnumMap;
import java.util.Map;
import mipt.app.messagenotification.dto.MessageType;
import mipt.app.messagenotification.email.AuthenticationEmailContent;
import mipt.app.messagenotification.email.DeletionEmailContent;
import mipt.app.messagenotification.email.EmailContent;
import mipt.app.messagenotification.email.RegistrationEmailContent;
import mipt.app.messagenotification.exception.NoSuchMessageTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailContentFactory {
  private final Map<MessageType, EmailContent> contents = new EnumMap<>(MessageType.class);

  @Autowired
  public EmailContentFactory(
      AuthenticationEmailContent authenticationContent,
      RegistrationEmailContent registrationContent,
      DeletionEmailContent deletionContent) {
    contents.put(MessageType.AUTHENTICATION, authenticationContent);
    contents.put(MessageType.REGISTRATION, registrationContent);
    contents.put(MessageType.DELETION, deletionContent);
  }

  public EmailContent getContent(MessageType type) {
    if (!contents.containsKey(type)) {
      throw new NoSuchMessageTypeException();
    }
    return contents.get(type);
  }
}
