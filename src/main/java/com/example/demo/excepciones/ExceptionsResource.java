package com.example.demo.excepciones;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(ExceptionsResource.EXCEPTIONS)
public class ExceptionsResource {

    public static final String EXCEPTIONS = "/exceptions";
    public static final String MY_FYILTER = "/my-fylter";
    public static final String OUT_OF_TIME = "/out-of-time";
    public static final String ID = "/{id}";
    public static final String ERROR = "/error";

    @GetMapping(value = MY_FYILTER)
    public String MyFilter() {
        return "{\"state\":\"my-filter\"}";
    }

    @GetMapping(value = OUT_OF_TIME)
    public String OutOfTime() {
        return "{\"state\":\"off\"}";
    }

}
