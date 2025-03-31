package mipt.app.message_service.strategy;

import mipt.app.message_service.dto.MessageType;

public interface EmailContentStrategy {
  MessageType getType();

  String getSubject();

  String getText(String name);
}
