/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.Teacher;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TeacherBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Teacher obj) {
        dao.create(obj);
    }

    public List<Teacher> findAll() {
        return dao.findAll("Teacher.findAll", Teacher.class);
    }

    public List<Teacher> findBySchoolId(Long id) {
        return dao.findAll("Teacher.findBySchoolId", Teacher.class, id);
    }

    public Teacher find(Long id) {
        return dao.find(id, Teacher.class);
    }

    public void update(Teacher obj) {
        dao.update(obj);
    }

    public void delete(Teacher obj) {
        dao.delete(obj);
    }

}
