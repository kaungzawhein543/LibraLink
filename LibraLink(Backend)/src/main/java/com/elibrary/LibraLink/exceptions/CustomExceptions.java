package com.elibrary.LibraLink.exceptions;

public class CustomExceptions {

    /* EXCEPTIONS */

    // RESOURCE NOT FOUND EXCEPTION
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message){
            super(message);
        }
    }

    // BAD REQUEST EXCEPTION
    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message){
            super(message);
        }
    }

    // UNAUTHORIZED EXCEPTION
    public static class UnAuthorizedException extends RuntimeException {
        public UnAuthorizedException(String message){
            super(message);
        }
    }

    // FORBIDDEN EXCEPTION
    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message){
            super(message);
        }
    }

    // EXCEPTION
    public static class Exception extends RuntimeException {
        public Exception(String message){
            super(message);
        }
    }

}
