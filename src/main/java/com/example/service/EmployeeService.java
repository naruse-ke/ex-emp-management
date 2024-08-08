package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報についての業務処理を実装する.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得する.
     * 
     * @return List Employee 全ての従業員情報
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報を取得する.
     * 
     * @param id 従業員ID
     * @return Employee 従業員情報
     */
    public Employee showDetail(Integer id){
        return employeeRepository.load(id);
    }
}
