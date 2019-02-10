/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.MonthTopMarkBean;
import com.megasourceye.schooly.entities.MonthTopMark;
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
@Named(value = "monthTopMarkMB")
@ViewScoped
public class MonthTopMarkMB implements Serializable {

    /**
     * Creates a new instance of MonthTopMarkMB
     */
    public MonthTopMarkMB() {
    }
    private MonthTopMark item;
    private List<MonthTopMark> items;

    @Inject
    private MonthTopMarkBean monthTopMarkBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = monthTopMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = monthTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new MonthTopMark();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = monthTopMarkBean.find(id2);
    }

    public String create() {
        System.out.println("1");
        if (loginMB.getUser() != null) {
            System.out.println("2");
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("3");
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("4");
                item.setSchool(loginMB.getUser().getSchool());
                System.out.println("5");
                monthTopMarkBean.create(item);
                System.out.println("6");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            }
        }
          return "items?faces-redirect=true";
    }

    public String update() {
        monthTopMarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        monthTopMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public MonthTopMark getItem() {
        return item;
    }

    public void setItem(MonthTopMark item) {
        this.item = item;
    }

    public List<MonthTopMark> getItems() {
        return items;
    }

    public void setItems(List<MonthTopMark> items) {
        this.items = items;
    }

}
