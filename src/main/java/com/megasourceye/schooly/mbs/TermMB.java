/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.TermBean;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.Term;
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
@Named(value = "termMB")
@ViewScoped
public class TermMB implements Serializable {

    /**
     * Creates a new instance of TermMB
     */
    public TermMB() {
    }
    private Term item;
    private List<Term> items;

    @Inject
    private TermBean termBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = termBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            }
        }

    }

    public void prepareCreate() {
        item = new Term();
    }

    public void prepareUpdate(Long id2) {
        item = termBean.find(id2);
    }

    public void create() {
        termBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        termBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        termBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public Term getItem() {
        return item;
    }

    public void setItem(Term item) {
        this.item = item;
    }

    public List<Term> getItems() {
        return items;
    }

    public void setItems(List<Term> items) {
        this.items = items;
    }

}
