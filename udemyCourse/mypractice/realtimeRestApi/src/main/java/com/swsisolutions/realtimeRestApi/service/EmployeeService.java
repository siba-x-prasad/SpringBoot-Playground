package com.swsisolutions.realtimeRestApi.service;


import com.swsisolutions.realtimeRestApi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findEmployee(int id);
    void deleteEmployee(int id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);

}
