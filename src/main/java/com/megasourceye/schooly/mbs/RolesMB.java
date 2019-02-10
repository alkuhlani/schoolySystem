/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.RolesBean;
import com.megasourceye.schooly.entities.Roles;
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
@Named(value = "rolesMB")
@ViewScoped
public class RolesMB implements Serializable {

    /**
     * Creates a new instance of RolesMB
     */
    public RolesMB() {
    }
    private Roles item;
    private List<Roles> items;

    @Inject
    private RolesBean rolesBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        items = rolesBean.findAll();
    }

    public void prepareItem() {
//        items = schoolBean.findAll();
        item = new Roles();
    }

    public void prepareCreate() {
        item = new Roles();
    }

    public void prepareUpdate(Long id2) {
        item = rolesBean.find(id2);
    }

    public void create() {
        rolesBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        rolesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        rolesBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public Roles getItem() {
        return item;
    }

    public void setItem(Roles item) {
        this.item = item;
    }

    public List<Roles> getItems() {
        return items;
    }

    public void setItems(List<Roles> items) {
        this.items = items;
    }

 

}
