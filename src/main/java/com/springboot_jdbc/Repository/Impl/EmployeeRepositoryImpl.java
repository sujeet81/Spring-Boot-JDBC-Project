package com.springboot_jdbc.Repository.Impl;

import com.springboot_jdbc.Repository.EmployeeRepository;
import com.springboot_jdbc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO EMPLOYEE(name,dept, email,doj) values(?,?,?,?)"
                , employee.getName(),employee.getDept(),employee.getEmail(),employee.getDoj());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE",
                (rs,  rowNum) ->
                Employee.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .dept(rs.getString("dept"))
                        .email(rs.getString("email"))
                        .doj(rs.getDate("doj"))
                        .build());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("Select * from Employee",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("Select * from Employee where id = ?",
                new BeanPropertyRowMapper<>(Employee.class),id);
    }

    @Override
    public String getNameById(int id) {
        return jdbcTemplate.queryForObject("Select name from Employee where id = ?",String.class,id);
    }

    @Override
    public List<Employee> findByNameAndDept(String name, String dept) {
        return jdbcTemplate.query("Select *  from Employee where name = ? and dept = ?",
                new BeanPropertyRowMapper<>(Employee.class),name,dept);
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("Update Employee Set name = ?, dept = ?, email = ?, doj = ? " +
                "where id = ?",employee.getName(),employee.getDept(),employee.getEmail(),employee.getDoj(),
                employee.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("Delete from Employee where id  = ?",id);
    }
}
