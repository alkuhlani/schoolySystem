/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.DayBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.DT;
import com.megasourceye.schooly.entities.School;
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
@Named(value = "dayMB")
@ViewScoped
public class DayMB implements Serializable{

    /**
     * Creates a new instance of DayMB
     */
    public DayMB() {
    }
    private DT item;
    private List<DT> items;

    @Inject
    private DayBean dayBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        items = dayBean.findAll();
    }



    public void prepareCreate() {
        item = new DT();
       
    }

    public void prepareUpdate(Long id2) {
        item = dayBean.find(id2);
    }

    public void create() {
        dayBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        dayBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        dayBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public DT getItem() {
        return item;
    }

    public void setItem(DT item) {
        this.item = item;
    }

    public List<DT> getItems() {
        return items;
    }

    public void setItems(List<DT> items) {
        this.items = items;
    }

    
    
}
