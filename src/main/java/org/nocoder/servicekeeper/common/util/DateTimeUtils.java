package org.nocoder.servicekeeper.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date time utils
 *
 * @author jason
 * @date 2019/4/19.
 */
public class DateTimeUtils {
    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
