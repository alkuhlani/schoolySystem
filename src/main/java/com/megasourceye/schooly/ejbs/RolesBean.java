/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Roles;
import com.megasourceye.schooly.entities.School;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class RolesBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Roles obj) {
        dao.create(obj);
    }

    public List<Roles> findAll() {
        return dao.findAll("Roles.findAll", Roles.class);
    }

    public List<Roles> findByCourseId(Long id) {
        return dao.findAll("Roles.findByCourseId", Roles.class, id);
    }

    public Roles findBy(Long id) {
        return dao.findBy("Roles.findById", Roles.class, id);
    }

    public Roles find(Long id) {
        return dao.find(id, Roles.class);
    }

    public void update(Roles obj) {
        dao.update(obj);
    }

    public void delete(Roles obj) {
        dao.delete(obj);
    }
}
