/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.MonthTopMarkBean;
import com.megasourceye.schooly.ejbs.SchoolMonthBean;
import com.megasourceye.schooly.ejbs.SchoolWeekBean;
import com.megasourceye.schooly.ejbs.SchoolYearBean;
import com.megasourceye.schooly.ejbs.SystemVariablesBean;
import com.megasourceye.schooly.ejbs.Term2TopMarkBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.ejbs.TermTopMarkBean;
import com.megasourceye.schooly.ejbs.TermTypeBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.ejbs.WeekTopMarkBean;
import com.megasourceye.schooly.entities.MonthTopMark;
import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.SchoolWeek;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.SystemVariables;
import com.megasourceye.schooly.entities.Term2TopMark;
import com.megasourceye.schooly.entities.TermMonth;
import com.megasourceye.schooly.entities.TermTopMark;
import com.megasourceye.schooly.entities.TermType;
import com.megasourceye.schooly.entities.TermWeek;
import com.megasourceye.schooly.entities.WeekTopMark;
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
@Named(value = "systemVariablesMB")
@ViewScoped
public class SystemVariablesMB implements Serializable {

    /**
     * Creates a new instance of SystemVariablesMB
     */
    public SystemVariablesMB() {
    }
    private SystemVariables item;
    private List<SystemVariables> items;
    private List<SchoolYear> schoolYears;
    private List<TermType> termTypes;
    private List<TermWeek> termWeeks;
    private List<TermMonth> termMonths;
    private List<SchoolWeek> schoolWeeks;
    private List<SchoolMonth> schoolMonths;
    private List<WeekTopMark> weekTopMarks;
    private List<MonthTopMark> monthTopMarks;
    private List<Term2TopMark> term2TopMarks;
    private List<TermTopMark> termTopMarks;
    private boolean editTermType;
    private boolean editSchoolYear;
    private boolean editofficeHoliday;
    private boolean edittermWeek;
    private boolean edittermMonth;
    private boolean editschoolWeek;
    private boolean editschoolMonth;
    private boolean edittermHalf;
    private boolean editfirstID;
    private boolean editweekTopMark;
    private boolean editmonthTopMark;
    private boolean editterm2TopMark;
    private boolean editterm3TopMark;

    @Inject
    private SystemVariablesBean systemVariablesBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private SchoolYearBean schoolYearBean;
    @Inject
    private TermTypeBean termTypeBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private SchoolWeekBean schoolWeekBean;
    @Inject
    private SchoolMonthBean schoolMonthBean;
    @Inject
    private WeekTopMarkBean weekTopMarkBean;
    @Inject
    private MonthTopMarkBean monthTopMarkBean;
    @Inject
    private Term2TopMarkBean term2TopMarkBean;
    @Inject
    private TermTopMarkBean termTopMarkBean;

    public void prepareItems() {
//        items = schoolBean.findAll();
//        items = systemVariablesBean.findAll();
//        System.out.println(items.size());
//        System.out.println(items.get(0));

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = systemVariablesBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            }
        }

    }

    public void prepareCreate() {
        item = new SystemVariables();
    }

    public void prepareUpdate(Long id) {
        item = systemVariablesBean.find(id);
    }

    public void create() {
        systemVariablesBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String delete() {
        systemVariablesBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void prepareUpdate() {
        editTermType = false;
        editSchoolYear = false;
        editofficeHoliday = false;
        edittermWeek = false;
        edittermHalf = false;
        editschoolMonth = false;
        editschoolWeek = false;
        editfirstID = false;
        editweekTopMark = false;
        editmonthTopMark = false;

        item = systemVariablesBean.find(loginMB.getUser().getSchool().getId());

    }

    public void editTermType() {
        editTermType = true;
        termTypes = termTypeBean.findAll();
        if (item.getTermType() == null) {
            item.setTermType(new TermType());
        }
    }

    public void editSchoolYear() {
        editSchoolYear = true;
        schoolYears = schoolYearBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        if (item.getSchoolYear() == null) {
            item.setSchoolYear(new SchoolYear());
        }

    }

    public void editofficeHoliday() {
        editofficeHoliday = true;
    }

    public void editTermWeek() {
        edittermWeek = true;
        termWeeks = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        if (item.getTermWeek() == null) {
            item.setTermWeek(new TermWeek());
        }
    }
    public void editTermMonth() {
        edittermMonth = true;
        termMonths = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        if (item.getTermMonth() == null) {
            item.setTermMonth(new TermMonth());
        }
    }

    public void editSchoolWeek() {
        editschoolWeek = true;
        if (item.getSchoolWeek() == null) {
            item.setSchoolWeek(new SchoolWeek());
        }
        if (item.getCurrentTermHalf() == null) {
            schoolWeeks = schoolWeekBean.findByTermId(item.getTermWeek().getId());
        } else {
            schoolWeeks = schoolWeekBean.findByTermIdandTermType(item.getTermWeek().getId(), item.getCurrentTermHalf());
        }

    }
    public void editSchoolMonth() {
        editschoolMonth = true;
        if (item.getSchoolMonth()== null) {
            item.setSchoolMonth(new SchoolMonth());
        }
        if (item.getTermMonth()== null) {
            schoolMonths = schoolMonthBean.findBySchoolYearId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        } else {
            schoolMonths = schoolMonthBean.findByTermId(
                    loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
        }

    }
    
    

    public void editTermHalf() {
        edittermHalf = true;
    }

    public void editFirstID() {
        editfirstID = true;
    }

    public void editWeekTopMark() {
        editweekTopMark = true;
        weekTopMarks = weekTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        if (item.getWeekTopMark() == null) {
            item.setWeekTopMark(new WeekTopMark());
        }
    }

    public void editMonthTopMark() {
        editmonthTopMark = true;
        monthTopMarks = monthTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        if (item.getMonthTopMark() == null) {
            item.setMonthTopMark(new MonthTopMark());
        }
    }
    public void editTerm2TopMark() {
        editterm2TopMark = true;
        term2TopMarks = term2TopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        if (item.getTerm2TopMark()== null) {
            item.setTerm2TopMark(new Term2TopMark());
        }
    }
    public void editTermTopMark() {
        editterm3TopMark = true;
        termTopMarks = termTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        if (item.getTermTopMark()== null) {
            item.setTermTopMark(new TermTopMark());
        }
    }

    public String saveTermTopMark() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveTerm2TopMark() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }
    public String saveMonthTopMark() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        editmonthTopMark=false;
        return "systemVariables?faces-redirect=true";
    }

    public String saveWeekTopMark() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveFirstID() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveTermHalf() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveSchoolWeek() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }
        public String saveSchoolMonth() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String savetTermHalf() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveTermType() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveSchoolYear() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String saveofficeHoliday() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public String savetermMonth() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }
    public String savetermWeek() {
        systemVariablesBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "systemVariables?faces-redirect=true";
    }

    public SystemVariables getItem() {
        return item;
    }

    public void setItem(SystemVariables item) {
        this.item = item;
    }

    public List<SystemVariables> getItems() {
        return items;
    }

    public void setItems(List<SystemVariables> items) {
        this.items = items;
    }

    public boolean isEditTermType() {
        return editTermType;
    }

    public void setEditTermType(boolean editTermType) {
        this.editTermType = editTermType;
    }

    public boolean isEditSchoolYear() {
        return editSchoolYear;
    }

    public void setEditSchoolYear(boolean editSchoolYear) {
        this.editSchoolYear = editSchoolYear;
    }

    public boolean isEditofficeHoliday() {
        return editofficeHoliday;
    }

    public void setEditofficeHoliday(boolean editofficeHoliday) {
        this.editofficeHoliday = editofficeHoliday;
    }

    public boolean isEdittermWeek() {
        return edittermWeek;
    }

    public void setEdittermWeek(boolean edittermWeek) {
        this.edittermWeek = edittermWeek;
    }

    public List<SchoolYear> getSchoolYears() {
        return schoolYears;
    }

    public void setSchoolYears(List<SchoolYear> schoolYears) {
        this.schoolYears = schoolYears;
    }

    public List<TermType> getTermTypes() {
        return termTypes;
    }

    public void setTermTypes(List<TermType> termTypes) {
        this.termTypes = termTypes;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
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

    public boolean isEditschoolWeek() {
        return editschoolWeek;
    }

    public void setEditschoolWeek(boolean editschoolWeek) {
        this.editschoolWeek = editschoolWeek;
    }

    public boolean isEditschoolMonth() {
        return editschoolMonth;
    }

    public void setEditschoolMonth(boolean editschoolMonth) {
        this.editschoolMonth = editschoolMonth;
    }

    public boolean isEdittermHalf() {
        return edittermHalf;
    }

    public void setEdittermHalf(boolean edittermHalf) {
        this.edittermHalf = edittermHalf;
    }

    public boolean isEditfirstID() {
        return editfirstID;
    }

    public void setEditfirstID(boolean editfirstID) {
        this.editfirstID = editfirstID;
    }

    public boolean isEditweekTopMark() {
        return editweekTopMark;
    }

    public void setEditweekTopMark(boolean editweekTopMark) {
        this.editweekTopMark = editweekTopMark;
    }

    public List<WeekTopMark> getWeekTopMarks() {
        return weekTopMarks;
    }

    public void setWeekTopMarks(List<WeekTopMark> weekTopMarks) {
        this.weekTopMarks = weekTopMarks;
    }

    public boolean isEditmonthTopMark() {
        return editmonthTopMark;
    }

    public void setEditmonthTopMark(boolean editmonthTopMark) {
        this.editmonthTopMark = editmonthTopMark;
    }

    public List<MonthTopMark> getMonthTopMarks() {
        return monthTopMarks;
    }

    public void setMonthTopMarks(List<MonthTopMark> monthTopMarks) {
        this.monthTopMarks = monthTopMarks;
    }

    public List<Term2TopMark> getTerm2TopMarks() {
        return term2TopMarks;
    }

    public void setTerm2TopMarks(List<Term2TopMark> term2TopMarks) {
        this.term2TopMarks = term2TopMarks;
    }

    public List<TermTopMark> getTermTopMarks() {
        return termTopMarks;
    }

    public void setTermTopMarks(List<TermTopMark> termTopMarks) {
        this.termTopMarks = termTopMarks;
    }

    public boolean isEditterm2TopMark() {
        return editterm2TopMark;
    }

    public void setEditterm2TopMark(boolean editterm2TopMark) {
        this.editterm2TopMark = editterm2TopMark;
    }

    public boolean isEditterm3TopMark() {
        return editterm3TopMark;
    }

    public void setEditterm3TopMark(boolean editterm3TopMark) {
        this.editterm3TopMark = editterm3TopMark;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

    public boolean isEdittermMonth() {
        return edittermMonth;
    }

    public void setEdittermMonth(boolean edittermMonth) {
        this.edittermMonth = edittermMonth;
    }
    

}
