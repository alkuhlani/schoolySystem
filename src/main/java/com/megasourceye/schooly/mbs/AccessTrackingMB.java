/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.AccessTrackingBean;
import com.megasourceye.schooly.ejbs.DayBean;
import com.megasourceye.schooly.entities.AccessTracking;
import com.megasourceye.schooly.entities.DT;
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
@Named(value = "accessTrackingMB")
@ViewScoped
public class AccessTrackingMB implements Serializable {

    /**
     * Creates a new instance of AccessTrackingMB
     */
    public AccessTrackingMB() {
    }
    private AccessTracking item;
    private List<AccessTracking> items;

    @Inject
    private AccessTrackingBean bean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = bean.findAll();
            }
        }
    }
    public void prepareItems(Long id) {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = bean.findByUserId(id);
            }
        }
    }

    public void prepareCreate() {
        item = new AccessTracking();

    }

    public void prepareUpdate(Long id2) {
        item = bean.find(id2);
    }

    public void create() {
        bean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        bean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        bean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public AccessTracking getItem() {
        return item;
    }

    public void setItem(AccessTracking item) {
        this.item = item;
    }

    public List<AccessTracking> getItems() {
        return items;
    }

    public void setItems(List<AccessTracking> items) {
        this.items = items;
    }

}
