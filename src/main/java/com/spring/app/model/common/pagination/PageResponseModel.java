package com.spring.app.model.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseModel<T> implements Serializable {
    private List<T> list;
    private Integer page;
    private Integer size;
    private Integer total;

    public Integer getPage() {
        return this.page + 1;
    }
}
