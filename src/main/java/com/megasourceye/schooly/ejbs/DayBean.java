/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.DT;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class DayBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(DT obj) {
        dao.create(obj);
    }

    public List<DT> findAll() {
        return dao.findAll("DT.findAll", DT.class);
    }

    public List<DT> findBySchoolId(Long id) {
        return dao.findAll("DT.findBySchoolId", DT.class, id);
    }

    public DT find(Long id) {
        return dao.find(id, DT.class);
    }

    public void update(DT obj) {
        dao.update(obj);
    }

    public void delete(DT obj) {
        dao.delete(obj);
    }

}
