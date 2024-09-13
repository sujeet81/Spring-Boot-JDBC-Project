package com.springboot_jdbc.Service;

import com.springboot_jdbc.Repository.EmployeeRepository;
import com.springboot_jdbc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public String saveEmployee(Employee employee){
        int count = repository.save(employee);
        return "Record Inserted" + count;
    }


    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public List<Employee> getEmployeesUsingBeanPropertyRowMapper(){
        return repository.getAllEmployees();
    }

    public Employee getEmployeeById(int id){
        return repository.findById(id);
    }

    public String getNameById(int id){
        return repository.getNameById(id);
    }

    public List<Employee> findEmployeesByNameAndDept(String name, String dept){
        return repository.findByNameAndDept(name,dept);
    }

    public String updateEmployee(Employee employee){
        int count =  repository.update(employee);
        return count + "Record Updated !";
    }

    public String deleteEmployee(int id){
        int count  = repository.delete(id);
        return count + "Record Deleted !";
    }

}
