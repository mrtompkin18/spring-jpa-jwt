package com.spring.app.task.chat;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.chat.ChatMessageInfoModel;
import com.spring.app.model.common.chat.ChatMessagePayloadRequestModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateChatMessageTask extends AbsGenericTask<ChatMessagePayloadRequestModel, ApiResponseModel<ChatMessageInfoModel>> {

    @Autowired
    private ChatMessageService chatMessageService;

    @Override
    protected ApiResponseModel<ChatMessageInfoModel> processTask(ChatMessagePayloadRequestModel request) throws ApiException {
        ChatMessageInfoModel chatMessageInfo = this.chatMessageService.message(request);
        return new ApiResponseModel<>(chatMessageInfo);
    }
}
