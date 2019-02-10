/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Users;
import com.megasourceye.schooly.entities.Vacation;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class VacationBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Vacation obj) {
        dao.create(obj);
    }

    public List<Vacation> findAll() {
        return dao.findAll("Vacation.findAll", Vacation.class);
    }
    public List<Vacation> findBySchoolyYearId(Long id) {
        return dao.findAll("Vacation.findBySchoolyYearId", Vacation.class,id);
    }
    public List<Vacation> findBySchoolyYearIdAndParentId(Long id,Long id1) {
        return dao.findAll("Vacation.findBySchoolyYearIdAndParentId", Vacation.class,id,id1);
    }
    public List<Vacation> findBySchoolyYearIdAndStudentId(Long id,Long id1) {
        return dao.findAll("Vacation.findBySchoolyYearIdAndStudentId", Vacation.class,id,id1);
    }
    public List<Vacation> findBySchoolyYearIdAndStudentIdAndTrue(Long id,Long id1,Date id2) {
        return dao.findAll("Vacation.findBySchoolyYearIdAndStudentIdAndTrue", Vacation.class,id,id1,id2);
    }

    public List<Vacation> findByParentId(Long id) {
        return dao.findAll("Vacation.findByParentId", Vacation.class, id);
    }

    public Vacation find(Long id) {
        return dao.find(id, Vacation.class);
    }

    public void update(Vacation obj) {
        dao.update(obj);
    }

    public void delete(Vacation obj) {
        dao.delete(obj);
    }
}
