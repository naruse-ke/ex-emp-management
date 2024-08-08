package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 従業員情報を検索する処理を記述する.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員⼀覧を出⼒する.
     * 
     * @param model Model
     * @return 従業員⼀覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        model.addAttribute("employeeList", employeeService.showList());
        return "employee/list.html";
    }

}
