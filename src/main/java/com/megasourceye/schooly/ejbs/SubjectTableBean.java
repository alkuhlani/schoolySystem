/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SubjectTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SubjectTableBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(SubjectTable obj) {
        dao.create(obj);
    }

    public List<SubjectTable> findAll() {
        return dao.findAll("SubjectTable.findAll", SubjectTable.class);
    }

    public List<SubjectTable> findBySchoolId(Long id) {
        return dao.findAll("SubjectTable.findBySchoolId", SubjectTable.class, id);
    }
    
    public List<SubjectTable> findBySchoolYearId(Long id) {
        return dao.findAll("SubjectTable.findBySchoolYearId", SubjectTable.class, id);
    }
    public List<SubjectTable> findBySchoolYearIdAndTeacherId(Long id,Long id2) {
        return dao.findAll("SubjectTable.findBySchoolYearIdAndTeacherId", SubjectTable.class, id,id2);
    }
    public List<SubjectTable> findBySchoolYearIdAndClassIdAndTeacherId(Long id,Long id2) {
        return dao.findAll("SubjectTable.findBySchoolYearIdAndClassIdAndTeacherId", SubjectTable.class, id,id2);
    }
    
    public List<SubjectTable> findBySchoolYearIdAndClassId(Long id,Long id2) {
        return dao.findAll("SubjectTable.findBySchoolYearIdAndClassId", SubjectTable.class, id,id2);
    }
    public List<SubjectTable> findBySchoolYearIdAndTeacherAIdAndDayIdAndLectureNo(Long id,Long id1,Long id2,Character id3) {
        return dao.findAll("SubjectTable.findBySchoolYearIdAndTeacherAIdAndDayIdAndLectureNo", SubjectTable.class, id,id1,id2,id3);
    }
    public List<SubjectTable> findBySchoolYearIdAndSubjectIdAndClassId(Long id,Long id1,Long id2) {
        return dao.findAll("SubjectTable.findBySchoolYearIdAndSubjectIdAndClassId", SubjectTable.class, id,id1,id2);
    }
        
    public SubjectTable find(Long id) {
        return dao.find(id, SubjectTable.class);
    }

    public void update(SubjectTable obj) {
        dao.update(obj);
    }

    public void delete(SubjectTable obj) {
        dao.delete(obj);
    }

}
