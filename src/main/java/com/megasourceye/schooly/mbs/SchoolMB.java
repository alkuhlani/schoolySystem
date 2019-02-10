/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.google.gson.Gson;
import com.megasourceye.schooly.ejbs.SchoolBean;
import com.megasourceye.schooly.ejbs.SystemVariablesBean;
import com.megasourceye.schooly.ejbs.TermTypeBean;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.SystemVariables;
import com.megasourceye.schooly.entities.TermType;
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
@Named(value = "schoolMB")
@ViewScoped
public class SchoolMB implements Serializable {

    /**
     * Creates a new instance of SchoolMB
     */
    public SchoolMB() {
    }
    private boolean edit = false;
    private long id = 1;
    private School item;
    private SystemVariables sv;
    private List<School> items;
    private List<TermType> termTypes;

    @Inject
    private SchoolBean schoolBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private SystemVariablesBean systemVariablesBean;
    @Inject
    private TermTypeBean termTypeBean;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = schoolBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item = schoolBean.find(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                item = schoolBean.find(loginMB.getUser().getSchool().getId());
            }
        }

    }

    public void prepareItem() {
//        items = schoolBean.findAll();
        item = schoolBean.find(loginMB.getUser().getSchool().getId());
        sv=new SystemVariables();
    }

    public void prepareCreate() {
        item = new School();
        sv = new SystemVariables();
        sv.setSchool(new School());
        sv.setTermType(new  TermType());
        termTypes=termTypeBean.findAll();
        
        
        
    }

    public void prepareUpdate(Long id2) {
        item = schoolBean.find(id2);
        termTypes=termTypeBean.findAll();
    }

    public void create() {
        schoolBean.create(item);
        sv.setSchool(item);
        systemVariablesBean.create(sv);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        schoolBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "setup_school?faces-redirect=true";

    }

    public String delete() {
        schoolBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "setup_school?faces-redirect=true";
    }

    public void edit() {
        edit = true;
    }

    public void save() {
        edit = false;
    }

    public School getItem() {
        return item;
    }

    public void setItem(School item) {
        this.item = item;
    }

    public List<School> getItems() {
        return items;
    }

    public void setItems(List<School> items) {
        this.items = items;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SystemVariables getSv() {
        return sv;
    }

    public void setSv(SystemVariables sv) {
        this.sv = sv;
    }

    public List<TermType> getTermTypes() {
        return termTypes;
    }

    public void setTermTypes(List<TermType> termTypes) {
        this.termTypes = termTypes;
    }

}
