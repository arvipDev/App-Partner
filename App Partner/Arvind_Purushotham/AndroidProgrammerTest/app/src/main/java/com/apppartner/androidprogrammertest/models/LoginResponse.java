package com.apppartner.androidprogrammertest.models;

public class LoginResponse {
    private String message;
    private String code;
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof LoginResponse)) return false;

        LoginResponse that = (LoginResponse) obj;

        if (!message.equals(that.message)) return false;
        return code.equals(that.code) && duration == that.duration;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "code: '" + this.code + "',message: '" + this.message + "',duration: '" + this.duration + "'";
    }
}
