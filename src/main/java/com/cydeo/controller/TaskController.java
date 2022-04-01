package com.cydeo.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/create")
    public String createTask(Model model){


        return "task/create";
    }
}
