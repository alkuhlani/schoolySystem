/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.Term2TopMarkBean;
import com.megasourceye.schooly.ejbs.TermTopMarkBean;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Term2TopMark;
import com.megasourceye.schooly.entities.TermTopMark;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author said
 */
@Named(value = "termTopMarkMB")
@ViewScoped
public class TermTopMarkMB implements Serializable {

    /**
     * Creates a new instance of TermTopMarkMB
     */
    public TermTopMarkMB() {
    }

    private TermTopMark item;
    private List<TermTopMark> items;

    @ManagedProperty("#{param.id}")
    private long id;
    @Inject
    private TermTopMarkBean termTopMarkBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = termTopMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = termTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new TermTopMark();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = termTopMarkBean.find(id2);
    }
    public void prepareUpdate() {
        item = termTopMarkBean.find(id);
    }

    public String create() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {

            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item.setSchool(loginMB.getUser().getSchool());

                termTopMarkBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            }
        }
      return "items?faces-redirect=true";
    }

    public String p() {
        return "create.xhtml";
    }

    public String p2() {
        return "update?faces-redirect=false&id=2";
    }

    public void doForward(ActionEvent evt) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String redirect = "update.xhtml?id=2";// define the navigation rule that must be used in order to redirect the user to the adequate page...
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, redirect);
    }

    public void nav() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "create");

    }

    public void red() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("create");
    }

    public String up(){
        return "update";
    }
    public String update() {
        termTopMarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        termTopMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public TermTopMark getItem() {
        return item;
    }

    public void setItem(TermTopMark item) {
        this.item = item;
    }

    public List<TermTopMark> getItems() {
        return items;
    }

    public void setItems(List<TermTopMark> items) {
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
