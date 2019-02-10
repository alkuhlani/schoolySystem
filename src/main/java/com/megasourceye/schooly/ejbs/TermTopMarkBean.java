/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.TermTopMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TermTopMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(TermTopMark obj) {
        dao.create(obj);
    }

    public List<TermTopMark> findAll() {
        return dao.findAll("TermTopMark.findAll", TermTopMark.class);
    }

    public List<TermTopMark> findBySchoolId(Long id) {
        return dao.findAll("TermTopMark.findBySchoolId", TermTopMark.class, id);
    }

    public TermTopMark find(Long id) {
        return dao.find(id, TermTopMark.class);
    }

    public void update(TermTopMark obj) {
        dao.update(obj);
    }

    public void delete(TermTopMark obj) {
        dao.delete(obj);
    }
}
