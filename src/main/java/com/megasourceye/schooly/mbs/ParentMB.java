/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.ParentBean;
import com.megasourceye.schooly.ejbs.SendSMS;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Parent;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Users;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
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
@Named(value = "parentMB")
@ViewScoped
public class ParentMB implements Serializable {

    /**
     * Creates a new instance of ParentMB
     */
    public ParentMB() {
    }

    private Parent item;
    private List<Parent> items;
    private Users user;

    @Inject
    private ParentBean parentBean;
    @Inject
    private UsersBean usersBean;    
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().endsWith("ADMINISTRATOR")) {
                items = parentBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {

            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }
        
    }

    public void prepareCreate() {
        item = new Parent();
        item.setSchool(new School());
    }

    public void prepareUpdate(Long id2) {
        item = parentBean.find(id2);
    }

    public void create() {
        items = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        int s = items.size();
        if (s >= 1) {
            Long i = items.get(s - 1).getParentID();
            item.setParentID(i + 1);
        } else {
            /*int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
            Date datei = new Date();
            String d=String.valueOf(datei.getYear())
                    +String.valueOf(datei.getMonth())
                    +String.valueOf(datei.getDay()
                    +String.valueOf(generatedInt));
            
            item.setParentID(new Long(d));*/
            String ParentID=loginMB.getUser().getSchool().getId().toString();
            ParentID=ParentID.concat("3").concat("10000");
            item.setParentID(new Long(ParentID));
        }
//        java.util.Date dt = new java.util.Date();
        item.setSchool(loginMB.getUser().getSchool());
        parentBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        parentBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        parentBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }
    public String createUser(Long id) throws NoSuchAlgorithmException, SOAPException {
        user = new Users();
        item = parentBean.find(id);
//        user.setStudent(item);
        user.setName_ar(item.getName_ar());
        user.setName_en(item.getName_en());
        String o=item.getParentID().toString();
        if(usersBean.findByUsername(o).size()>=1){
            
            int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
            Date datei = new Date();
            String d=String.valueOf(datei.getYear())
                    +String.valueOf(datei.getMonth())
                    +String.valueOf(datei.getDay()
                    +String.valueOf(generatedInt));
            user.setUsername(d);
        }else
        {
            user.setUsername(item.getParentID().toString());
        }
        user.setPassword(EncodeClass.encode(item.getPhone1()));
        user.setRole_("PARENT");
        user.setPhone(item.getPhone1());
        user.setSchool(item.getSchool());
        usersBean.create(user);
        item.setUser(new Users());
        item.setUser(user);
        parentBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", id.toString()));
        SendSMS.setPhoneAndSMS("967".concat(user.getPhone()),
                "مرحبا بك في نظام وتطبيق مدرستي"+"\n"+"اسم المستخدم :"+item.getParentID()+"\n"+"كلمة السر :"+item.getPhone1());
        return "items?faces-redirect=true";
    }

    public Parent getItem() {
        return item;
    }

    public void setItem(Parent item) {
        this.item = item;
    }

    public List<Parent> getItems() {
        return items;
    }

    public void setItems(List<Parent> items) {
        this.items = items;
    }

    

}
