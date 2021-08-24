package com.spring.app.task.chat;

import com.spring.app.common.database.storage.ChatMessageStorageManager;
import com.spring.app.common.database.storage.ChatRoomStorageManager;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.chat.ChatMessageInfoModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import com.spring.app.model.common.chat.ChatRoomInfoModel;
import com.spring.app.model.common.task.AbsGenericTask;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class InquiryChatMessageRoomTask extends AbsGenericTask<ChatMessagePayloadRequestModel, ApiResponseModel<List<ChatMessageInfoModel>>> {
    @Override
    protected ApiResponseModel<List<ChatMessageInfoModel>> processTask(ChatMessagePayloadRequestModel request) throws ApiException {
        Optional<ChatRoomInfoModel> exists = ChatRoomStorageManager.find(request.getRoomId(), request.getSourceClientId(), request.getTargetClientId());
        if (!exists.isPresent()) {
            return new ApiResponseModel<>(Collections.emptyList());
        }
        List<ChatMessageInfoModel> messages = ChatMessageStorageManager.findChatByRoomId(request.getRoomId());
        return new ApiResponseModel<>(messages);
    }
}
