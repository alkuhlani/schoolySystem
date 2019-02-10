/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.MessagingBean;
import com.megasourceye.schooly.ejbs.TeacherBean;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Messaging;
import com.megasourceye.schooly.entities.Teacher;
import com.megasourceye.schooly.entities.Users;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "messagingMB")
@ViewScoped
public class MessagingMB implements Serializable {

    /**
     * Creates a new instance of MessagingMB
     */
    public MessagingMB() {
    }
    private Messaging item;
    private Users users;
    private List<Messaging> items;
    private List<Teacher> teachers;
    private long reciverId=-1L;

    @Inject
    private MessagingBean messagingBean;
    @Inject
    private TeacherBean teacherBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {

            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                teachers = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                item=new Messaging();
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            }else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                teachers = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                item=new Messaging();
            }
        }

    }

    public void prepareCreate() {
        item = new Messaging();
    }

    public void prepareUpdate(Long id2) {
        item = messagingBean.find(id2);
    }

    public void create() {
        messagingBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        messagingBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        messagingBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void viewChat(long id) {
        reciverId=id;
        System.out.println("1");
        items = messagingBean.findBySYIdAndSenderIdAndOwnId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                id,
                loginMB.getUser().getId());
        System.out.println("items"+items.size());
        item=new Messaging();
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        System.out.println("getSchoolYear"+loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        item.setSend_date(new Date());
        System.out.println("new Date()"+new Date());
        item.setUsers(usersBean.find(id));
        System.out.println("id"+id);
        item.setUsers1(loginMB.getUser());
        System.out.println("getUser"+loginMB.getUser().getId());
        item.setSeen(false);        
        System.out.println("uyuyuy");
    }
    
    public void sendMessage(){
        System.out.println("ssss");
        System.out.println("item.getText()"+item.getText());
        if(item.getText().length()>0){
            messagingBean.create(item);
        }
        items = messagingBean.findBySYIdAndSenderIdAndOwnId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                item.getUsers().getId(),
                loginMB.getUser().getId());
        item=new Messaging();
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        item.setSend_date(new Date());
        item.setUsers(new Users(reciverId));        
        item.setUsers1(loginMB.getUser());
        item.setSeen(false); 
        System.out.println("iouiuoi");
    }

    public Messaging getItem() {
        return item;
    }

    public void setItem(Messaging item) {
        this.item = item;
    }

    public List<Messaging> getItems() {
        return items;
    }

    public void setItems(List<Messaging> items) {
        this.items = items;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public long getReciverId() {
        return reciverId;
    }

    public void setReciverId(long reciverId) {
        this.reciverId = reciverId;
    }

}
