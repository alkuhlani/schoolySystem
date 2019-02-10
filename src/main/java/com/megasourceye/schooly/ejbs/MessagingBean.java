/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Messaging;
import com.megasourceye.schooly.entities.Term;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class MessagingBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Messaging obj) {
        dao.create(obj);
    }

    public List<Messaging> findAll() {
        return dao.findAll("Messaging.findAll", Messaging.class);
    }

    public List<Messaging> findByTermType(Long id) {
        return dao.findAll("Messaging.findByTermType", Messaging.class, id);
    }
    public List<Messaging> findBySYIdAndSenderIdAndOwnId(Long id,Long id1,Long id2) {
        return dao.findAll("Messaging.findBySYIdAndSenderIdAndOwnId", Messaging.class, id,id1,id2);
    }

    public Messaging find(Long id) {
        return dao.find(id, Messaging.class);
    }

    public void update(Messaging obj) {
        dao.update(obj);
    }

    public void delete(Messaging obj) {
        dao.delete(obj);
    }

}
