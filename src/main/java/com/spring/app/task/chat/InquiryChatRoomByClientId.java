package com.spring.app.task.chat;

import com.spring.app.common.database.storage.ChatRoomStorageManager;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import com.spring.app.model.common.chat.ChatRoomInfoModel;
import com.spring.app.model.common.task.AbsGenericTask;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InquiryChatRoomByClientId extends AbsGenericTask<ChatMessagePayloadRequestModel, ApiResponseModel<List<ChatRoomInfoModel>>> {

    @Override
    protected ApiResponseModel<List<ChatRoomInfoModel>> processTask(ChatMessagePayloadRequestModel request) throws ApiException {
        String sourceClientId = request.getSourceClientId();
        List<ChatRoomInfoModel> chatRoomList = ChatRoomStorageManager.find(sourceClientId);
        return new ApiResponseModel<>(chatRoomList);
    }
}
