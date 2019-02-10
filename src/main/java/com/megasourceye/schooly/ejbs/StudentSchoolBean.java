/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.StudentSchool;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StudentSchoolBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(StudentSchool obj) {
        dao.create(obj);
    }

    public List<StudentSchool> findAll() {
        return dao.findAll("StudentSchool.findAll", StudentSchool.class);
    }

    public List<StudentSchool> findBySchoolYear(Long id) {
        return dao.findAll("StudentSchool.findBySchoolYear", StudentSchool.class, id);
    }
    public List<StudentSchool> findBySchoolYearAndClassId(Long id,Long id1) {
        return dao.findAll("StudentSchool.findBySchoolYearAndClassId", StudentSchool.class, id,id1);
    }
    public List<StudentSchool> findBySchoolYearAndStudentId(Long id,Long id1) {
        return dao.findAll("StudentSchool.findBySchoolYearAndStudentId", StudentSchool.class, id,id1);
    }
    public List<StudentSchool> findBySchoolYearAndParentId(Long id,Long id1) {
        return dao.findAll("StudentSchool.findBySchoolYearAndParentId", StudentSchool.class, id,id1);
    }
    
    

    public StudentSchool find(Long id) {
        return dao.find(id, StudentSchool.class);
    }

    public void update(StudentSchool obj) {
        dao.update(obj);
    }

    public void delete(StudentSchool obj) {
        dao.delete(obj);
    }

}
