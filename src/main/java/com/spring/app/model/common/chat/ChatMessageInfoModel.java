package com.spring.app.model.common.chat;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatMessageInfoModel {
    private String message;
    private LocalDateTime timestamp;
    private String roomId;
    private String ownerId;
}
