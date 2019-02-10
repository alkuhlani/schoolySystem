/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.TermBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.Term;
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
@Named(value = "termMonthMB")
@ViewScoped
public class TermMonthMB implements Serializable {

    /**
     * Creates a new instance of TermMonthMB
     */
    public TermMonthMB() {
    }
    private TermMonth item;
    private List<TermMonth> items;
    private List<Term> terms;

    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private TermBean termBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("1");
                items = termMonthBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("2");
                items = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                System.out.println("3");
                items = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            }
        }
        System.out.println(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println(items.size());

    }

    public void prepareCreate() {
        item = new TermMonth();
        if (loginMB.getUser() != null) {
            item.setSchoolYear(new SchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId()));
        }
        item.setTerm(new Term());
        terms = termBean.findByTermType(new Long("1"));
//        terms=termBean.findByTermType(loginMB.getUser().getSchool().getSystemVariables().getTermType().getId());

    }

    public void prepareUpdate(Long id2) {
        item = termMonthBean.find(id2);
        terms = termBean.findAll();
    }

    public String create() {
        if (termMonthBean.findBySchoolYearIdAndTermId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), 
                item.getTerm().getId()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Founded"));
        } else if (termMonthBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId()).size() >= 2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Founded"));
        } else {
            termMonthBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        }

          return "items?faces-redirect=true";
    }

    public String update() {
        termMonthBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        termMonthBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public TermMonth getItem() {
        return item;
    }

    public void setItem(TermMonth item) {
        this.item = item;
    }

    public List<TermMonth> getItems() {
        return items;
    }

    public void setItems(List<TermMonth> items) {
        this.items = items;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

}
