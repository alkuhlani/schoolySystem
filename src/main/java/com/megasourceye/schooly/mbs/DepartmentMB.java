/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.DepartmentBean;
import com.megasourceye.schooly.ejbs.DepartmentTBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Department;
import com.megasourceye.schooly.entities.DepartmentType;
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
@Named(value = "departmentMB")
@ViewScoped
public class DepartmentMB implements Serializable{

    /**
     * Creates a new instance of DepartmentMB
     */
    public DepartmentMB() {
    }

    private Department item;
    private List<Department> items;
    private List<DepartmentType> departmentTypes;

    @Inject
    private DepartmentBean departmentBean;
    @Inject
    private DepartmentTBean departmentTBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        items = departmentBean.findAll();
    }

    public void prepareCreate() {
        item = new Department();
        item.setDepartmentType(new DepartmentType());
        departmentTypes=departmentTBean.findAll();
    }

    public void prepareUpdate(Long id2) {
        item = departmentBean.find(id2);
        departmentTypes=departmentTBean.findAll();
        if(item.getDepartmentType()==null){
            item.setDepartmentType(new DepartmentType());
        }
    }

    public void create() {
        departmentBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        departmentBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        departmentBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public Department getItem() {
        return item;
    }

    public void setItem(Department item) {
        this.item = item;
    }

    public List<Department> getItems() {
        return items;
    }

    public void setItems(List<Department> items) {
        this.items = items;
    }

    public List<DepartmentType> getDepartmentTypes() {
        return departmentTypes;
    }

    public void setDepartmentTypes(List<DepartmentType> departmentTypes) {
        this.departmentTypes = departmentTypes;
    }

    
}
