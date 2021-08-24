package com.spring.app.common.database.storage;

import com.spring.app.model.common.chat.ChatMessageInfoModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChatMessageStorageManager {

    private static List<ChatMessageInfoModel> messages = null;

    synchronized public static List<ChatMessageInfoModel> instance() {
        if (Objects.isNull(messages)) {
            messages = new ArrayList<>();
        }
        return messages;
    }

    synchronized public static ChatMessageInfoModel add(ChatMessagePayloadRequestModel.Payload payload, String roomId) {
        ChatMessageInfoModel messageInfoModel = ChatMessageInfoModel.builder()
                .ownerId(payload.getSourceClientId())
                .roomId(roomId)
                .message(payload.getMessage())
                .timestamp(payload.getTimestamp())
                .build();
        instance().add(messageInfoModel);

        return messageInfoModel;
    }

    synchronized public static List<ChatMessageInfoModel> findChatByRoomId(String roomId) {
        return instance().stream().filter(chat -> StringUtils.equals(chat.getRoomId(), roomId)).collect(Collectors.toList());
    }
}
