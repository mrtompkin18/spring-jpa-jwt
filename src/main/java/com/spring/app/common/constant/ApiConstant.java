package com.spring.app.common.constant;

public class ApiConstant {
    public static final String TOKEN_BEARER_TYPE = "Bearer";

    public interface SYMBOL {
        String EMPTY = "";
        String PERCENTAGE = "%";
    }

    public interface CLAIM_KEY {
        String USER_ID = "userId";
        String PERMISSION_CODE = "permissionsCode";
    }

    public enum FLAG {
        A, // active
        I  // inactive
    }

    public interface DATE_PATTERN {
        String yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    }
}
