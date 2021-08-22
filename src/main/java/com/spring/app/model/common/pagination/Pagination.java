package com.spring.app.model.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> implements Serializable {
    private List<T> list = new ArrayList<>();
    private Integer filtered;
    private Integer total;
}
