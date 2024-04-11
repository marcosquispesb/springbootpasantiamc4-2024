package com.example.springbootdemo.utils;

/**
 * DBUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class DBUtil {

    public static boolean isValidId(Long id) {
        return id != null && id > 0;
    }

    public static boolean isValidId(IGetID iGetID) {
        if (iGetID == null)
            return false;

        return iGetID.getId() != null && iGetID.getId() > 0;
    }
}
