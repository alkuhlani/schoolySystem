/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.BranchBean;
import com.megasourceye.schooly.entities.Branch;
import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.mbs.utls.PrimeFaces;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author said
 */
@Named(value = "branchMB")
@ViewScoped
public class BranchMB implements Serializable {

    /**
     * Creates a new instance of BranchMB
     */
    public BranchMB() {
    }
    private Branch item;
    private List<Branch> items;
    private List<Branch> selectedItems = new ArrayList<>();
    private List<Branch> selectedBranches;
    boolean loggedIn = false;
    boolean inUpdate = false;
    UploadedFile file;

    @Inject
    private BranchBean branchBean;
    @Inject
    private LoginMB loginMB;

    @PostConstruct
    public void init() {
        items = branchBean.findBySchoolId(
                loginMB.getUser().getSchool().getId());
//        item=new Branch();
    }

    public void showItems(ActionEvent ae) {
        System.out.println("wwwwwww");
        RequestContext.getCurrentInstance()
                .openDialog("branchess", getDialogOptions(), null);
    }

//    private List<Branch> selectedItems = new ArrayList<>();
//     public void showEmployees(ActionEvent ae) {
//        RequestContext.getCurrentInstance()
//                      .openDialog("branchess", getDialogOptions(), null);
//    }
    public Map<String, Object> getDialogOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("height", 600);
        options.put("contentHeight", "100%");
        return options;
    }

    public void onItemSelected(SelectEvent selectEvent) {
        this.selectedItems = (List<Branch>) selectEvent.getObject();
    }

    public List<Branch> getSelectedItems() {
        return selectedItems;
    }

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                // items = termBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = branchBean.findBySchoolId(
                        loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            }
        }

    }

    public void prepareAllItems2() {
        items = branchBean.findBySchoolId(-10L);
    }

    public void viewCars() {
        System.out.println("sssss");
        Map<String, Object> options = new HashMap();
        options.put("resizable", false);
        PrimeFaces.current().dialog.openDynamic("selectCar", options, null);
        System.out.println("eeeeeeee");
    }

    public void viewCarsCustomized() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        PrimeFaces.current().dialog.openDynamic("selectCar", options, null);
    }

    public void prepareCreate() {
        inUpdate = false;

        RequestContext rctx = RequestContext.getCurrentInstance();
//            rctx.execute("UsuarioLoginDialog.hide()");     
//            rctx.update("UsuarioLoginForm:growl");
        rctx.update("form2");
        System.out.println("dddd");
        item = new Branch();
        item.setSchool(loginMB.getUser().getSchool());
        System.out.println("oooooooo");
        rctx.execute("PF('dlg').show();");
    }

    public void prepareUpdate(Long id2) {
        inUpdate = true;
        RequestContext rctx = RequestContext.getCurrentInstance();
        System.out.println("starrrrrrrrt");
        item = new Branch();
        item = branchBean.find(id2);
        System.out.println("this its item " + item.getName_ar());
        rctx.update("form2");
        rctx.execute("PF('dlg').show();");
    }

    public void create() {
        System.out.println("item.name" + item.getName_ar());
        System.out.println("item.name2" + item.getName_en());
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "Su");

        System.out.println("b create");
        branchBean.create(item);
        loggedIn = true;

        System.out.println("itemaftename" + item.getName_ar() + " " + item.getName_en());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
//        prepareCreate();
        System.out.println("afer create");
        ///
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        //  return "items?faces-redirect=true";
    }

    public void update() {
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "Su");

        branchBean.update(item);
        loggedIn = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
//        return "items?faces-redirect=true";
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);

    }

    public String delete() {
        branchBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
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
        InputStream ExcelFileToRead = new FileInputStream(oStream.getPath());
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            if (row.getRowNum() > 0) {
                item = new Branch();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {

                    cell = (XSSFCell) cells.next();
                    System.out.println("cell.getColumnIndex" + cell.getColumnIndex());
                    if (cell.getColumnIndex() == 0) {
                        item.setName_ar(cell.getStringCellValue());
                    } else if (cell.getColumnIndex() == 1) {
                        item.setName_en(cell.getStringCellValue());
                    }
                }
                item.setSchool(loginMB.getUser().getSchool());
                System.out.println();
                items.add(item);
            }

        }
    }

    public String createItemsFromExcel() {
        for (int i = 0; i < items.size(); i++) {
            branchBean.create(items.get(i));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }

    private DefaultStreamedContent download;

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        System.out.println("GET = " + download.getName());
        return download;
    }

    public void prepDownload() throws Exception {
        File files = new File("C:\\templates\\branchTemplate.xlsx");
        InputStream input = new FileInputStream(files);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(files.getName()), files.getName()));
        System.out.println("PREP = " + download.getName());

    }

    public Branch getItem() {
        return item;
    }

    public void setItem(Branch item) {
        this.item = item;
    }

    public List<Branch> getItems() {
        return items;
    }

    public void setItems(List<Branch> items) {
        this.items = items;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isInUpdate() {
        return inUpdate;
    }

    public void setInUpdate(boolean inUpdate) {
        this.inUpdate = inUpdate;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Branch> getSelectedBranches() {
        return selectedBranches;
    }

    public void setSelectedBranches(List<Branch> selectedBranches) {
        this.selectedBranches = selectedBranches;
    }

}
