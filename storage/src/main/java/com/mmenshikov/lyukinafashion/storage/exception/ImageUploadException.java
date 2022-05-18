package com.mmenshikov.lyukinafashion.storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageUploadException extends RuntimeException{
    public ImageUploadException(Throwable throwable) {
        super("image failed to upload, cause: " + throwable.getMessage());
    }
}
