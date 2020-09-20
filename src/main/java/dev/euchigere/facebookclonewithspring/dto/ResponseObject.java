package dev.euchigere.facebookclonewithspring.dto;

import org.springframework.stereotype.Component;

/**
 * ResponseObject
 * a data transfer generic object to transfer object and messages between application layers
 * employs generic object type to ensure type safety
 *
 * message variable stores and message to be transmitted
 * status variable indicates whether the operation was successful or not
 * object variable holds the object value to be transferred
 * @param <T>
 */
@Component
public class ResponseObject<T> {
    private String message;
    private boolean status;
    private T object;

    // getters and setters
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
