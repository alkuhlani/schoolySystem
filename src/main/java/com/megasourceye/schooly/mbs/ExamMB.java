/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ExamBean;
import com.megasourceye.schooly.ejbs.SchoolMonthBean;
import com.megasourceye.schooly.ejbs.SchoolWeekBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.entities.Exam;
import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.SchoolWeek;
import com.megasourceye.schooly.entities.TermMonth;
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
@Named(value = "examMB")
@ViewScoped
public class ExamMB implements Serializable {

    /**
     * Creates a new instance of ExamMB
     */
    public ExamMB() {
    }
    private Exam item;
    private List<Exam> items;
    private List<SchoolWeek> schoolWeeks;
    private List<SchoolMonth> schoolMonths;
    private List<TermWeek> termWeeks;
    private List<TermMonth> termMonths;

    @Inject
    private ExamBean examBean;
    @Inject
    private SchoolWeekBean schoolWeekBean;
    @Inject
    private SchoolMonthBean schoolMonthBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("5");
                System.out.println("5");
                System.out.println("5");
                System.out.println("5");
                items = examBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());               
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            }
        }

    }

    public void prepareCreate() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item = new Exam();
                item.setSchoolYear(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 1) {
                    item.setTermMonth(loginMB.getUser().getSchool().getSystemVariables().getTermMonth());
                } else if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 2) {
                    item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());

                }
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            }
        }
    }

    public void prepareUpdate(Long id2) {
        item = examBean.find(id2);
    }

    public String create() {
        item.setIsActive(false);
        System.out.println("create");
        System.out.println("create");
        System.out.println("create");
        examBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }

    public String update() {
        examBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        examBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void typeListner() {
        System.out.println("term type" + loginMB.getUser().getSchool().getSystemVariables().getTermType().getId());
        System.out.println("item.getType(): " + item.getType());
        if (null != item.getType()) {
            switch (item.getType()) {
                case '0':
                    System.out.println("0");
                    schoolWeeks = schoolWeekBean.findByTermId(
                            loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
                    if (loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek() != null) {
                        item.setSchoolWeek(loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek());
                    } else {
                        item.setSchoolWeek(new SchoolWeek());
                    }
                    System.out.println("0.0");
                    item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());
                    break;
                case '1':
                    termWeeks = termWeekBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermWeek() != null) {
                        item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());
                    } else {
                        item.setTermWeek(new TermWeek());
                    }
                    item.setSchoolWeek(null);
                    break;
                case '2':
                    termWeeks = termWeekBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermWeek() != null) {
                        item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());
                    } else {
                        item.setTermWeek(new TermWeek());
                    }
                    item.setSchoolWeek(null);
                    break;
                case '3':
                    System.out.println("33");
                    System.out.println("33");
                    System.out.println("33");
                    schoolMonths = schoolMonthBean.findByTermId(
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
                    if (loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth() != null) {
                        item.setSchoolMonth(new SchoolMonth());
                        item.setSchoolMonth(loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth());
                    } else {
                        item.setSchoolMonth(new SchoolMonth());
                    }
//                    item.setTermMonth(loginMB.getUser().getSchool().getSystemVariables().getTermMonth());
                    System.out.println("444");
                    System.out.println("444");
                    System.out.println("444");
                    break;
                case '4':
                    System.out.println("1");
                    System.out.println("1");
                    System.out.println("1");
                    System.out.println("1");
                    System.out.println("1");
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermMonth() != null) {
                        System.out.println("1000");
                        item.setTermMonth(loginMB.getUser().getSchool().getSystemVariables().getTermMonth());
                        System.out.println("getTermMonth" + loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getName_ar());
                    } else {
                        System.out.println("5000");

                        item.setTermMonth(new TermMonth());
                    }
                    long i = 1;
                    termMonths = termMonthBean.findBySchoolYearIdAndTermId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            i);
                    System.out.println("getSchoolYear" + loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                    System.out.println("new Long('1')" + new Long('1'));
                    item.setSchoolMonth(null);
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    break;
                case '5':
                    long i2 = 2;
                    System.out.println("2");
                    System.out.println("2");
                    System.out.println("2");
                    System.out.println("2");
                    System.out.println("2");
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermMonth() != null) {
                        item.setTermMonth(loginMB.getUser().getSchool().getSystemVariables().getTermMonth());
                    } else {
                        item.setTermMonth(new TermMonth());
                    }
                    termMonths = termMonthBean.findBySchoolYearIdAndTermId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            i2);

                    item.setSchoolMonth(null);
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    System.out.println("termMonths" + termMonths.size());
                    break;
                default:
                    break;
            }
        }
    }

    public void setExamActive(Long id) {
        System.out.println("1111");
        items = examBean.findBySchoolYearIdAndIsActive(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println("items.size()" + items.size());
        if (items.size() >= 1) {
            System.out.println("in for");
            for (int i = 0; i < items.size(); i++) {
                System.out.println("i=" + i);
                item =examBean.find(items.get(i).getId());
                item.setIsActive(false);
                examBean.update(item);
            }
        }
        item = examBean.find(id);
        System.out.println("id" + id);
        item.setIsActive(true);
        examBean.update(item);
        prepareItems();

    }

    public Exam getItem() {
        return item;
    }

    public void setItem(Exam item) {
        this.item = item;
    }

    public List<Exam> getItems() {
        return items;
    }

    public void setItems(List<Exam> items) {
        this.items = items;
    }

    public List<SchoolWeek> getSchoolWeeks() {
        return schoolWeeks;
    }

    public void setSchoolWeeks(List<SchoolWeek> schoolWeeks) {
        this.schoolWeeks = schoolWeeks;
    }

    public List<SchoolMonth> getSchoolMonths() {
        return schoolMonths;
    }

    public void setSchoolMonths(List<SchoolMonth> schoolMonths) {
        this.schoolMonths = schoolMonths;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

}
