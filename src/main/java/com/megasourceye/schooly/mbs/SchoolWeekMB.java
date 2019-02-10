/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.SchoolWeekBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.entities.SchoolWeek;
import com.megasourceye.schooly.entities.TermWeek;
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
@Named(value = "schoolWeekMB")
@ViewScoped
public class SchoolWeekMB implements Serializable {

    /**
     * Creates a new instance of SchoolWeekMB
     */
    public SchoolWeekMB() {
    }
    private SchoolWeek item;
    private List<SchoolWeek> items;
    private List<TermWeek> termWeeks;
    private TermWeek termWeek;

    @Inject
    private SchoolWeekBean schoolWeekBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = schoolWeekBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = schoolWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = schoolWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = schoolWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = schoolWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = schoolWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new SchoolWeek();
        item.setTermWeek(new TermWeek());
        item.setSchoolYear(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        termWeeks = termWeekBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println(termWeeks.size());
    }

    public void prepareUpdate(Long id2) {
        item = schoolWeekBean.find(id2);
        termWeeks = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getId());
    }

    public String create() {
        if (item.getTermWeek().getId() != null && item.getTermType() != null) {
            termWeek = termWeekBean.find(item.getTermWeek().getId());
            items = schoolWeekBean.findByTermIdandTermType(item.getTermWeek().getId(), item.getTermType());
            if (item.getTermType().equals('1')) {
                if (termWeek.getNoFirst() <= items.size()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "You can't add more than it the total week you can add is " + termWeek.getNoFirst()));
                } else {
                    schoolWeekBean.create(item);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
                }
            } else if (item.getTermType().equals('2')) {
                if (termWeek.getNoEnd() <= items.size()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "You can't add more than it the total week you can add is " + termWeek.getNoEnd()));
                } else {
                    schoolWeekBean.create(item);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
                }
            }

        }

          return "items?faces-redirect=true";
    }

    public String update() {
        schoolWeekBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        schoolWeekBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public SchoolWeek getItem() {
        return item;
    }

    public void setItem(SchoolWeek item) {
        this.item = item;
    }

    public List<SchoolWeek> getItems() {
        return items;
    }

    public void setItems(List<SchoolWeek> items) {
        this.items = items;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
    }

}
