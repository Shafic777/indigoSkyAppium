package com.indigoSky.app.dataPojo;

import java.util.HashMap;
import java.util.Map;

public class loginData
{
    private String loginID;
    private String password;

    public loginData() {}

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  /*  public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/
    public String toString() {
        return "[loginID: " + getLoginID() + ", pass: "
                + getPassword()+ "]";
    }

}