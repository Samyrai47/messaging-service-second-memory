package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "Login into Second Memory";
  }

  @Override
  public String getText(String name) {
    return "Dear " + name + ", we detected a login into your account.";
  }
}
