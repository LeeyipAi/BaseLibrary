package jmm.baselibrary.data.net;


import java.util.HashMap;
import java.util.Map;

import jmm.baselibrary.utils.StringUtils;
import jmm.baselibrary.utils.TimeUtils;

public class HttpParams {

    private static final String KEY = "jmm1441658802";

    private static Map<String, String> getMap() {
        return new HashMap<>();
    }

    public static Map<String, String> signature(Map<String, String> params) {
        String timestamp = TimeUtils.getTimestamp();
        String base64 = StringUtils.getBase64(StringUtils.getJson(params));
        params.put("scret", StringUtils.getSignature(KEY, timestamp, base64));
        params.put("context", base64);
        params.put("time", timestamp);
        return params;
    }

    /**
     * 获取积分列表
     */
    public static Map<String, String> getJf(String userId, String pageNo, String pageSize) {
        Map<String, String> params = getMap();
        params.put("userId", userId);
        params.put("pageSize", pageSize);
        params.put("pageNo", pageNo);
        return signature(params);
    }

}
