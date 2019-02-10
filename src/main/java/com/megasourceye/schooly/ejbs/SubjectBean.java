/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Subject_t;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SubjectBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Subject_t obj) {
        dao.create(obj);
    }

    public List<Subject_t> findAll() {
        return dao.findAll("Subject_t.findAll", Subject_t.class);
    }

    public List<Subject_t> findBySchoolId(Long id) {
        return dao.findAll("Subject_t.findBySchoolId", Subject_t.class, id);
    }

    public Subject_t find(Long id) {
        return dao.find(id, Subject_t.class);
    }

    public void update(Subject_t obj) {
        dao.update(obj);
    }

    public void delete(Subject_t obj) {
        dao.delete(obj);
    }

}
