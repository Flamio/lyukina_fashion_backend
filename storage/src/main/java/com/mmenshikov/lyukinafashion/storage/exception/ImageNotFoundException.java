package com.mmenshikov.lyukinafashion.storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException  extends RuntimeException {
    public ImageNotFoundException(String message) {
        super("image "+message+" not found");
    }
}