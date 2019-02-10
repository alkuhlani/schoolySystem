/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class UsersBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Users obj) {
        dao.create(obj);
    }

    public List<Users> findAll() {
        return dao.findAll("Users.findAll", Users.class);
    }

    public List<Users> findBySchoolId(Long id) {
        return dao.findAll("Users.findBySchoolId", Users.class, id);
    }
    public List<Users> findByUsername(String id) {
        return dao.findAll("Users.findByUsername", Users.class, id);
    }
    

    public Users find(Long id) {
        return dao.find(id, Users.class);
    }

    public void update(Users obj) {
        dao.update(obj);
    }

    public void delete(Users obj) {
        dao.delete(obj);
    }


}
