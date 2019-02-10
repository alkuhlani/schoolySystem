/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.SendSMS;
import com.megasourceye.schooly.ejbs.TeacherBean;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Teacher;
import com.megasourceye.schooly.entities.Users;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.xml.soap.SOAPException;

/**
 *
 * @author said
 */
@Named(value = "teacherMB")
@ViewScoped
public class TeacherMB implements Serializable {

    /**
     * Creates a new instance of TeacherMB
     */
    public TeacherMB() {
    }

    private Teacher item;
    private List<Teacher> items;
    private Users user;

    @Inject
    private TeacherBean teacherBean;
    @Inject
    private UsersBean usersBean;

    @Inject
    private LoginMB loginMB;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = teacherBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }

    }

    public void prepareCreate() {
        item = new Teacher();
        item.setIsTeacher(true);

    }

    public void prepareUpdate(Long id) {
        item = teacherBean.find(id);
    }

    public void create() {
        int s = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId()).size();

        if (s >= 1) {
            Long i = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId()).get(s - 1).getTeacherID();
            item.setTeacherID(i + 1);
        } else {
            /*int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
            Date datei = new Date();
            String d=String.valueOf(datei.getYear())
                    +String.valueOf(datei.getMonth())
                    +String.valueOf(datei.getDay()
                    +String.valueOf(generatedInt));
            
            item.setTeacherID(new Long(d));*/
            String TeacherID = loginMB.getUser().getSchool().getId().toString();
            TeacherID = TeacherID.concat("1").concat("10000");
            item.setTeacherID(new Long(TeacherID));
        }
        item.setSchool(loginMB.getUser().getSchool());

        teacherBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        teacherBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        teacherBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public String createUser(Long id) throws NoSuchAlgorithmException, SOAPException {
        System.out.println("1");
        user = new Users();
        System.out.println("2");
        item = teacherBean.find(id);
        System.out.println("3");
//        user.setStudent(item);
        user.setPhone(item.getPhone1());
        System.out.println("4");
        user.setName_ar(item.getName_ar());
        System.out.println("5");
        user.setName_en(item.getName_en());
        System.out.println("6");
        String o = item.getTeacherID().toString();
        System.out.println("7");
        if (usersBean.findByUsername(o).size() >= 1) {
            System.out.println("8");
            int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
//            Date datei = new Date();
            String d = item.getTeacherID().toString().concat(String.valueOf(generatedInt));
            System.out.println("9");
            user.setUsername(d);
            System.out.println("10");
        } else {
            System.out.println("11");
            user.setUsername(item.getTeacherID().toString());
        }
        System.out.println("12");
        user.setPassword(EncodeClass.encode(item.getPhone1()));
//        user.setRole_("TEACHER");
        System.out.println("13");
        if (item.getIsEmployee() && item.getIsTeacher()) {
            user.setRole_("TEA-EMP");
        }
        if (item.getIsEmployee() && !item.getIsTeacher()) {
            user.setRole_("EMPLOYEE");
        }
        if (!item.getIsEmployee() && item.getIsTeacher()) {
            user.setRole_("TEACHER");
        }
        user.setSchool(item.getSchool());
        user.setPhone(item.getPhone1());
        usersBean.create(user);
        item.setUser(new Users());
        item.setUser(user);
        teacherBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", id.toString()));
//        SendSMS.setPhoneAndSMS("967".concat(user.getPhone()),
//                "مرحبا بك في نظام وتطبيق مدرستي"+"\n"+"اسم المستخدم :"+item.getTeacherID()+"\n"+"كلمة السر :"+item.getPhone1());
        return "items?faces-redirect=true";
    }

    public Teacher getItem() {
        return item;
    }

    public void setItem(Teacher item) {
        this.item = item;
    }

    public List<Teacher> getItems() {
        return items;
    }

    public void setItems(List<Teacher> items) {
        this.items = items;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
