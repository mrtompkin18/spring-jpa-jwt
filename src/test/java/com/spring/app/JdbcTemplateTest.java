package com.spring.app;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.pagination.PageRequestModel;
import com.spring.app.model.common.pagination.PageResponseModel;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.custom.CustomRoleRepository;
import com.spring.app.repository.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    private CustomRoleRepository customRoleRepository;

    @Test
    public void testQueryRole() throws ApiException {
        InquiryRoleRequestModel request = new InquiryRoleRequestModel();
        request.setPaging(new PageRequestModel(1, 1));

        PageResponseModel<Role> pageResponseModel = this.customRoleRepository.inquiryRole(request);
        System.out.println(pageResponseModel);
    }
}
