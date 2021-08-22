package com.spring.app.common.database;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.pagination.PageRequestModel;
import com.spring.app.model.common.pagination.Pagination;
import com.spring.app.util.DatabaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public abstract class AbsDatabase {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    protected <T> T queryForObjectNoRowMapper(String sql, SqlParameterSource sps, Class<T> mappedClass) throws ApiException {
        T result;
        try {
            log.info("sql: {}", sql);
            result = this.jdbcTemplate.queryForObject(sql, sps, mappedClass);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    protected <T> T queryForObject(String sql, SqlParameterSource sps, Class<T> mappedClass) throws ApiException {
        T result;
        try {
            log.info("sql: {}", sql);
            result = this.jdbcTemplate.queryForObject(sql, sps, DatabaseUtils.getRowMapper(mappedClass));
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    protected <T> List<T> queryForList(String sql, Class<T> mappedClass) throws ApiException {
        List<T> result;
        try {
            log.info("sql: {}", sql);
            result = this.jdbcTemplate.query(sql, DatabaseUtils.getRowMapper(mappedClass));
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    protected <T> List<T> queryForList(String sql, SqlParameterSource sps, Class<T> mappedClass) throws ApiException {
        List<T> result;
        try {
            log.info("sql: {}", sql);
            result = this.jdbcTemplate.query(sql, sps, DatabaseUtils.getRowMapper(mappedClass));
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    protected <T> Pagination<T> queryForListPagination(String SQLStatement, String SQLCountStatement, MapSqlParameterSource sps, PageRequestModel page, Class<T> mappedClass) throws ApiException {
        List<T> list = this.queryForPagination(SQLStatement, sps, mappedClass, page);
        Integer total = this.queryForCount(SQLCountStatement, sps);
        return DatabaseUtils.getPagination(list, total);
    }

    protected Integer queryForCount(String fullSQLStatement, SqlParameterSource sps) throws ApiException {
        try {
            return this.queryForObjectNoRowMapper(fullSQLStatement, sps, Integer.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    protected String toSQLString(StringBuilder... sql) {
        return Arrays.stream(sql).map(StringBuilder::toString).collect(Collectors.joining());
    }

    private <T> List<T> queryForPagination(String SQLStatement, MapSqlParameterSource sps, Class<T> mappedClass, PageRequestModel page) throws ApiException {
        if (Objects.nonNull(page)) {
            int size = page.getSize();
            int offset = page.getPage() * size;
            SQLStatement += String.format(" limit %d, %d", offset, size);
        }

        return this.queryForList(SQLStatement, sps, mappedClass);
    }
}
