package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class RegistrationEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "Welcome to Second Memory";
  }

  @Override
  public String getText(String name) {
    return "Welcome aboard, " + name + "!";
  }
}
