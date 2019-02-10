/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Branch;
import com.megasourceye.schooly.entities.Term;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class BranchBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Inject
    private DataAccessObject dao;

    public void create(Branch obj) {
        dao.create(obj);
    }

    public List<Branch> findAll() {
        return dao.findAll("Branch.findAll", Branch.class);
    }

    public List<Branch> findBySchoolId(Long id) {
        return dao.findAll("Branch.findBySchoolId", Branch.class, id);
    }

    public Branch find(Long id) {
        return dao.find(id, Branch.class);
    }

    public void update(Branch obj) {
        dao.update(obj);
    }

    public void delete(Branch obj) {
        dao.delete(obj);
    }
}
