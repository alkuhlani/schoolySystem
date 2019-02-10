/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.TermTypeBean;
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
@Named(value = "termTypeMB")
@ViewScoped
public class TermTypeMB implements Serializable {

    /**
     * Creates a new instance of TermTypeMB
     */
    public TermTypeMB() {
    }

    private TermType item;
    private List<TermType> items;

    @Inject
    private TermTypeBean termTypeBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = termTypeBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            }
        }
    }

    public void prepareCreate() {
        item = new TermType();
    }

    public void prepareUpdate(Long id2) {
        item = termTypeBean.find(id2);
    }

    public void create() {
        termTypeBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        termTypeBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        termTypeBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public TermType getItem() {
        return item;
    }

    public void setItem(TermType item) {
        this.item = item;
    }

    public List<TermType> getItems() {
        return items;
    }

    public void setItems(List<TermType> items) {
        this.items = items;
    }

}
