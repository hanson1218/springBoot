package com.lhs.www.utils;

import javax.servlet.http.HttpServletRequest;

public class NetHelper {

    /**
     * 获取Ip地址，
     * 先取X-Real-IP地址，如果为空，
     * 取X-Forwarded-For地址，如果为空，
     * 取Proxy-Client-IP，如果为空，再取
     * WL-Proxy-Client-IP，如果为空，再取request.getRemoteAddr()
     * 注意：如果以上任何一种方式获取到的ip中有, 逗号分隔出的多个IP地址，则取第一个合法的IP地址（非unknown的地址)
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request.getHeader("X-Real-IP") != null) {
            return request.getHeader("X-Real-IP");
        }
        if(request.getHeader("X-Forwarded-For") != null) {
            return request.getHeader("X-Forwarded-For");
        }
        String ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || "".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || "".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && !"".equals(ip)) {
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                if (ips[i] != null && !"".equals(ips[i])
                        && !"unknown".equalsIgnoreCase(ips[i])) {
                    ip = ips[i];
                    break;
                }

            }
        }
        return ip;
    }
}
