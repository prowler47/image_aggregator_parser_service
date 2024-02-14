package ua.dragunovskiy;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    public Message(String message) {
        this.message = message;
    }
}
