package in.ara.auth.auth_app_backend.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status, int statusCode){}
