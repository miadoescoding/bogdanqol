package com.bogdan.qol.utils;

import java.util.Date;

public class TimeUtils {
    public static long curTime() {
        return (new Date()).getTime();
    }
}
