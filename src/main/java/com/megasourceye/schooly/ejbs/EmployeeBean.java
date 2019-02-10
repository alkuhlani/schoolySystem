/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.DepartmentType;
import com.megasourceye.schooly.entities.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class EmployeeBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Employee obj) {
        dao.create(obj);
    }

    public List<Employee> findAll() {
        return dao.findAll("Employee.findAll", Employee.class);
    }

    public List<Employee> findBySchoolId(Long id) {
        return dao.findAll("Employee.findBySchoolId", Employee.class, id);
    }

    public Employee find(Long id) {
        return dao.find(id, Employee.class);
    }

    public void update(Employee obj) {
        dao.update(obj);
    }

    public void delete(Employee obj) {
        dao.delete(obj);
    }

}
