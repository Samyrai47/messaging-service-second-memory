package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class DeletionEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "We’re sad to see you go";
  }

  @Override
  public String getText(String name) {
    return "It’s never easy to say goodbye, but we respect your decision, " + name + ".";
  }
}
