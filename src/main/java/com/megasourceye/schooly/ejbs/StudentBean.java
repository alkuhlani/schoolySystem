/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.Subject_t;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StudentBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Student obj) {
        dao.create(obj);
    }

    public List<Student> findAll() {
        return dao.findAll("Student.findAll", Student.class);
    }

    public List<Student> findBySchoolId(Long id) {
        return dao.findAll("Student.findBySchoolId", Student.class, id);
    }

    public Student find(Long id) {
        return dao.find(id, Student.class);
    }

    public void update(Student obj) {
        dao.update(obj);
    }

    public void delete(Student obj) {
        dao.delete(obj);
    }

}
