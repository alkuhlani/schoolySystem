/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.HomeWork;
import com.megasourceye.schooly.entities.NoteDaily;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class NoteDailyBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(NoteDaily obj) {
        dao.create(obj);
    }

    public List<NoteDaily> findAll() {
        return dao.findAll("NoteDaily.findAll", NoteDaily.class);
    }

    public List<NoteDaily> findBySchoolYearAndDate(Long id, Date id1) {
        return dao.findAll("NoteDaily.findBySchoolYearAndDate", NoteDaily.class, id, id1);
    }

    public List<NoteDaily> findBySchoolYearAndTeahcerId(Long id, Long id1) {
        return dao.findAll("NoteDaily.findBySchoolYearAndTeahcerId", NoteDaily.class, id, id1);
    }

    public List<NoteDaily> findBySchoolYearAndTeahcerIdAndDate(Long id, Long id1, Date id2) {
        return dao.findAll("NoteDaily.findBySchoolYearAndTeahcerIdAndDate", NoteDaily.class, id, id1, id2);
    }

    public List<NoteDaily> findBySchoolYearAndClass(Long id, Long id1) {
        return dao.findAll("NoteDaily.findBySchoolYearAndClass", NoteDaily.class, id, id1);
    }

    public List<NoteDaily> findBySchoolYear(Long id) {
        return dao.findAll("NoteDaily.findBySchoolYear", NoteDaily.class, id);
    }

    public List<NoteDaily> findBySubjectTAndEntryDate(Long id, Date id1) {
        return dao.findAll("NoteDaily.findBySubjectTAndEntryDate", NoteDaily.class, id, id1);
    }

    public List<NoteDaily> findBySchoolYearIdAndStudentId(Long id, Long id1) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndStudentId", NoteDaily.class, id, id1);
    }

    public List<NoteDaily> findBySchoolYearIdAndStudentIdAndDate(Long id, Long id1, Date id2) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndStudentIdAndDate", NoteDaily.class, id, id1, id2);
    }
    public List<NoteDaily> findBySchoolYearIdAndParentIdAndDate(Long id, Long id1, Date id2) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndParentIdAndDate", NoteDaily.class, id, id1, id2);
    }
    public List<NoteDaily> findBySubjectTAndStudentSIdEntryDate(Long id, Long id1, Date id2) {
        return dao.findAll("NoteDaily.findBySubjectTAndStudentSIdEntryDate", NoteDaily.class, id, id1, id2);
    }

    public List<NoteDaily> findBySchoolYearAndClassAnd2Date(Long id, Long id1, Date id2, Date id3) {
        return dao.findAll("NoteDaily.findBySchoolYearAndClassAnd2Date", NoteDaily.class, id, id1, id2, id3);
    }

    public List<NoteDaily> findBySchoolYearIdAndStudentIdAnd2Date(Long id, Long id1, Date id2, Date id3) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndStudentIdAnd2Date", NoteDaily.class, id, id1, id2, id3);
    }
    public List<NoteDaily> findBySchoolYearIdAndParentIdAnd2Date(Long id, Long id1, Date id2, Date id3) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndParentIdAnd2Date", NoteDaily.class, id, id1, id2, id3);
    }

    public List<NoteDaily> findBySchoolYearIdAndSubjectIdAndStudentIdAnd2Date(Long id, Long id1, Long id2, Date id3, Date id4) {
        return dao.findAll("NoteDaily.findBySchoolYearIdAndSubjectIdAndStudentIdAnd2Date", NoteDaily.class, id, id1, id2, id3, id4);
    }

    public NoteDaily find(Long id) {
        return dao.find(id, NoteDaily.class);
    }

    public void update(NoteDaily obj) {
        dao.update(obj);
    }

    public void delete(NoteDaily obj) {
        dao.delete(obj);
    }
}
