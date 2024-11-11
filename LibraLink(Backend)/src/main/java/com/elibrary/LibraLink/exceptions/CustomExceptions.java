package com.elibrary.LibraLink.exceptions;

public class CustomExceptions {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message){
            super(message);
        }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message){
            super(message);
        }
    }

    public static class UnAuthorizedException extends RuntimeException {
        public UnAuthorizedException(String message){
            super(message);
        }
    }

    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message){
            super(message);
        }
    }

    public static class Exception extends RuntimeException {
        public Exception(String message){
            super(message);
        }
    }

}
