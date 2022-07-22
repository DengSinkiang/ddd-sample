package com.sinkiang.ddd.sample.common.auth;

/**
 * @author dengxj
 * @date 2022/7/20 15:10
 */
public class AuthData {
    private String username;
    private String accId;
    private String personNo;
    private String personName;
    private String permissionCode;

    public AuthData() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getAccId() {
        return this.accId;
    }

    public String getPersonNo() {
        return this.personNo;
    }

    public String getPersonName() {
        return this.personName;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

}
