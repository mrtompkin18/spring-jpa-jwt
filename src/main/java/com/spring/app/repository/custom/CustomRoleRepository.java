package com.spring.app.repository.custom;

import com.spring.app.common.database.AbsDatabase;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.pagination.PageRequestModel;
import com.spring.app.model.common.pagination.PaginationModel;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.util.DatabaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CustomRoleRepository extends AbsDatabase {

    public PaginationModel<Role> inquiryRole(InquiryRoleRequestModel criteria) throws ApiException {
        StringBuilder SQLStatement = new StringBuilder(" select * from roles where 1=1 ");
        StringBuilder SQLCountStatement = new StringBuilder(" select count(1) from roles where 1=1 ");
        StringBuilder SQLWhereCause = new StringBuilder();

        if (StringUtils.isNotEmpty(criteria.getRoleName())) {
            SQLWhereCause.append(" and name like :name ");
        }

        if (StringUtils.isNotEmpty(criteria.getFlag())) {
            SQLWhereCause.append(" and flag = :flag ");
        }

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", DatabaseUtils.toLikeContains(criteria.getRoleName()))
                .addValue("flag", criteria.getFlag());

        PageRequestModel page = criteria.getPaging();
        String SQL = this.toSQLString(SQLStatement, SQLWhereCause);
        String SQLCount = this.toSQLString(SQLCountStatement, SQLWhereCause);

        return this.queryForListPagination(SQL, SQLCount, sqlParameterSource, page, Role.class);
    }
}
