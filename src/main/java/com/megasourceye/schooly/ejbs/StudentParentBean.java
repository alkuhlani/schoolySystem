/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.StudentParent;
import com.megasourceye.schooly.entities.StudentSchool;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StudentParentBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
@Inject
    private DataAccessObject dao;

    public void create(StudentParent obj) {
        dao.create(obj);
    }

    public List<StudentParent> findAll() {
        return dao.findAll("StudentParent.findAll", StudentParent.class);
    }

    public List<StudentParent> findBySchoolId(Long id) {
        return dao.findAll("StudentParent.findBySchoolId", StudentParent.class, id);
    }
    public List<StudentParent> findByStudentId(Long id) {
        return dao.findAll("StudentParent.findByStudentId", StudentParent.class, id);
    }
    public List<StudentParent> findByParentId(Long id) {
        return dao.findAll("StudentParent.findByParentId", StudentParent.class, id);
    }

    public StudentParent find(Long id) {
        return dao.find(id, StudentParent.class);
    }

    public void update(StudentParent obj) {
        dao.update(obj);
    }

    public void delete(StudentParent obj) {
        dao.delete(obj);
    }

}
