package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "Вход в ваш аккаунт";
  }

  @Override
  public String getText(String name) {
    return name + ", кто-то вошел в ваш аккаунт. Если это были вы: рады вас видеть вновь!";
  }
}
