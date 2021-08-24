package com.spring.app.model.common.chat;

import com.spring.app.model.common.ApiRequestModel;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessagePayloadRequestModel extends ApiRequestModel {
    private String roomId;
    private String sourceClientId;
    private String targetClientId;
    private String timestamp;
    private String message;
    
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {
        private String sourceClientId;
        private LocalDateTime timestamp;
        private String message;
    }
}
