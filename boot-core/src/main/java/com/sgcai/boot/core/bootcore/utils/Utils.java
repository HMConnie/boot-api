package com.sgcai.boot.core.bootcore.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

public final class Utils {
    /**
     * ID生成器
     *
     * @return
     */
    public static String generateUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 订单No生成器
     *
     * @return
     */
    public static String generateOrderNo() {
        StringBuilder sb = new StringBuilder();
        String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        sb.append(date);
        for (int i = 0; i < 6; i++) {
            sb.append(RandomUtils.nextInt(10));
        }
        return sb.toString();
    }
}
