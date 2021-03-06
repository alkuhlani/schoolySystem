/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.WeekMark;
import com.megasourceye.schooly.entities.WeekTopMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class WeekTopMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(WeekTopMark obj) {
        dao.create(obj);
    }

    public List<WeekTopMark> findAll() {
        return dao.findAll("WeekTopMark.findAll", WeekTopMark.class);
    }

    public List<WeekTopMark> findBySchoolId(Long id) {
        return dao.findAll("WeekTopMark.findBySchoolId", WeekTopMark.class, id);
    }

    public WeekTopMark find(Long id) {
        return dao.find(id, WeekTopMark.class);
    }

    public void update(WeekTopMark obj) {
        dao.update(obj);
    }

    public void delete(WeekTopMark obj) {
        dao.delete(obj);
    }
}
