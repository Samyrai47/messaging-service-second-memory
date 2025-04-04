package mipt.app.messagenotification.email;

public interface EmailContent {
  String getSubject();

  String getText(String name);
}
