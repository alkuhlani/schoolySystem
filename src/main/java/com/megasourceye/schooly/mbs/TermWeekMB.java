/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.TermBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.Term;
import com.megasourceye.schooly.entities.TermWeek;
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
@Named(value = "termWeekMB")
@ViewScoped
public class TermWeekMB implements Serializable {

    /**
     * Creates a new instance of TermWeekMB
     */
    public TermWeekMB() {
    }
    private TermWeek item;
    private List<TermWeek> items;
    private List<Term> terms;

    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private TermBean termBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("1");
                items = termWeekBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("2");
                items = termWeekBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                System.out.println("3");
                items = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            }
        }
        System.out.println(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println(items.size());

    }

    public void prepareCreate() {
        item = new TermWeek();
        if (loginMB.getUser() != null) {
            item.setSchoolYear(new SchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId()));
        }
        item.setTerm(new Term());
        terms = termBean.findAll();
    }

    public void prepareUpdate(Long id2) {
        item = termWeekBean.find(id2);
        terms = termBean.findAll();
    }

    public String create() {
        if (termWeekBean.findBySchoolYearIdAndTermId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), item.getTerm().getId()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Founded"));
        } else if (termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId()).size() >= 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Founded"));
        } else {
            termWeekBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        }
          return "items?faces-redirect=true";
    }

    public String update() {
        termWeekBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        termWeekBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void callReport(){
        
    }
    public TermWeek getItem() {
        return item;
    }

    public void setItem(TermWeek item) {
        this.item = item;
    }

    public List<TermWeek> getItems() {
        return items;
    }

    public void setItems(List<TermWeek> items) {
        this.items = items;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

}
