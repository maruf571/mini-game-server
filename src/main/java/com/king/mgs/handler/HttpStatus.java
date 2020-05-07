package com.king.mgs.handler;

public enum  HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request."),
    UNAUTHORIZED(401, "Unauthorized."),
    FORBIDDEN(403, "Forbidden."),
    NOT_FOUND(404, "Not Found."),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed."),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error."),
    NOT_IMPLEMENTED(501, "Not Implemented.")
    ;

    int code;
    String message;
    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
