/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.AttendanceBean;
import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.DepartmentBean;
import com.megasourceye.schooly.ejbs.SchoolWeekBean;
import com.megasourceye.schooly.ejbs.StudentParentBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.ejbs.VacationBean;
import com.megasourceye.schooly.entities.Attendance;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.SchoolWeek;
import com.megasourceye.schooly.entities.StudentParent;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.TeacherAssign;
import com.megasourceye.schooly.entities.TermWeek;
import com.megasourceye.schooly.entities.Vacation;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "attendanceMB")
@ViewScoped
public class AttendanceMB implements Serializable {

    /**
     * Creates a new instance of AttendanceMB
     */
    public AttendanceMB() {
    }

    private Attendance item;
    private StudentSchool studentSchool;
    private Vacation vacation;
    private List<Attendance> items;
    private List<Attendance> todayAttendance;
    private List<StudentSchool> studentSchools;
    private List<StudentSchool> selectedStudentSchools;
    private List<Class_> class_s;
    private List<TeacherAssign> teacherAssigns;
    private List<StudentParent> studentParents;
    private List<Vacation> vacations;
    private List<SchoolWeek> schoolWeeks;
    private List<TermWeek> termWeeks;
    private String dateNow = new java.util.Date().toString();
    private Date attendanceDate = new Date();
    private int searchMode = 1;
    private long classId;
    private long studentId;
    private Date startDate;
    private Date endDate;
    private long weekId;
    private long termId;

    @Inject
    private AttendanceBean attendanceBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private DepartmentBean departmentBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private StudentParentBean studentParentBean;
    @Inject
    private VacationBean vacationBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private SchoolWeekBean schoolWeekBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private LnsMB lnsMB;

    public void prepareItems() {
        System.out.println("1");
        System.out.println("1");
        if (loginMB.getUser() != null) {
            System.out.println("2");
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("3");
                items = attendanceBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = attendanceBean.findBySchoolYearIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = attendanceBean.findBySchoolYearIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = attendanceBean.findBySchoolYearIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                studentSchool = new StudentSchool();
                studentParents = studentParentBean.findByParentId(
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = attendanceBean.findByStudentIdAndDate(
                        loginMB.getUser().getStudent().getId(),
                        new java.util.Date());
//                studentParents = studentParentBean.findByParentId(loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                items = attendanceBean.findBySchoolYearIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
//        items = attendanceBean.findAll();
//        items = attendanceBean.findByStudentIdAndDate(new Long("4"), new java.util.Date());
    }

//term
    public void prepareAllItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = attendanceBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getEndDate());
                } else {
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() == '1') {

                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst2Date());
                    } else {
                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd2Date());
                    }

                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getEndDate());
                } else {
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() == '1') {
                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst2Date());
                    } else {
                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd2Date());
                    }
                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getEndDate());
                } else {
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() == '1') {
                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst2Date());
                    } else {
                        items = attendanceBean.findBySchoolYearIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd2Date());
                    }
                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findByStudentSIdAnd2Date(
                            loginMB.getUser().getStudent().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getEndDate());
                } else {
                    if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() == '1') {
                        items = attendanceBean.findByStudentSIdAnd2Date(
                                loginMB.getUser().getStudent().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getFirst2Date());
                    } else {
                        items = attendanceBean.findByStudentSIdAnd2Date(
                                loginMB.getUser().getStudent().getId(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd1Date(),
                                loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getEnd2Date());
                    }
                }

            }
        }
    }

    public void prepareAll2Items() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = attendanceBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getEndDate());
                } else {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getEndDate());
                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getEndDate());
                } else {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getEndDate());
                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getEndDate());
                } else {
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getEndDate());
                }
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == '1') {
                    items = attendanceBean.findByStudentSIdAnd2Date(
                            loginMB.getUser().getStudent().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolMonth().getEndDate());
                } else {
                    items = attendanceBean.findByStudentSIdAnd2Date(
                            loginMB.getUser().getStudent().getId(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getFirstDate(),
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getEndDate());
                }
            }
        }
    }

    public void prepareClasses() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                class_s = classBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
        for (int i = 0; i < class_s.size(); i++) {
            if(attendanceBean.findBySchoolYearIdAndClassIdAndDate(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    class_s.get(i).getId(),
                    attendanceDate).size()>=1){
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", 
                                class_s.get(i).getName_en()+" has been attended"));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", 
                                class_s.get(i).getName_en()+" has not been attended"));
            }
        }

    }

    public void prepareStudentByClass(Long id) {
        dateNow = new java.util.Date().toString();
        attendanceDate = new Date();
//            studentSchools = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                id);

        for (int i = 0; i < studentSchools.size(); i++) {
            if (vacationBean.findBySchoolyYearIdAndStudentIdAndTrue(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    studentSchools.get(i).getId(),
                    attendanceDate).size() >= 1) {
                studentSchools.get(i).setSubClass('1');
                System.out.println(studentSchools.get(i).getStudent().getStudentID());
            }
//                    studentSchools.get(i).setId(id);
        }

    }

    public void prepareCreate() {
        item = new Attendance();

    }

    public void prepareUpdate(Long id2) {
        item = attendanceBean.find(id2);
    }

    public void studentListener() {
        System.out.println(studentSchool.getId());
        items = attendanceBean.findByStudentIdAndDate(
                studentSchool.getStudent().getId(),
                new java.util.Date());
    }

    public String create() throws InterruptedException {
        System.out.println(attendanceDate);
        int s = selectedStudentSchools.size();
        System.out.println("1");
        System.out.println(s);
        for (int i = 0; i < s; i++) {
            System.out.println(i);
            todayAttendance = attendanceBean.findByStudentIdAndDate(
                    selectedStudentSchools.get(i).getId(), attendanceDate);
            System.out.println(todayAttendance.size());
            if (todayAttendance.size() <= 0) {
                System.out.println("2222222");
                System.out.println("2222222");
                System.out.println("2222222");
                item = new Attendance();
                System.out.println("a");
                vacations = vacationBean.findBySchoolyYearIdAndStudentIdAndTrue(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        selectedStudentSchools.get(i).getId(),
                        attendanceDate);
                System.out.println("B");
                System.out.println("b" + vacations.size());
                if (vacations.size() >= 1) {
//                    System.out.println("c");
//                    vacation=new Vacation();
//                    vacation=vacationBean.find(vacations.get(0).getId());
//                    System.out.println("d");
//                    int p=vacation.getActive_day()+1;
//                    System.out.println("e");
//                    String o =String.valueOf(p);
//                    System.out.println("f");
//                    vacation.setActive_day(Short.valueOf(o));
//                    System.out.println("g");
//                    vacationBean.update(vacation);
//                    item.setStatus('2');
//                    FacesContext.getCurrentInstance().addMessage(
//                        null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                                "Info", "AUTORAIZED "
//                                + selectedStudentSchools.get(i).getStudent().getName_en()));
                } else {
                    item.setStatus('1');
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Info", "ATEND "
                                    + selectedStudentSchools.get(i).getStudent().getName_en()));
                }
                item.setStudentSchool(selectedStudentSchools.get(i));

                item.setAttendanceDate(attendanceDate);
                attendanceBean.create(item);
                System.out.println("ppppppp");
                System.out.println("ppppppp");
                System.out.println("ppppppp");
                System.out.println("ppppppp");

            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Info", "ATEND "
                                + selectedStudentSchools.get(i).getStudent().getName_en()
                                + " BEFORE"));
            }
        }
        System.out.println("2");
        int q = studentSchools.size();
        System.out.println(q);
        for (int i = 0; i < q; i++) {
            todayAttendance = attendanceBean.findByStudentIdAndDate(
                    studentSchools.get(i).getId(), attendanceDate);
            if (todayAttendance.size() <= 0) {
                item = new Attendance();
                vacations = vacationBean.findBySchoolyYearIdAndStudentIdAndTrue(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchools.get(i).getId(),
                        attendanceDate);
                if (vacations.size() >= 1) {
                    vacation = new Vacation();
                    vacation = vacationBean.find(vacations.get(0).getId());
                    int p = vacation.getActive_day() + 1;
                    String o = String.valueOf(p);
                    vacation.setActive_day(Short.valueOf(o));
                    vacationBean.update(vacation);
                    item.setStatus('2');
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "AUTORAIZED"
                                    + studentSchools.get(i).getStudent().getName_en()));
                } else {
                    item.setStatus('0');
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "ABSENT"
                                    + studentSchools.get(i).getStudent().getName_en()));
                }
                item.setStudentSchool(studentSchools.get(i));

                item.setAttendanceDate(attendanceDate);
                attendanceBean.create(item);

            } else {

            }
        }
        System.out.println("3");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", ("en".equals(lnsMB.getLanguage())) ? "Successfully Saved" : "تم الحفظ بنجاح"));
//        Thread.sleep(5000);
        return "items_class?faces-redirect=true";

    }

    public void dateListner() {
        System.out.println(attendanceDate);
    }

    public boolean checkIfClassAtta(Long id) {
        if (attendanceBean.findByClassIdAndDate(id, new java.util.Date()).size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public String update() {
        attendanceBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String checnageToAbsent(Long id) {
        item = attendanceBean.find(id);
        item.setStatus('0');
        attendanceBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String checnageToPresent(Long id) {
        item = attendanceBean.find(id);
        item.setStatus('1');
        attendanceBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String checnageToAuthorized(Long id) {
        item = attendanceBean.find(id);
        item.setStatus('2');
        attendanceBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String delete() {
        attendanceBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void studentListner() {
        if (classId > 0) {
            if (loginMB.getUser().getRole_().equals("ADMIN")) {
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId);
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId);
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId);
            }
        } else {
            studentSchools = null;
        }

    }

    public void weekListner() {
        if (termId > 0) {
            schoolWeeks = schoolWeekBean.findByTermId(termId);
        }
    }

    public void search() {
        System.out.println("search");
        switch (searchMode) {
            case 1:
                System.out.println("1");
                if (classId == -1) {
                    System.out.println("classId -1");
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            startDate,
                            endDate);
                } else {
                    System.out.println("classId " + classId);
                    if (studentId == -1) {
                        System.out.println("studentId " + studentId);
                        items = attendanceBean.findBySchoolYearIdAndClassIdAnd2Date(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                classId,
                                startDate,
                                endDate);
                    } else {
                        System.out.println("studentId " + studentId);
                        items = attendanceBean.findByStudentSIdAnd2Date(
                                studentId,
                                startDate,
                                endDate);
                    }
                }
                break;
            case 2:
                System.out.println("2");
                if (weekId == -1) {
                    System.out.println("weekId " + weekId);
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            termWeekBean.find(termId).getFirst1Date(),
                            termWeekBean.find(termId).getEnd1Date());
                    items.addAll(attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            termWeekBean.find(termId).getFirst2Date(),
                            termWeekBean.find(termId).getEnd2Date()));
                } else {

                    System.out.println("weekId " + weekId);
                    items = attendanceBean.findBySchoolYearIdAnd2Date(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            schoolWeekBean.find(weekId).getFirstDate(),
                            schoolWeekBean.find(weekId).getEndDate());
                }
                break;

        }
    }

    public void setSearchModeValue(int value) {
        searchMode = value;

        if (searchMode == 1) {

        } else if (searchMode == 2) {
            termWeeks = termWeekBean.findBySchoolYearId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        }
    }

    public Attendance getItem() {
        return item;
    }

    public void setItem(Attendance item) {
        this.item = item;
    }

    public List<Attendance> getItems() {
        return items;
    }

    public void setItems(List<Attendance> items) {
        this.items = items;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<StudentSchool> getSelectedStudentSchools() {
        return selectedStudentSchools;
    }

    public void setSelectedStudentSchools(List<StudentSchool> selectedStudentSchools) {
        this.selectedStudentSchools = selectedStudentSchools;
    }

    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

    public List<Attendance> getTodayAttendance() {
        return todayAttendance;
    }

    public void setTodayAttendance(List<Attendance> todayAttendance) {
        this.todayAttendance = todayAttendance;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<StudentParent> getStudentParents() {
        return studentParents;
    }

    public void setStudentParents(List<StudentParent> studentParents) {
        this.studentParents = studentParents;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(int searchMode) {
        this.searchMode = searchMode;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getWeekId() {
        return weekId;
    }

    public void setWeekId(long weekId) {
        this.weekId = weekId;
    }

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

    public List<SchoolWeek> getSchoolWeeks() {
        return schoolWeeks;
    }

    public void setSchoolWeeks(List<SchoolWeek> schoolWeeks) {
        this.schoolWeeks = schoolWeeks;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

}
