/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ActivityBean;
import com.megasourceye.schooly.entities.Activity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author said
 */
@Named(value = "activityMB")
@ViewScoped
public class ActivityMB implements Serializable {

    /**
     * Creates a new instance of ActivityMB
     */
    public ActivityMB() {
    }
    UploadedFile file;
    private Activity item;
    private List<Activity> items;
    private List<Activity> activities;
    private List<Activity> descision;
    private List<Activity> albumImages;

    @Inject
    private ActivityBean activityBean;
    @Inject
    private LoginMB loginMB;
//
//    private StreamedContent image;
//
//    public void DynamicImageController() {
//        InputStream stream = this.getClass().getResourceAsStream("C:\\1\\activity\\10319.jpg");
//        image = new DefaultStreamedContent(stream, "image/jpg");
//    }
//
//    public StreamedContent getImage() {
//        return this.image;
//    }

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = activityBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
//                DynamicImageController();
                items = activityBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                albumImages = activityBean.findBySchoolYearIdAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), '0');
                activities = activityBean.findBySchoolYearIdAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), '1');
                descision = activityBean.findBySchoolYearIdAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), '2');
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                albumImages = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '2', '0');
                albumImages.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '0'));
                activities = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '2', '1');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '1'));
                descision = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '2', '2');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '2'));
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                albumImages = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '3', '0');
                albumImages.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '0'));
                activities = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '3', '1');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '1'));
                descision = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '3', '2');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '2'));
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                albumImages = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '4', '0');
                albumImages.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '0'));
                activities = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '4', '1');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '1'));
                descision = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '4', '2');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '2'));

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                albumImages = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '5', '0');
                albumImages.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '0'));
                activities = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '5', '1');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '1'));
                descision = activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '5', '2');
                activities.addAll(activityBean.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        '0', '2'));
            }
        }

    }

    public void prepareCreate() {
        Calendar c = Calendar.getInstance();

        Date s = new java.util.Date();

        item = new Activity();
        item.setIsActive(true);
        item.setType('0');
        item.setSeenType('0');
        item.setSchoolYear(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        item.setCreation_date(new Date());
    }

    public void prepareUpdate(Long id2) {
        item = activityBean.find(id2);
    }

    public void create() {
        activityBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }

    public String update() {
        activityBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        activityBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void fileUploadListener(FileUploadEvent e) throws IOException, Exception {
        Calendar c = Calendar.getInstance();

        System.out.println("c.getFirstDayOfWeek()" + c.getFirstDayOfWeek());
        System.out.println("c.getCalendarType()" + c.getCalendarType());
        System.out.println("c.getFirstDayOfWeek()" + c.getFirstDayOfWeek());
        System.out.println("c.getTime()" + c.getTime());
        System.out.println("c.getWeekYear()" + c.getWeekYear());
        System.out.println("c.getWeeksInWeekYear()" + c.getWeeksInWeekYear());
        System.out.println("Calendar.DATE" + Calendar.DATE);
        System.out.println("Calendar.DAY_OF_WEEK" + Calendar.DAY_OF_WEEK);
        System.out.println("Calendar.MONTH" + Calendar.MONTH);
        System.out.println("Calendar.DAY_OF_MONTH" + Calendar.DAY_OF_MONTH);
        Date s = new java.util.Date();
        System.out.println("getDate " + s.getDate());
        System.out.println("getMonth " + s.getMonth());
        System.out.println("aaaaa" + loginMB.getUser().getSchool().getId());
        System.out.println(loginMB.getUser().getSchool().getId());
        int generatedInt = new Integer("11111")
                + (int) (Math.random()
                * (new Integer("10000") - new Integer("11111")));
        this.file = e.getFile();
        String filename = file.getFileName();
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());
        System.out.println("-1");
        System.out.println(ext);
        System.out.println("0");
        File o = new File("E:\\Java2EE\\schooly\\src\\main\\webapp\\resources\\schools\\"
                + loginMB.getUser().getSchool().getId()
                + "\\activity");
        String f = String.valueOf(c.getWeekYear());
        String concat = f.concat(String.valueOf((s.getMonth() + 1)))
                .concat(String.valueOf(s.getDate()))
                .concat(String.valueOf(generatedInt));
        File oStream = new File(
                "E:\\Java2EE\\schooly\\src\\main\\webapp\\resources\\schools\\"
                + loginMB.getUser().getSchool().getId()
                + "\\activity\\" + concat + "." + ext);
        if (!o.exists()) {
            o.mkdirs();
        }
        item.setImagePath(concat + "." + ext);
        System.out.println(concat + "." + ext);
        System.out.println("s.getMonth()+" + (s.getMonth() + 1));
        System.out.println("s.getDate()" + s.getDate());
        System.out.println("generatedInt" + generatedInt);
        System.out.println("c.getWeekYear()" + c.getWeekYear());
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

    }

    public Activity getItem() {
        return item;
    }

    public void setItem(Activity item) {
        this.item = item;
    }

    public List<Activity> getItems() {
        return items;
    }

    public void setItems(List<Activity> items) {
        this.items = items;
    }

    public ActivityBean getActivityBean() {
        return activityBean;
    }

    public void setActivityBean(ActivityBean activityBean) {
        this.activityBean = activityBean;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getDescision() {
        return descision;
    }

    public void setDescision(List<Activity> descision) {
        this.descision = descision;
    }

    public List<Activity> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<Activity> albumImages) {
        this.albumImages = albumImages;
    }

}
