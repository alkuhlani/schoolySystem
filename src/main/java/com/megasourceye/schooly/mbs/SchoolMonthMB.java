/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.SchoolBean;
import com.megasourceye.schooly.ejbs.SchoolMonthBean;
import com.megasourceye.schooly.ejbs.SystemVariablesBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.SystemVariables;
import com.megasourceye.schooly.entities.TermMonth;
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
@Named(value = "schoolMonthMB")
@ViewScoped
public class SchoolMonthMB implements Serializable {

    /**
     * Creates a new instance of SchoolMonthMB
     */
    public SchoolMonthMB() {
    }
    private SchoolMonth item;
    private TermMonth termMonth;
    private List<SchoolMonth> items;
    private List<TermMonth> termMonths;

    @Inject
    private SchoolMonthBean schoolMonthBean;
    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = schoolMonthBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = schoolMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = schoolMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = schoolMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = schoolMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = schoolMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            }

        }
    }

    public void prepareCreate() {
        item = new SchoolMonth();
        item.setSchoolYear(new SchoolYear());
        item.setTermMonth(new TermMonth());
        termMonths = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
    }

    public void prepareUpdate(Long id) {
        item = schoolMonthBean.find(id);
    }

    public String create() {
        termMonth = termMonthBean.find(item.getTermMonth().getId());
        if (schoolMonthBean.findByTermId(
                item.getTermMonth().getId()).size() >= termMonth.getNoMonths()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "You can't add more than it the total month you can add is " + termMonth.getNoMonths()));
        } else {
            item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
            schoolMonthBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        }

          return "items?faces-redirect=true";
    }

    public String update() {
        schoolMonthBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "setup_school?faces-redirect=true";

    }

    public String delete() {
        schoolMonthBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "setup_school?faces-redirect=true";
    }

    public SchoolMonth getItem() {
        return item;
    }

    public void setItem(SchoolMonth item) {
        this.item = item;
    }

    public List<SchoolMonth> getItems() {
        return items;
    }

    public void setItems(List<SchoolMonth> items) {
        this.items = items;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

}
