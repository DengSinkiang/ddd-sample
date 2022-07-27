package com.sinkiang.ddd.sample.common.auth;

/**
 * @author dengxj
 * @date 2022/7/20 15:10
 */
public class AuthDataInfoContext {

    private static final ThreadLocal<AuthData> AUTH_DATA_THREAD_LOCAL = new ThreadLocal<>();

    public AuthDataInfoContext() {
    }

    public static void saveAuthData(AuthData authData) {
        AUTH_DATA_THREAD_LOCAL.set(authData);
    }

    public static void removeAll() {
        AUTH_DATA_THREAD_LOCAL.remove();
    }

    public static AuthData getAuthData() {
        return AUTH_DATA_THREAD_LOCAL.get();
    }
}
