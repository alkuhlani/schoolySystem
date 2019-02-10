/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Term;
import com.megasourceye.schooly.entities.TermType;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TermTypeBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(TermType obj) {
        dao.create(obj);
    }

    public List<TermType> findAll() {
        return dao.findAll("TermType.findAll", TermType.class);
    }

    public List<TermType> findBySchoolId(Long id) {
        return dao.findAll("TermType.findBySchoolId", TermType.class, id);
    }

    public TermType find(Long id) {
        return dao.find(id, TermType.class);
    }

    public void update(TermType obj) {
        dao.update(obj);
    }

    public void delete(TermType obj) {
        dao.delete(obj);
    }

}
