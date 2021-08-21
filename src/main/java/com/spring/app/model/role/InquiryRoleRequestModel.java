package com.spring.app.model.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.app.model.common.ApiRequestModel;
import com.spring.app.model.common.pagination.PageRequestModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InquiryRoleRequestModel extends ApiRequestModel {

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("flag")
    private String flag;

    private PageRequestModel paging;
}
