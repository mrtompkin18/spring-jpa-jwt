package com.spring.app.model.common.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomInfoModel {
    private String sourceClientId;
    private String targetClientId;
    private String roomId;
    private LocalDateTime timestamp;
}
