/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.RolesBean;
import com.megasourceye.schooly.ejbs.SchoolYearBean;
import com.megasourceye.schooly.entities.Roles;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.SchoolYear;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "schoolYearMB")
@ViewScoped
public class SchoolYearMB implements Serializable{

    /**
     * Creates a new instance of SchoolYearMB
     */
    public SchoolYearMB() {
    }
    private SchoolYear item;
    private List<SchoolYear> items;

    @Inject
    private SchoolYearBean schoolYearBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        items = schoolYearBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        
        
    }

    public void prepareItem() {
//        items = schoolBean.findAll();
        item = new SchoolYear();
    }

    public void prepareCreate() {
        item = new SchoolYear();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = schoolYearBean.find(id2);
    }

    public void create() {
        item.setSchool(loginMB.getUser().getSchool());
        schoolYearBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        schoolYearBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        schoolYearBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public SchoolYear getItem() {
        return item;
    }

    public void setItem(SchoolYear item) {
        this.item = item;
    }

    public List<SchoolYear> getItems() {
        return items;
    }

    public void setItems(List<SchoolYear> items) {
        this.items = items;
    }

    
 

}
