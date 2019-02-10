/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.Term;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TermBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Term obj) {
        dao.create(obj);
    }

    public List<Term> findAll() {
        return dao.findAll("Term.findAll", Term.class);
    }

    public List<Term> findByTermType(Long id) {
        return dao.findAll("Term.findByTermType", Term.class, id);
    }

    public Term find(Long id) {
        return dao.find(id, Term.class);
    }

    public void update(Term obj) {
        dao.update(obj);
    }

    public void delete(Term obj) {
        dao.delete(obj);
    }

}
