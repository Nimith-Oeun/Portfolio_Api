package com.personal.portfolio_api.exception;


public class MaxUploadSizeExceededException extends RuntimeException {

   public MaxUploadSizeExceededException(String message) {
      super(message);

   }

}