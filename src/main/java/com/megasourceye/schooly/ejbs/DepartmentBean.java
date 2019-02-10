/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Department;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class DepartmentBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Department obj) {
        dao.create(obj);
    }

    public List<Department> findAll() {
        return dao.findAll("Department.findAll", Department.class);
    }

    public List<Department> findBySchoolId(Long id) {
        return dao.findAll("Department.findBySchoolId", Department.class, id);
    }

    public Department find(Long id) {
        return dao.find(id, Department.class);
    }

    public void update(Department obj) {
        dao.update(obj);
    }

    public void delete(Department obj) {
        dao.delete(obj);
    }

}
