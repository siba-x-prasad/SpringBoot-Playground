package com.swsisolutions.realtimeRestApi.dao;

import com.swsisolutions.realtimeRestApi.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findEmployee(int id);
    void deleteEmployee(int id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
}
