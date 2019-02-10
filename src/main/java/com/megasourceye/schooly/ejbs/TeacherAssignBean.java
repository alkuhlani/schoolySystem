/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Teacher;
import com.megasourceye.schooly.entities.TeacherAssign;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TeacherAssignBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(TeacherAssign obj) {
        dao.create(obj);
    }

    public List<TeacherAssign> findAll() {
        return dao.findAll("TeacherAssign.findAll", TeacherAssign.class);
    }

    public List<TeacherAssign> findBySchoolYearId(Long id) {
        return dao.findAll("TeacherAssign.findBySchoolYearId", TeacherAssign.class, id);
    }
    public List<TeacherAssign> findBySchoolYearIdAndTeacherId(Long id,Long id2) {
        return dao.findAll("TeacherAssign.findBySchoolYearIdAndTeacherId", TeacherAssign.class, id,id2);
    }
    public List<TeacherAssign> findBySchoolYearIdAndClassId(Long id,Long id2) {
        return dao.findAll("TeacherAssign.findBySchoolYearIdAndClassId", TeacherAssign.class, id,id2);
    }
    public List<TeacherAssign> findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId(Long id,Long id2,Long id3,Long id4) {
        return dao.findAll("TeacherAssign.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId", TeacherAssign.class, id,id2,id3,id4);
    }
    public List<TeacherAssign> findBySchoolYearIdAndClassIdAndSubjectId(Long id,Long id2,Long id3) {
        return dao.findAll("TeacherAssign.findBySchoolYearIdAndClassIdAndSubjectId", TeacherAssign.class, id,id2,id3);
    }

    public TeacherAssign find(Long id) {
        return dao.find(id, TeacherAssign.class);
    }

    public void update(TeacherAssign obj) {
        dao.update(obj);
    }

    public void delete(TeacherAssign obj) {
        dao.delete(obj);
    }

}
