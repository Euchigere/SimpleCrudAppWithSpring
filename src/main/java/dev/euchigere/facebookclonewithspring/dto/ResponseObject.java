package dev.euchigere.facebookclonewithspring.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseObject<T> {
    private String message;
    private boolean status;
    private T object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
