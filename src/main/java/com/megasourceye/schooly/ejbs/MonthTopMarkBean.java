/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.MonthTopMark;
import com.megasourceye.schooly.entities.WeekTopMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class MonthTopMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(MonthTopMark obj) {
        dao.create(obj);
    }

    public List<MonthTopMark> findAll() {
        return dao.findAll("MonthTopMark.findAll", MonthTopMark.class);
    }

    public List<MonthTopMark> findBySchoolId(Long id) {
        return dao.findAll("MonthTopMark.findBySchoolId", MonthTopMark.class, id);
    }

    public MonthTopMark find(Long id) {
        return dao.find(id, MonthTopMark.class);
    }

    public void update(MonthTopMark obj) {
        dao.update(obj);
    }

    public void delete(MonthTopMark obj) {
        dao.delete(obj);
    }
}
