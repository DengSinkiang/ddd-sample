package com.sinkiang.ddd.sample.common.auth;

/**
 * @author dengxj
 * @date 2022/7/20 15:10
 */
public class AuthDataInfoContext {

    private static final ThreadLocal<AuthData> authDataThreadLocal = new ThreadLocal<>();

    public AuthDataInfoContext() {
    }

    public static void saveAuthData(AuthData authData) {
        authDataThreadLocal.set(authData);
    }

    public static void removeAll() {
        authDataThreadLocal.remove();
    }

    public static AuthData getAuthData() {
        return (AuthData)authDataThreadLocal.get();
    }
}
