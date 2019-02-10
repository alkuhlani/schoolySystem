/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.DepartmentTBean;
import com.megasourceye.schooly.entities.DepartmentType;
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
@Named(value = "departmentTMB")
@ViewScoped
public class DepartmentTMB implements Serializable{

    /**
     * Creates a new instance of DepartmentTMB
     */
    public DepartmentTMB() {
    }

    private DepartmentType item;
    private List<DepartmentType> items;

    @Inject
    private DepartmentTBean departmentTBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        items = departmentTBean.findAll();
    }

    public void prepareCreate() {
        item = new DepartmentType();
    }

    public void prepareUpdate(Long id2) {
        item = departmentTBean.find(id2);
    }

    public void create() {
        departmentTBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        departmentTBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String delete() {
        departmentTBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public DepartmentType getItem() {
        return item;
    }

    public void setItem(DepartmentType item) {
        this.item = item;
    }

    public List<DepartmentType> getItems() {
        return items;
    }

    public void setItems(List<DepartmentType> items) {
        this.items = items;
    }
}
