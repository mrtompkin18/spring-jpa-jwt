package com.spring.app.model.common.pagination;

import lombok.Data;

@Data
public class PageRequestModel {
    private Integer page;
    private Integer size;

    public PageRequestModel() {
        this.page = 1;
        this.size = 10;
    }

    public Integer getPage() {
        return this.page > 0 ? this.page - 1 : this.page;
    }
}
