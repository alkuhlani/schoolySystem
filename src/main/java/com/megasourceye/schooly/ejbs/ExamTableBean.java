/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Exam;
import com.megasourceye.schooly.entities.ExamTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class ExamTableBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(ExamTable obj) {
        dao.create(obj);
    }

    public List<ExamTable> findAll() {
        return dao.findAll("ExamTable.findAll", ExamTable.class);
    }

    public List<ExamTable> findBySchoolYearId(Long id) {
        return dao.findAll("ExamTable.findBySchoolYearId", ExamTable.class, id);
    }
    public List<ExamTable> findBySchoolYearIdAndSubjectIdAndClassId(Long schoolYearId,Long subjectId,Long classId) {
        return dao.findAll("ExamTable.findBySchoolYearIdAndSubjectIdAndClassId", ExamTable.class, schoolYearId,subjectId,classId);
    }

    public ExamTable find(Long id) {
        return dao.find(id, ExamTable.class);
    }

    public void update(ExamTable obj) {
        dao.update(obj);
    }

    public void delete(ExamTable obj) {
        dao.delete(obj);
    }
}
