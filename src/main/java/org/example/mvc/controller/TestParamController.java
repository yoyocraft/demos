package org.example.mvc.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yoyocraft
 * @date 2024/10/11
 */
@RestController
@RequestMapping
public class TestParamController {

    @RequestMapping(path = "/parse1", method = RequestMethod.GET)
    public String parse1(@RequestParam("name") String name, @RequestParam("age") int age) {
        return name + " is " + age + " years old";
    }

    @RequestMapping(path = "/parse2", method = RequestMethod.GET)
    public String parse2(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return "date is " + date;
    }
}
