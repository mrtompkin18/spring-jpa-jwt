package com.spring.app.util;

import com.spring.app.common.constant.ApiConstant;
import com.spring.app.model.common.pagination.PaginationModel;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@UtilityClass
public class DatabaseUtils {

    public <T> BeanPropertyRowMapper<T> getRowMapper(Class<T> mappedClass) {
        return new BeanPropertyRowMapper<>(mappedClass);
    }

    public String toLikeContains(String value) {
        return ApiConstant.SYMBOL.PERCENTAGE + value + ApiConstant.SYMBOL.PERCENTAGE;
    }

    public <T> PaginationModel<T> getPagination(List<T> list, int totalRecord) {
        PaginationModel<T> pageResponse = new PaginationModel<>();
        pageResponse.setTotal(totalRecord);
        pageResponse.setList(list);
        pageResponse.setFiltered(CollectionUtils.size(list));
        return pageResponse;
    }
}
