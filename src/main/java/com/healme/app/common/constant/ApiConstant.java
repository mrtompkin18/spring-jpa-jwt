package com.healme.app.common.constant;

public class ApiConstant {
    public static final String TOKEN_BEARER_TYPE = "Bearer";
    
    public interface SYMBOL {
        String EMPTY = "";
    }

    public interface CLAIM_KEY {
        String USER_ID = "userId";
        String PERMISSION_CODE = "permissionsCode";
    }

    public enum FLAG {
        A, U
    }
}
