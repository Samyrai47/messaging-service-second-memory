package mipt.app.messagenotification.dto;

import static java.util.Optional.ofNullable;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mipt.app.messagenotification.email.AuthenticationEmailContent;
import mipt.app.messagenotification.email.DeletionEmailContent;
import mipt.app.messagenotification.email.EmailContent;
import mipt.app.messagenotification.email.RegistrationEmailContent;
import mipt.app.messagenotification.exception.NoSuchMessageTypeException;

@Getter
@AllArgsConstructor
public enum MessageType {
  REGISTRATION("REGISTRATION"),
  AUTHENTICATION("AUTHENTICATION"),
  DELETION("DELETION"),
  ;

  private static final Map<MessageType, EmailContent> contents =
      Map.of(
          REGISTRATION, new RegistrationEmailContent(),
          AUTHENTICATION, new AuthenticationEmailContent(),
          DELETION, new DeletionEmailContent());

  private final String type;

  public static EmailContent getStrategy(MessageType type) {
    return ofNullable(contents.get(type))
        .orElseThrow(() -> new NoSuchMessageTypeException("No such type: " + type));
  }
}
