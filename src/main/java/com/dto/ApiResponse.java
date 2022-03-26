package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private File file;
    private Object object;

    public ApiResponse(String message, boolean success,Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public ApiResponse(final String message, final boolean success, final File file) {
        this.message = message;
        this.success = success;
        this.file = file;
    }

    public ApiResponse(final String message, final boolean success) {
        this.message = message;
        this.success = success;
    }
}
