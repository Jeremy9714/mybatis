package com.jeremy.controller;

import com.jeremy.bean.Employee;
import com.jeremy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Chenyang
 * @create 2021-04-18-11:34
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getemps")
    public String emps(Map<String, Object> map) {
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmps", emps);
        return "list";
    }
}
