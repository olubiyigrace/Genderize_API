package com.hng.task.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "message", "data"})
public class ApiResponse<T> {
    private final String status;
    private final String message;
    private final T data;

    private ApiResponse(String status, String message, T data) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <T> ApiResponse<T> success(T data){


        return new ApiResponse<>("success", null, data);
    }

    public static <T> ApiResponse<T> success(String message, T data){


        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> failure(String message){


        return new ApiResponse<>("error", message, null);
    }

}
