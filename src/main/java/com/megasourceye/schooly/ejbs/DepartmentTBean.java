/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Department;
import com.megasourceye.schooly.entities.DepartmentType;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class DepartmentTBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(DepartmentType obj) {
        dao.create(obj);
    }

    public List<DepartmentType> findAll() {
        return dao.findAll("DepartmentType.findAll", DepartmentType.class);
    }

    public List<DepartmentType> findBySchoolId(Long id) {
        return dao.findAll("DepartmentType.findBySchoolId", DepartmentType.class, id);
    }

    public DepartmentType find(Long id) {
        return dao.find(id, DepartmentType.class);
    }

    public void update(DepartmentType obj) {
        dao.update(obj);
    }

    public void delete(DepartmentType obj) {
        dao.delete(obj);
    }

}
