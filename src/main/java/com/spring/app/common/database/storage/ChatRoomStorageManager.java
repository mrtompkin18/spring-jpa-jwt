package com.spring.app.common.database.storage;

import com.spring.app.model.common.chat.ChatRoomInfoModel;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ChatRoomStorageManager {

    private static Set<ChatRoomInfoModel> clients = null;

    synchronized public static Set<ChatRoomInfoModel> instance() {
        if (Objects.isNull(clients)) {
            clients = new HashSet<>();
        }
        return clients;
    }

    synchronized public static List<ChatRoomInfoModel> find(String clientId) {
        return instance().stream()
                .filter(chat -> StringUtils.equals(chat.getSourceClientId(), clientId) || StringUtils.equals(chat.getTargetClientId(), clientId))
                .collect(Collectors.toList());
    }

    synchronized public static Optional<ChatRoomInfoModel> find(String roomId, String clientId, String targetClientId) {
        return instance().stream().filter(chat -> (StringUtils.equals(chat.getSourceClientId(), clientId)
                && StringUtils.equals(chat.getTargetClientId(), targetClientId)
                || StringUtils.equals(chat.getSourceClientId(), targetClientId) && StringUtils.equals(chat.getTargetClientId(), clientId))
                && StringUtils.equals(chat.getRoomId(), roomId))
                .findAny();
    }

    synchronized public static Optional<ChatRoomInfoModel> findByRoomId(String roomId) {
        return instance().stream().filter(chat -> StringUtils.equals(chat.getRoomId(), roomId)).findAny();
    }

    synchronized public static ChatRoomInfoModel add(String clientId, String targetClientId) {
        ChatRoomInfoModel chatRoomInfo = ChatRoomInfoModel.builder()
                .sourceClientId(clientId)
                .targetClientId(targetClientId)
                .roomId(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();

        instance().add(chatRoomInfo);

        return chatRoomInfo;
    }
}
