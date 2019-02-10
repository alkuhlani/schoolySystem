/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.Term2TopMarkBean;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Term2TopMark;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "term2TopMarkMB")
@ViewScoped
public class Term2TopMarkMB implements Serializable {

    /**
     * Creates a new instance of Term2TopMarkMB
     */
    public Term2TopMarkMB() {
    }

    private Term2TopMark item;
    private List<Term2TopMark> items;

    @Inject
    private Term2TopMarkBean term2TopMarkBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        System.out.println("1");
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = term2TopMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("2");
                items = term2TopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new Term2TopMark();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = term2TopMarkBean.find(id2);
    }

    public String create() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {

            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item.setSchool(loginMB.getUser().getSchool());

                term2TopMarkBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            }
        }
          return "items?faces-redirect=true";
    }

    public String update() {
        term2TopMarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        term2TopMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public Term2TopMark getItem() {
        return item;
    }

    public void setItem(Term2TopMark item) {
        this.item = item;
    }

    public List<Term2TopMark> getItems() {
        return items;
    }

    public void setItems(List<Term2TopMark> items) {
        this.items = items;
    }

}
