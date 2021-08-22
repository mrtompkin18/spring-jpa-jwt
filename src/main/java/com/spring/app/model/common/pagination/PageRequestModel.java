package com.spring.app.model.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequestModel {
    private Integer page;
    private Integer size;

    public PageRequestModel() {
        this.page = 0;
        this.size = 10;
    }
}
