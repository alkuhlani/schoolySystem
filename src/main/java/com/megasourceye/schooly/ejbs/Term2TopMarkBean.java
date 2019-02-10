/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Term2TopMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class Term2TopMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Term2TopMark obj) {
        dao.create(obj);
    }

    public List<Term2TopMark> findAll() {
        return dao.findAll("Term2TopMark.findAll", Term2TopMark.class);
    }

    public List<Term2TopMark> findBySchoolId(Long id) {
                System.out.println("3");
        return dao.findAll("Term2TopMark.findBySchoolId", Term2TopMark.class, id);
    }

    public Term2TopMark find(Long id) {
        return dao.find(id, Term2TopMark.class);
    }

    public void update(Term2TopMark obj) {
        dao.update(obj);
    }

    public void delete(Term2TopMark obj) {
        dao.delete(obj);
    }
}
