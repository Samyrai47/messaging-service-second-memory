package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class RegistrationEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "Добро пожаловать!";
  }

  @Override
  public String getText(String name) {
    return "Добро пожаловать во Вторую Память, " + name + "!";
  }
}
