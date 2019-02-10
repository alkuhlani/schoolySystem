/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.HomeWork;
import com.megasourceye.schooly.entities.MonthMark;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class HomeWorkBaen {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(HomeWork obj) {
        dao.create(obj);
    }

    public List<HomeWork> findAll() {
        return dao.findAll("HomeWork.findAll", HomeWork.class);
    }

    public List<HomeWork> findBySchoolYearAndTeahcerId(Long id, Long id1) {
        return dao.findAll("HomeWork.findBySchoolYearAndTeahcerId", HomeWork.class, id, id1);
    }
    public List<HomeWork> findBySchoolYearAndTeahcerIdAndDate(Long id, Long id1,Date id2) {
        return dao.findAll("HomeWork.findBySchoolYearAndTeahcerIdAndDate", HomeWork.class, id, id1,id2);
    }

    public List<HomeWork> findBySchoolYearAndClass(Long id, Long id1) {
        return dao.findAll("HomeWork.findBySchoolYearAndClass", HomeWork.class, id, id1);
    }
    public List<HomeWork> findBySchoolYearAndClassAndDate(Long id, Long id1,Date id2) {
        return dao.findAll("HomeWork.findBySchoolYearAndClassAndDate", HomeWork.class, id, id1,id2);
    }
    public List<HomeWork> findBySchoolYearAndClassAnd2Date(Long id, Long id1,Date id2,Date id3) {
        return dao.findAll("HomeWork.findBySchoolYearAndClassAnd2Date", HomeWork.class, id, id1,id2,id3);
    }
    public List<HomeWork> findBySchoolYearAndTeahcerAssignIdAnd2Date(Long id, Long id1,Date id2,Date id3) {
        return dao.findAll("HomeWork.findBySchoolYearAndTeahcerAssignIdAnd2Date", HomeWork.class, id, id1,id2,id3);
    }

    public List<HomeWork> findBySchoolYear(Long id) {
        return dao.findAll("HomeWork.findBySchoolYear", HomeWork.class, id);
    }
    public List<HomeWork> findBySchoolYearAndDate(Long id,Date id1) {
        return dao.findAll("HomeWork.findBySchoolYearAndDate", HomeWork.class, id,id1);
    }
    public List<HomeWork> findBySchoolYearAndClassAndSubjectIdAnd2Date(Long id,Long id1,Long id2,Date id3,Date id4) {
        return dao.findAll("HomeWork.findBySchoolYearAndClassAndSubjectIdAnd2Date", HomeWork.class, id,id1,id2,id3,id4);
    }

    public List<HomeWork> findBySubjectTAndDate(Long id, Date id1) {
        return dao.findAll("HomeWork.findBySubjectTAndDate", HomeWork.class, id, id1);
    }

    public HomeWork find(Long id) {
        return dao.find(id, HomeWork.class);
    }

    public void update(HomeWork obj) {
        dao.update(obj);
    }

    public void delete(HomeWork obj) {
        dao.delete(obj);
    }
}
