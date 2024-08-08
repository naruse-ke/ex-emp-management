package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    /**
     * 従業員情報を出力する.
     * 
     * @param id 従業員ID
     * @param model Model
     * @param form UpdateEmployeeForm
     * @return 従業員情報画面
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        Employee employee = employeeService.showDetail(Integer.parseInt(id));

        BeanUtils.copyProperties(employee, form);
        form.setId(employee.getId().toString());
        form.setHireDate(employee.getHireDate().toString());
        form.setSalary(employee.getSalary().toString());
        form.setDependentsCount(employee.getDependentsCount().toString());

        model.addAttribute("updateEmployeeForm", form);
        return "employee/detail.html";
    }

    /**
     * 従業員詳細を更新する.
     * 
     * <pre>
     * 入力チェックエラーの場合は従業員情報画面を返却します。
     * </pre>
     * 
     * @param form UpdateEmployeeForm
     * @param br BindingResult
     * @param model Model
     * @return 従業員⼀覧画面へリダイレクト
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form, BindingResult br, Model model) {

        // 入力チェック
        if (br.hasErrors()) {
            return "employee/detail.html";
        }

        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }
}
