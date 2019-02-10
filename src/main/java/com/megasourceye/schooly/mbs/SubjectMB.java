/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.RolesBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.entities.Roles;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Subject_t;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "subjectMB")
@ViewScoped
public class SubjectMB implements Serializable {

    /**
     * Creates a new instance of SubjectMB
     */
    public SubjectMB() {
    }
    private Subject_t item;
    private List<Subject_t> items;

    @Inject
    private SubjectBean subjectBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
       
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = subjectBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
    }



    public void prepareCreate() {
        item = new Subject_t();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = subjectBean.find(id2);
    }

    public void create() {
        item.setSchool(loginMB.getUser().getSchool());
        subjectBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        prepareCreate();
        //  return "items?faces-redirect=true";
    }

    public String update() {
        subjectBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        subjectBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public Subject_t getItem() {
        return item;
    }

    public void setItem(Subject_t item) {
        this.item = item;
    }

    public List<Subject_t> getItems() {
        return items;
    }

    public void setItems(List<Subject_t> items) {
        this.items = items;
    }

    

}
