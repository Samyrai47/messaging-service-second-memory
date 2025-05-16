package mipt.app.messagenotification.email;

import org.springframework.stereotype.Component;

@Component
public class DeletionEmailContent implements EmailContent {
  @Override
  public String getSubject() {
    return "Нам жаль видеть, как вы уходите";
  }

  @Override
  public String getText(String name) {
    return "Прощаться всегда непросто, но мы уважаем ваше решение, " + name + ".";
  }
}
