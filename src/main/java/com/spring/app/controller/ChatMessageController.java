package com.spring.app.controller;

import com.spring.app.common.database.storage.ChatRoomStorageManager;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.chat.ChatMessageInfoModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import com.spring.app.model.common.chat.ChatRoomInfoModel;
import com.spring.app.task.chat.CreateChatMessageTask;
import com.spring.app.task.chat.InquiryChatMessageRoomTask;
import com.spring.app.task.chat.InquiryChatRoomByClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ChatMessageController {

    @Autowired
    private CreateChatMessageTask createChatMessageTask;

    @Autowired
    private InquiryChatRoomByClientId inquiryChatRoomByClientId;

    @Autowired
    private InquiryChatMessageRoomTask inquiryChatMessageRoomTask;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PutMapping("/chat/message")
    public ApiResponseModel<ChatMessageInfoModel> createChatMessage(@RequestBody ChatMessagePayloadRequestModel request) throws ApiException {
        return this.createChatMessageTask.executeTask(request);
    }

    @PostMapping("/chat/message/room")
    public ApiResponseModel<List<ChatMessageInfoModel>> getChatByRoomId(@RequestBody ChatMessagePayloadRequestModel request) throws ApiException {
        return this.inquiryChatMessageRoomTask.executeTask(request);
    }

    @GetMapping("/chat/message/room/{clientId}")
    public ApiResponseModel<List<ChatRoomInfoModel>> inquiryChatRoomByClientId(@PathVariable("clientId") String clientId) throws ApiException {
        ChatMessagePayloadRequestModel request = ChatMessagePayloadRequestModel.builder().sourceClientId(clientId).build();
        return this.inquiryChatRoomByClientId.executeTask(request);
    }
}
