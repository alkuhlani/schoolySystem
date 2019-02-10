/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.RolesBean;
import com.megasourceye.schooly.ejbs.SchoolBean;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Roles;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author said
 */
@Named(value = "usersMB")
@ViewScoped
public class UsersMB implements Serializable {

    /**
     * Creates a new instance of UsersMB
     */
    public UsersMB() {
    }
    UploadedFile file;
    private boolean edit = false;
    private boolean save = false;
    private boolean editNameEn = false;
    private boolean editNameAr = false;
    private boolean editUserName = false;
    private boolean editPassword = false;
    private boolean editImagePath = false;
    private Users item;
    private List<Users> items;
    private List<School> schools;
    private List<Roles> roles;
    private String oldUsername;
    private String newUsername;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private boolean inUpdate = false;
    private Long userId;

    @Inject
    private SchoolBean schoolBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private RolesBean rolesBean;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = usersBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = usersBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = usersBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }
    }

    public void prepareItem() {
//        if (loginMB.getUser() != null) {
//            if (loginMB.getUser().getRole_().equals("ADMIN")) {
//                items = usersBean.findBySchoolId(loginMB.getUser().getSchool().getId());
//            }
//        } else {
//            items = usersBean.findAll();
//        }

    }

    public void prepareCreate() {
        item = new Users();
        item.setSchool(new School());

        schools = schoolBean.findAll();
        roles = rolesBean.findAll();
    }

    public void prepareUpdate(Long id2) {
        item = usersBean.find(id2);
//        item.setPassword("");
        schools = schoolBean.findAll();
        roles = rolesBean.findAll();
        inUpdate = true;
    }

    public String create() throws NoSuchAlgorithmException {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                item.setPassword(EncodeClass.encode(item.getPassword()));
                usersBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
                return "items?faces-redirect=true";
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item.setPassword(EncodeClass.encode(item.getPassword()));
                item.setSchool(loginMB.getUser().getSchool());
                usersBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
                return "items?faces-redirect=true";
            }
            return "items?faces-redirect=true";
        } else {
            item.setPassword(EncodeClass.encode(item.getPassword()));
            usersBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            return "items?faces-redirect=true";
        }

    }

    public String update() throws NoSuchAlgorithmException {
//        item.setRole_("ADMIN");
//        item.setPassword(EncodeClass.encode(item.getPassword()));
        usersBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        usersBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void prepareUpdateProfile() {
//        item = usersBean.find(Long10);
        edit = false;
        save = false;
        editNameEn = false;
        editNameAr = false;
        editUserName = false;
        editPassword = false;
        editImagePath = false;
        item = usersBean.find(loginMB.getUser().getId());

    }

    public String updateProfile() throws NoSuchAlgorithmException {
        item.setRole_("ADMIN");
        item.setPassword(EncodeClass.encode(item.getPassword()));
        usersBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public void edit() {
        System.out.println("yes");
        edit = true;
    }

    public void save() {
        save = true;
    }

    public void editNameEn() {
        editNameEn = true;
    }

    public void editNameAr() {
        editNameAr = true;
    }

    public void editUserName() {
        editUserName = true;
    }

    public void editPassword() {
        editPassword = true;
        newPassword = new String();
        newPassword2 = new String();
        oldPassword = new String();
    }

    public void editImage() {
        editImagePath = true;
    }

    public String saveNameEn() {
        usersBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "profile?faces-redirect=true";
    }

    public String saveNameAr() {
        usersBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "profile?faces-redirect=true";
    }

    public String saveImage() {
        usersBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "profile?faces-redirect=true";
    }

    public void saveUserName() {
        if (oldUsername.equals(item.getUsername()) && newUsername.isEmpty() == false) {
            item.setUsername(newUsername);
            usersBean.update(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
            reload();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Error Updated"));
        }
    }

    public String reload() {
        System.out.println("1111");

        return "profile?faces-redirect=true";

    }

    public void savePassword() throws NoSuchAlgorithmException {
        if (EncodeClass.encode(oldPassword).equals(item.getPassword()) && newPassword.isEmpty() == false) {
            System.out.println("1");
            item.setPassword(EncodeClass.encode(newPassword));
            System.out.println("2");
            usersBean.update(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
            System.out.println("3");
            reload();
        } else {
            System.out.println("-1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Error Updated"));
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

        File o = new File("E:\\Java2EE\\schoolySystem\\src\\main\\webapp\\resources\\schools\\"
                + loginMB.getUser().getSchool().getId()
                + "\\users");
        String f = "";
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                f = String.valueOf(item.getId());
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                f = String.valueOf(item.getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                f = String.valueOf(item.getTeacher().getTeacherID());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                f = String.valueOf(item.getTeacher().getTeacherID());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                f = String.valueOf(item.getParent().getParentID());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                f = String.valueOf(item.getStudent().getStudentID());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                f = String.valueOf(item.getTeacher().getTeacherID());
            }
        }
        String concat = f.concat(String.valueOf((s.getMonth() + 1)))
                .concat(String.valueOf(c.getWeekYear()))
                .concat(String.valueOf(s.getDate()))
                .concat(String.valueOf(generatedInt));
        File oStream = new File(
                "E:\\Java2EE\\schoolySystem\\src\\main\\webapp\\resources\\schools\\"
                + loginMB.getUser().getSchool().getId()
                + "\\users\\" + concat + "." + ext);
        if (!o.exists()) {
            o.mkdirs();
        }
        item.setImagePath(concat + "." + ext);
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

    }

    public void prepareReset(Long id) {
        inUpdate = true;
        item = usersBean.find(id);
    }

    public void reset() throws NoSuchAlgorithmException {
        item.setPassword(EncodeClass.encode(newPassword));
        usersBean.update(item);
        inUpdate = false;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password Reset Successfully",""));
    }

    public String check() {
        System.out.println(userId);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
            if (!Objects.equals(usersBean.find(userId).getSchool().getId(), loginMB.getUser().getSchool().getId())) {
                return "/access?faces-redirect=true";
            } 
            if (!loginMB.getUser().getRole_().equals("ADMIN")) {
                return "/access?faces-redirect=true";
            }

            return null;
        }
        return "/index?faces-redirect=true";
    }

    public Users getItem() {
        return item;
    }

    public void setItem(Users item) {
        this.item = item;
    }

    public List<Users> getItems() {
        return items;
    }

    public void setItems(List<Users> items) {
        this.items = items;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public boolean isEditNameEn() {
        return editNameEn;
    }

    public void setEditNameEn(boolean editNameEn) {
        this.editNameEn = editNameEn;
    }

    public boolean isEditNameAr() {
        return editNameAr;
    }

    public void setEditNameAr(boolean editNameAr) {
        this.editNameAr = editNameAr;
    }

    public boolean isEditUserName() {
        return editUserName;
    }

    public void setEditUserName(boolean editUserName) {
        this.editUserName = editUserName;
    }

    public boolean isEditPassword() {
        return editPassword;
    }

    public void setEditPassword(boolean editPassword) {
        this.editPassword = editPassword;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isEditImagePath() {
        return editImagePath;
    }

    public void setEditImagePath(boolean editImagePath) {
        this.editImagePath = editImagePath;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public boolean isInUpdate() {
        return inUpdate;
    }

    public void setInUpdate(boolean inUpdate) {
        this.inUpdate = inUpdate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
