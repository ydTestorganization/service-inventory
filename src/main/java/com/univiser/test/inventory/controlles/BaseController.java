package com.univiser.test.inventory.controlles;

import org.springframework.http.ResponseEntity;

public class BaseController {


       public <T>ResponseEntity<T> response(T t) {
        return ResponseEntity.ok(t);
    }
}
