/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.AccessTracking;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class AccessTrackingBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(AccessTracking obj) {
        dao.create(obj);
    }

    public List<AccessTracking> findAll() {
        return dao.findAll("AccessTracking.findAll", AccessTracking.class);
    }

    public List<AccessTracking> findBySchoolId(Long id) {
        return dao.findAll("AccessTracking.findBySchoolId", AccessTracking.class, id);
    }   
    public List<AccessTracking> findByUserId(Long id) {
        return dao.findAll("AccessTracking.findByUserId", AccessTracking.class, id);
    }   

    public AccessTracking find(Long id) {
        return dao.find(id, AccessTracking.class);
    }

    public void update(AccessTracking obj) {
        dao.update(obj);
    }

    public void delete(AccessTracking obj) {
        dao.delete(obj);
    }

}
