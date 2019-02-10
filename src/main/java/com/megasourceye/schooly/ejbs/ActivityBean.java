/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Activity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class ActivityBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Activity obj) {
        dao.create(obj);
    }

    public List<Activity> findAll() {
        return dao.findAll("Attendance.findAll", Activity.class);
    }

    public List<Activity> findBySchoolId(Long id) {
        return dao.findAll("Activity.findBySchoolId", Activity.class, id);
    }

    public List<Activity> findBySchoolYearId(Long id) {
        return dao.findAll("Activity.findBySchoolYearId", Activity.class, id);
    }
    public List<Activity> findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(Long id,Character id1,Character id2) {
        return dao.findAll("Activity.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive", Activity.class, id,id1,id2);
    }
    public List<Activity> findBySchoolYearIdAndTypeAndIsActive(Long id,Character id1) {
        return dao.findAll("Activity.findBySchoolYearIdAndTypeAndIsActive", Activity.class, id,id1);
    }

    public Activity find(Long id) {
        return dao.find(id, Activity.class);
    }

    public void update(Activity obj) {
        dao.update(obj);
    }

    public void delete(Activity obj) {
        dao.delete(obj);
    }

}
