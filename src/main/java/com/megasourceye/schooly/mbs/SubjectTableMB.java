/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.DayBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.SubjectTableBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.TeacherBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.DT;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.SubjectTable;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.Teacher;
import com.megasourceye.schooly.entities.TeacherAssign;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author said
 */
@Named(value = "subjectTableMB")
@ViewScoped
public class SubjectTableMB implements Serializable {

    /**
     * Creates a new instance of SubjectTableMB
     */
    public SubjectTableMB() {
    }
    private SubjectTable item;
    private TeacherAssign teacherAssign;
    private List<SubjectTable> items;
    private List<SubjectTable> selectedSubjectTable;
    private List<TeacherAssign> teacherAssigns;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private List<Teacher> teachers;
    private List<DT> days;
    private long subjectId;
    private long classId;
    UploadedFile file;
    private long teacherId;
    private long dayId;
    private char LectureNo;
    private long LectureNoLong;
    private String lectureNoString;

    @Inject
    private SubjectTableBean subjectTableBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private TeacherBean teacherBean;
    @Inject
    private DayBean dayBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = subjectTableBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = subjectTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = subjectTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = subjectTableBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = subjectTableBean.findBySchoolYearIdAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
            }
        }

    }

    public void prepareAllItems2() {
//        items = schoolBean.findAll();
        items = subjectTableBean.findBySchoolId(100L);
    }

    public void prepareCreate() {
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        item = new SubjectTable();
        item.setTeacherAssign(new TeacherAssign());
        item.setDay(new DT());
//        teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        days = dayBean.findAll();

    }

    public void prepareCreate(long classId, long subjectId, long teacherAssginId, long dayId, char lectureNo) {
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        item = new SubjectTable();
        item.setTeacherAssign(new TeacherAssign(teacherAssginId));
        item.setDay(new DT(dayId));
        item.setLectureNo(lectureNo);
//        teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        days = dayBean.findAll();
        teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassIdAndSubjectId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                classId,
                subjectId);

    }

    public void prepareUpdate(Long id2) {
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        days = dayBean.findAll();
        item = subjectTableBean.find(id2);
        teacherAssigns = teacherAssignBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        teacherAssign = teacherAssignBean.find(item.getTeacherAssign().getId());
        classId = item.getTeacherAssign().getClass_().getId();
        subjectId = teacherAssign.getSubject_t().getId();
        days = dayBean.findAll();
    }

    public void create() {

        System.out.println(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        System.out.println(item.getTeacherAssign().getId());
        System.out.println(item.getDay().getId());
        System.out.println(item.getLectureNo());

        if (subjectTableBean.findBySchoolYearIdAndTeacherAIdAndDayIdAndLectureNo(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                item.getTeacherAssign().getId(),
                item.getDay().getId(),
                item.getLectureNo()).size() >= 1) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Record is Exist"));

        } else {
            subjectTableBean.create(item);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        }
        prepareCreate(
                classId,
                subjectId,
                item.getTeacherAssign().getId(),
                item.getDay().getId(),
                item.getLectureNo());
        //  return "items?faces-redirect=true";
    }

    public String createItemsFromExcel() {
        for (int i = 0; i < items.size(); i++) {
            subjectTableBean.create(items.get(i));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }

    public String update() {
        subjectTableBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        item.setStateDeActive(true);
        subjectTableBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void subjectTableListner() {
        if (classId > 0 && subjectId > 0) {
            teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassIdAndSubjectId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId,
                    subjectId);
            item.setTeacherAssign(teacherAssigns.get(0));
        }
    }

    public void fileUploadListener(FileUploadEvent e) throws IOException, Exception {
        Calendar c = Calendar.getInstance();

        Date s = new java.util.Date();

        int generatedInt = new Integer("11")
                + (int) (Math.random()
                * (new Integer("10") - new Integer("11")));
        this.file = e.getFile();
        String filename = file.getFileName();
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());
        System.out.println("-1");
        System.out.println(ext);
        System.out.println("0");
        File o = new File("C:\\importFilesXlsxCsv\\");
        String f = "";

        String concat = f.concat(String.valueOf((s.getMonth() + 1)))
                .concat(String.valueOf(c.getWeekYear()))
                .concat(String.valueOf(s.getDate()))
                .concat(String.valueOf(generatedInt));
        File oStream = new File(
                "C:\\importFilesXlsxCsv\\" + concat + "." + ext);
        if (!o.exists()) {
            o.mkdirs();
        }

        System.out.println(concat + "." + ext);

        System.out.println("3");
        oStream.createNewFile();
        System.out.println("4");
        Long sss = file.getSize();
        System.out.println("5");
        InputStream inputStream = file.getInputstream();
        System.out.println("6");
        try (FileOutputStream outputStream = new FileOutputStream(oStream)) {
            System.out.println("7");
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, lenght);
            }
        }

        float ff = (int) file.getSize() / 1024;
        System.out.println("8");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Uploaded Successfully -" + ff + "KB"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "File Uploaded Successfully"));

        String extension = file.getContentType();
        Long size1 = file.getSize();
        System.out.println(size1.toString());
        System.out.println(filename);
        System.out.println(extension);

        System.out.println("getPath : " + oStream.getPath());
        System.out.println("getPath : " + oStream.getPath());
        System.out.println("getPath : " + oStream.getPath());
        InputStream ExcelFileToRead = new FileInputStream(oStream.getPath());
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            item = new SubjectTable();
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                System.out.println("cell.getColumnIndex" + cell.getColumnIndex());
                if (cell.getColumnIndex() == 0) {
                    teacherId = (long) cell.getNumericCellValue();
                    System.out.println("teacherId" + teacherId);
                } else if (cell.getColumnIndex() == 1) {
                    classId = (long) cell.getNumericCellValue();
                    System.out.println("classId" + classId);
                } else if (cell.getColumnIndex() == 2) {
                    subjectId = (long) cell.getNumericCellValue();
                    System.out.println("subjectId" + subjectId);
                } else if (cell.getColumnIndex() == 3) {
                    dayId = (long) cell.getNumericCellValue();
                    System.out.println("dayId" + dayId);
                } else if (cell.getColumnIndex() == 4) {
                    lectureNoString = cell.getStringCellValue();
                    //LectureNo = lectureNoString.charAt(0);
                    System.out.println("lectureNoString" + lectureNoString);
                    LectureNo = lectureNoString.charAt(0);
                    System.out.println("LectureNo" + LectureNo);
                }
            }

            if (teacherAssignBean.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId,
                    teacherId,
                    subjectId).size() <= 0) {
                System.out.println("ERRORERRORERRORERRORERRORERRORERRORERRORERRORERROR");
                teacherAssign = new TeacherAssign();
                teacherAssign.setClass_(new Class_(classId));
                teacherAssign.setTeacher(new Teacher(teacherId));
                teacherAssign.setSubject_t(new Subject_t(subjectId));
                teacherAssign.setSchoolYear(new SchoolYear(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId()));
                teacherAssignBean.create(teacherAssign);
                item.setTeacherAssign(teacherAssign);
                item.setDay(dayBean.find(dayId));
                item.setLectureNo(LectureNo);
                item.setStateDeActive(false);
                System.out.println("...............................................");
                items.add(item);
            } else {
                System.out.println("TRUETRUETRUETRUETRUETRUETRUETRUETRUETRUETRUETRUETRUE");
                teacherAssign = teacherAssignBean.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        teacherId,
                        subjectId).get(0);
                if (teacherAssignBean.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        teacherId,
                        subjectId).size() <= 0) {
                    System.out.println("ERRORERRORERRORERRORERRORERRORERRORERRORERRORERROR");
                }
                System.out.println("teacherAssign" + teacherAssign.getId());
                item.setTeacherAssign(teacherAssign);
                item.setDay(dayBean.find(dayId));
                item.setLectureNo(LectureNo);
                item.setStateDeActive(false);
                System.out.println("...............................................");
                items.add(item);
            }

        }
    }

    public SubjectTable getItem() {
        return item;
    }

    public void setItem(SubjectTable item) {
        this.item = item;
    }

    public List<SubjectTable> getItems() {
        return items;
    }

    public void setItems(List<SubjectTable> items) {
        this.items = items;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<DT> getDays() {
        return days;
    }

    public void setDays(List<DT> days) {
        this.days = days;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<Subject_t> getSubject_ts() {
        return subject_ts;
    }

    public void setSubject_ts(List<Subject_t> subject_ts) {
        this.subject_ts = subject_ts;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public char getLectureNo() {
        return LectureNo;
    }

    public void setLectureNo(char LectureNo) {
        this.LectureNo = LectureNo;
    }

    public List<SubjectTable> getSelectedSubjectTable() {
        return selectedSubjectTable;
    }

    public void setSelectedSubjectTable(List<SubjectTable> selectedSubjectTable) {
        this.selectedSubjectTable = selectedSubjectTable;
    }

    public long getLectureNoLong() {
        return LectureNoLong;
    }

    public void setLectureNoLong(long LectureNoLong) {
        this.LectureNoLong = LectureNoLong;
    }

    public String getLectureNoString() {
        return lectureNoString;
    }

    public void setLectureNoString(String lectureNoString) {
        this.lectureNoString = lectureNoString;
    }

}
