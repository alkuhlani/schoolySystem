/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.ParentBean;
import com.megasourceye.schooly.ejbs.SendSMS;
import com.megasourceye.schooly.ejbs.StudentBean;
import com.megasourceye.schooly.ejbs.TermBean;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Parent;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.Term;
import com.megasourceye.schooly.entities.Users;
import com.megasourceye.schooly.mbs.utls.JasperManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.xml.soap.SOAPException;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author said
 */
@Named(value = "studentMB")
@ViewScoped
public class StudentMB implements Serializable {

    /**
     * Creates a new instance of StudentMB
     */
    public StudentMB() {
    }
    
    private Student item;
    private List<Student> items;
    private List<Student> selectedStudentSchools;
    private Users user;
    private Parent parent;
    private List<Parent> parents;
    UploadedFile file;
    private boolean createSee;
    
    @Inject
    private StudentBean studentBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private ParentBean parentBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private JasperManager jasperManager;
    
    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = studentBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                
            }
        }
        
    }
    
    public void prepareAllItems() {
//        items = schoolBean.findAll();
        items = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public void prepareAllItems2() {
        items = studentBean.findBySchoolId(-10L);
    }
    
    public void prepareAllParents(Long studentId) {
//        items = schoolBean.findAll();
        parents = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println("ssssstudent" + studentId);
        System.out.println("1");
        item = studentBean.find(studentId);
    }
    
    public void prepareCreate() {
        item = new Student();
    }
    
    public void prepareUpdate(Long id2) {
        item = studentBean.find(id2);
    }
    
    public void create() {
        items = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        int s = items.size();
        if (s >= 1) {
            Long i = items.get(s - 1).getStudentID();
            item.setStudentID(i + 1);
        } else {

//            item.setStudentID(loginMB.getUser().getSchool().getSystemVariables().getFirstID());
            String StudentID = loginMB.getUser().getSchool().getId().toString();
            StudentID = StudentID.concat("6").concat("10000");
            item.setStudentID(new Long(StudentID));
        }
        item.setRegisterDate(new java.util.Date());
        item.setSchool(loginMB.getUser().getSchool());
        studentBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }
    
    public String update() {
        studentBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
        
    }
    
    public String delete() {
        studentBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }
    
    public String createUser(Long id) throws NoSuchAlgorithmException, SOAPException {
        user = new Users();
        item = studentBean.find(id);
//        user.setStudent(item);
        user.setName_ar(item.getName_ar());
        user.setName_en(item.getName_en());
        String o = item.getStudentID().toString();
        if (usersBean.findByUsername(o).size() >= 1) {
            
            int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
//            Date datei = new Date();

            String d = item.getStudentID().toString().concat(String.valueOf(generatedInt));
            user.setUsername(d);
        } else {
            user.setUsername(item.getStudentID().toString());
        }
        user.setPassword(EncodeClass.encode(item.getStudentID().toString()));
        user.setRole_("STUDENT");
        user.setSchool(item.getSchool());
        usersBean.create(user);
        item.setUser(new Users());
        item.setUser(user);
        user.setPhone(item.getPhone1());
        studentBean.update(item);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", id.toString()));
        SendSMS.setPhoneAndSMS("967".concat(user.getPhone()), "مرحبا بك في نظام وتطبيق مدرستي" + "\n" + "اسم المستخدم :" + item.getStudentID() + "\n" + "كلمة السر :" + item.getStudentID());
        return "items?faces-redirect=true";
    }
    
    public String UpdateParent(Long parentId) {
        System.out.println("sssssparent" + parentId);
        
        System.out.println("2");
        parent = parentBean.find(parentId);
        System.out.println("3");
        item.setParent(parent);
        System.out.println("4");
        studentBean.update(item);
        System.out.println("5");
        return "items?faces-redirect=true";
        
    }
    
    public void print(long id) throws JRException, IOException {
        Map<String, Object> m = new HashMap();
        m.put("id", id);
        jasperManager.exportToPDF("SR.jrxml", null, null, m, true);
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
        File o = new File("C:\\Users\\root\\Desktop\\importFilesXlsxCsv\\");
        String f = "";
        
        String concat = f.concat(String.valueOf((s.getMonth() + 1)))
                .concat(String.valueOf(c.getWeekYear()))
                .concat(String.valueOf(s.getDate()))
                .concat(String.valueOf(generatedInt));
        File oStream = new File(
                "C:\\Users\\root\\Desktop\\importFilesXlsxCsv\\" + concat + "." + ext);
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
            item = new Student();
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                
                cell = (XSSFCell) cells.next();
                System.out.println("cell.getColumnIndex" + cell.getColumnIndex());
                if (cell.getColumnIndex() == 0) {
                    item.setName_ar(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    item.setName_en(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 2) {
                    cell.setCellType(cell.CELL_TYPE_STRING);
                    item.setPhone1(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 3) {
                    item.setGender(cell.getBooleanCellValue());
                } else if (cell.getColumnIndex() == 4) {
                    item.setStudentID((long) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 5) {
                    item.setAddress(cell.getStringCellValue());
                }

                /*if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    if (cell.getColumnIndex() == 0) {
                        item.setName_ar(cell.getStringCellValue());
                    } else if (cell.getColumnIndex() == 1) {
                        item.setName_en(cell.getStringCellValue());
                    } else if (cell.getColumnIndex() == 5) {
                        item.setAddress(cell.getStringCellValue());
                    }
//                    item.setName_ar(cell.getStringCellValue());
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    if (cell.getColumnIndex() == 2) {
                        item.setPhone1(String.valueOf(cell.getNumericCellValue()));
                    } else if (cell.getColumnIndex() == 4) {
                        item.setStudentID((long) cell.getNumericCellValue());
                    }
//                    item.setId(Math.round(cell.getNumericCellValue()));
                    System.out.print(cell.getNumericCellValue() + " ");
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
                    if (cell.getColumnIndex() == 3) {
                        item.setGender(cell.getBooleanCellValue());
                    }
                    System.out.print(cell.getBooleanCellValue() + " ");
                }*/
            }
            
            item.setSchool(loginMB.getUser().getSchool());
            System.out.println();
            items.add(item);
        }
        
    }
    
    public String createItemsFromExcel() {
        for (int i = 0; i < items.size(); i++) {
       
                studentBean.create(items.get(i));
         
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }
    
    public boolean hasUser(Long id) {
        return true;
    }
    
    public Student getItem() {
        return item;
    }
    
    public void setItem(Student item) {
        this.item = item;
    }
    
    public List<Student> getItems() {
        return items;
    }
    
    public void setItems(List<Student> items) {
        this.items = items;
    }
    
    public Users getUser() {
        return user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    
    public Parent getParent() {
        return parent;
    }
    
    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    public List<Parent> getParents() {
        return parents;
    }
    
    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
    
    public UploadedFile getFile() {
        return file;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public List<Student> getSelectedStudentSchools() {
        return selectedStudentSchools;
    }
    
    public void setSelectedStudentSchools(List<Student> selectedStudentSchools) {
        this.selectedStudentSchools = selectedStudentSchools;
    }
    
    public boolean isCreateSee() {
        return createSee;
    }
    
    public void setCreateSee(boolean createSee) {
        this.createSee = createSee;
    }
    
}
