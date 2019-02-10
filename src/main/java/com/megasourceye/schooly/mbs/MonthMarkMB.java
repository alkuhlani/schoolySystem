/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.AttendanceBean;
import com.megasourceye.schooly.ejbs.MonthMarkBean;
import com.megasourceye.schooly.ejbs.SchoolMonthBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.entities.Attendance;
import com.megasourceye.schooly.entities.MonthMark;
import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.TeacherAssign;
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
@Named(value = "monthMarkMB")
@ViewScoped
public class MonthMarkMB implements Serializable {

    /**
     * Creates a new instance of MonthMarkMB
     */
    public MonthMarkMB() {
    }

    private MonthMark item;
    private List<MonthMark> items;
    private List<SchoolMonth> schoolMonths;
    private List<TeacherAssign> teacherAssigns;
    private List<StudentSchool> studentSchools;
    private List<TermMonth> termMonths;
    private List<Attendance> attendances;
    private TermMonth termMonth;

    @Inject
    private MonthMarkBean monthMarkBean;
    @Inject
    private SchoolMonthBean schoolMonthBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private AttendanceBean attendanceBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
//                items = weekMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = monthMarkBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = monthMarkBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = monthMarkBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = monthMarkBean.findBySchoolYearIdAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new MonthMark();
        termMonth = new TermMonth();

        if (loginMB.getUser().getSchool().getSystemVariables().getTermMonth() != null) {
            termMonth = new TermMonth(
                    loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
            schoolMonths = schoolMonthBean.findByTermId(
                    loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
        }
        if (loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth() != null) {
            item.setSchoolMonth(new SchoolMonth());
            item.setSchoolMonth(
                    new SchoolMonth(loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getId()));
        } else {
            item.setSchoolMonth(new SchoolMonth());
        }
        termMonths = termMonthBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println();
        System.out.println();

        item.setStudentSchool(new StudentSchool());
        item.setTeacherAssign(new TeacherAssign());
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                schoolMonths = schoolMonthBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                schoolMonths = schoolMonthBean.findByTermId(loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
                teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());

            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth() != null) {
                    schoolMonths = schoolMonthBean.findByTermId(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
                } else {
                    schoolMonths = schoolMonthBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                }
                teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth() != null) {
                    schoolMonths = schoolMonthBean.findByTermId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getId());
                } else {
                    schoolMonths = schoolMonthBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                }
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            }
        }
    }

    public void prepareCreate(Long schoolMonthId, Long termMonthId, Long taecherAssignId, Long studentId) {
        item = new MonthMark();
        termMonth = new TermMonth(termMonthId);
        schoolMonths = schoolMonthBean.findByTermId(termMonthId);
        termMonths = termMonthBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println(taecherAssignId);
        System.out.println(studentId);

        item.setSchoolMonth(new SchoolMonth());
        if (loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth() != null) {
            item.setSchoolMonth(loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth());
        } else {
            item.setSchoolMonth(new SchoolMonth());
        }
        item.setStudentSchool(new StudentSchool(studentId));
        item.setTeacherAssign(new TeacherAssign(taecherAssignId));
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                schoolMonths = schoolMonthBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                schoolMonths = schoolMonthBean.findByTermId(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
                teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        item.getTeacherAssign().getClass_().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek() != null) {
                    schoolMonths = schoolMonthBean.findByTermId(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
                } else {
                    schoolMonths = schoolMonthBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                }
                teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() != null) {
                    schoolMonths = schoolMonthBean.findByTermId(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
                } else {
                    schoolMonths = schoolMonthBean.findBySchoolYearId(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                }
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            }
        }
    }

    public void studentListener() {

        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                teacherAssignBean.find(item.getTeacherAssign().getId()).getClass_().getId());
        System.out.println("teacherAssignBean.find(item.getTeacherAssign().getId()).getClass_().getId()" + teacherAssignBean.find(item.getTeacherAssign().getId()).getClass_().getId());
    }

    public void calcAttendance() {
        int ab = 0;
        int at = 0;
        int m = 0;
        int to = 20;
        attendances = attendanceBean.findByStudentSIdAnd2Date(item.getStudentSchool().getId(),
                item.getSchoolMonth().getFirstDate(),
                item.getSchoolMonth().getEndDate());
        int g = attendances.size();
        System.out.println(g);
        for (int i = 0; i < g; i++) {

            if (attendances.get(i).getStatus() == '1') {
                at++;
            } else if (attendances.get(i).getStatus() == '0') {
                ab++;
            } else if (attendances.get(i).getStatus() == '2') {
                m++;
            }

        }
        if (m >= 3) {
            to--;
        }
        for (int j = 0; j < ab; j++) {
            to--;
        }
        item.setBehavior(to * 1.0F);
        System.out.println(item.getBehavior());
    }

    public void weekListener() {
        System.out.println("3222");
        schoolMonths = schoolMonthBean.findByTermId(termMonth.getId());
    }

    public void prepareUpdate(Long id2) {
        item = monthMarkBean.find(id2);
    }

    public void create() {
        System.out.println("1");
        if (monthMarkBean.findByStudentSIdAndTeacherAssignAndSchoolW(
                item.getStudentSchool().getId(),
                item.getTeacherAssign().getId(),
                item.getSchoolMonth().getId()).size() >= 1) {
            System.out.println("1.1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "is Founded"));

        } else {

            monthMarkBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            prepareCreate(
                    item.getSchoolMonth().getId(), 
                    termMonth.getId(), 
                    item.getTeacherAssign().getId(), 
                    item.getStudentSchool().getId());
        }

        //  return "items?faces-redirect=true";
    }

    public String update() {
        monthMarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        monthMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public MonthMark getItem() {
        return item;
    }

    public void setItem(MonthMark item) {
        this.item = item;
    }

    public List<MonthMark> getItems() {
        return items;
    }

    public void setItems(List<MonthMark> items) {
        this.items = items;
    }

    public List<SchoolMonth> getSchoolMonths() {
        return schoolMonths;
    }

    public void setSchoolMonths(List<SchoolMonth> schoolMonths) {
        this.schoolMonths = schoolMonths;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public TermMonth getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(TermMonth termMonth) {
        this.termMonth = termMonth;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

}
