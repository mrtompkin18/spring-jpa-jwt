package com.spring.app.service;

import com.spring.app.common.config.WebSocketConfig;
import com.spring.app.common.constant.ApiConstant;
import com.spring.app.common.database.storage.ChatMessageStorageManager;
import com.spring.app.common.database.storage.ChatRoomStorageManager;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.chat.ChatMessageInfoModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import com.spring.app.model.common.chat.ChatRoomInfoModel;
import com.spring.app.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ChatMessageService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    public ChatMessageInfoModel message(ChatMessagePayloadRequestModel request) throws ApiException {
        String sourceClientId = request.getSourceClientId();
        String targetClientId = request.getTargetClientId();
        String roomId = request.getRoomId();
        String message = request.getMessage();

        ChatRoomInfoModel chatRoomInfo;
        Optional<ChatRoomInfoModel> exists = ChatRoomStorageManager.find(roomId, sourceClientId, targetClientId);
        if (!exists.isPresent()) {
            chatRoomInfo = ChatRoomStorageManager.add(sourceClientId, targetClientId);
            roomId = chatRoomInfo.getRoomId();
        }

        ChatMessagePayloadRequestModel.Payload payload = new ChatMessagePayloadRequestModel.Payload();
        LocalDateTime timestamp = DateUtils.toLocalDateTime(request.getTimestamp(), ApiConstant.DATE_PATTERN.yyyy_MM_dd_HHmmss);
        payload.setMessage(message);
        payload.setSourceClientId(sourceClientId);
        payload.setTimestamp(timestamp);

        ChatMessageInfoModel messageInfo = ChatMessageStorageManager.add(payload, roomId);

        this.broadcastMsgInRooms(messageInfo, roomId);
        this.broadcastNewRooms(sourceClientId, targetClientId);

        return messageInfo;
    }

    private void broadcastMsgInRooms(ChatMessageInfoModel messageInfo, String roomId) {
        this.simpMessagingTemplate.convertAndSend(WebSocketConfig.TOPIC_BROADCAST_NEW_MESSAGE_IN_ROOM + "/" + roomId, messageInfo);
        log.info("send message : {}", messageInfo);
    }

    private void broadcastNewRooms(String sourceClientId, String targetClientId) {
        List<ChatRoomInfoModel> rooms = ChatRoomStorageManager.find(sourceClientId);
        this.simpMessagingTemplate.convertAndSend(WebSocketConfig.TOPIC_BROADCAST_NEW_ROOM_LIST + "/" + sourceClientId, rooms);
        this.simpMessagingTemplate.convertAndSend(WebSocketConfig.TOPIC_BROADCAST_NEW_ROOM_LIST + "/" + targetClientId, rooms);
    }
}
