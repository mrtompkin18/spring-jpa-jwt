package com.healme.app.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {
    public boolean isCause(Class<? extends Throwable> expected, Throwable exc) {
        return expected.isInstance(exc) || (exc != null && isCause(expected, exc.getCause()));
    }
}
