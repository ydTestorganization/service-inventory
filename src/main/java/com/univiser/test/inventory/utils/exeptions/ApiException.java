package com.univiser.test.inventory.utils.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiException extends Exception{

     private int code;
     private String message;
     private HttpStatus statusCode;


}
