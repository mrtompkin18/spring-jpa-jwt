package com.healme.app.common.constant;

public class ApiConstant {
    public static final String TOKEN_BEARER_TYPE = "Bearer";
    public static final String SUCCESS = "SUCCESS";
    public static final String PRINCIPAL_KEY = "principal";

    public interface SYMBOL {
        String EMPTY = "";
    }

    public interface CLAIM_KEY {
        String USER_ID = "userId";
        String ROLE_ID = "roleId";
        String PERMISSION_CODE = "permissionsCode";
    }

    public enum FLAG {
        A, U
    }
}
