/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.BranchBean;
import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.DepartmentBean;
import com.megasourceye.schooly.ejbs.StaticClassBean;
import com.megasourceye.schooly.ejbs.StaticLevelBean;
import com.megasourceye.schooly.ejbs.StaticSubClassBean;
import com.megasourceye.schooly.entities.Branch;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Department;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.StaticClass;
import com.megasourceye.schooly.entities.StaticLevel;
import com.megasourceye.schooly.entities.StaticSubClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
@Named(value = "classMB")
@ViewScoped
public class ClassMB implements Serializable {

    /**
     * Creates a new instance of ClassMB
     */
    public ClassMB() {
    }
    private Class_ item;
    private List<Class_> items;
    private List<Department> departments;
    private List<Branch> branchs;
    private List<StaticClass> staticClasses;
    private List<StaticLevel> staticLevels;
    private List<StaticSubClass> staticSubClasses;
    private List<Class_> selectedClasses;
    UploadedFile file;
    private static String[] columnsSC = {"ID", "Name in Arabic", "Name in English"};
    private static String[] columnsSL = {"ID", "Name in Arabic", "Name in English"};
    private static String[] columnsSSC = {"ID", "Name in Arabic", "Name in English"};
    private static String[] columnsSB = {"ID", "Name in Arabic", "Name in English"};
    private DefaultStreamedContent download;
    private DefaultStreamedContent download2;

    @Inject
    private ClassBean classBean;
    @Inject
    private DepartmentBean departmentBean;
    @Inject
    private BranchBean branchBean;
    @Inject
    private StaticClassBean staticClassBean;
    @Inject
    private StaticLevelBean staticLevelBean;
    @Inject
    private StaticSubClassBean staticSubClassBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private LnsMB lnsMB;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = classBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                items = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
    }

    public void prepareAllItems2() {
        items = classBean.findBySchoolId(-10L);
    }

    public void prepareCreate() {
        item = new Class_();
        item.setSchool(new School());
        item.setDepartment(new Department());
        item.setBranch(new Branch());
        item.setStaticClass(new StaticClass());
        item.setStaticLevel(new StaticLevel());
        item.setStaticSubClass(new StaticSubClass());
        departments = departmentBean.findAll();
        branchs = branchBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        staticClasses = staticClassBean.findAll();
        staticLevels = staticLevelBean.findAll();
        staticSubClasses = staticSubClassBean.findAll();
    }

    public void prepareUpdate(Long id2) {
        item = classBean.find(id2);
        if (item.getBranch() == null) {
            item.setBranch(new Branch());
        }
        if (item.getDepartment() == null) {
            item.setDepartment(new Department());
        }
        branchs = branchBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        departments = departmentBean.findAll();
    }

    public void create() {
        item.setSchool(loginMB.getUser().getSchool());
        classBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", ("en".equals(lnsMB.getLanguage())) ? "Successfully Saved" : "تم الحفظ بنجاح"));
        prepareCreate();
        //  return "items?faces-redirect=true";
    }

    public String update() {
        classBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", ("en".equals(lnsMB.getLanguage())) ? "Successfully Updated" : "تم التعديل بنجاح"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        classBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", ("en".equals(lnsMB.getLanguage())) ? "Successfully Deleted" : "تم الحذف بنجاح"));
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
        System.out.println(ext);
        File o = new File("C:\\importFilesXlsxCsv\\");
        String f = "";

        String concat = f.concat(String.valueOf((s.getMonth() + 1)))
                .concat(String.valueOf(c.getWeekYear()))
                .concat(String.valueOf(s.getDate()))
                .concat(String.valueOf(generatedInt));
        File oStream = new File("C:\\importFilesXlsxCsv\\" + concat + "." + ext);
        if (!o.exists()) {
            o.mkdirs();
        }
        oStream.createNewFile();
        Long sss = file.getSize();
        InputStream inputStream = file.getInputstream();
        try (FileOutputStream outputStream = new FileOutputStream(oStream)) {
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, lenght);
            }
        }
        float ff = (int) file.getSize() / 1024;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Uploaded Successfully -" + ff + "KB"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "File Uploaded Successfully"));
        InputStream ExcelFileToRead = new FileInputStream(oStream.getPath());
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {

            row = (XSSFRow) rows.next();
            if (row.getRowNum() > 0) {
                item = new Class_();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
//                    if (cell != null) {
                    System.out.println("cell.getColumnIndex" + cell.getColumnIndex());
                    if (cell.getColumnIndex() == 0) {
                        System.out.println("case 0");
                        item.setName_ar(cell.getStringCellValue());
                    } else if (cell.getColumnIndex() == 1) {

                        System.out.println("case 1");
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setDepartment(departmentBean.find((long) cell.getNumericCellValue()));
                        } else {
                            item.setDepartment(null);
                        }
                    } else if (cell.getColumnIndex() == 2) {
                        System.out.println("case 2");
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setStaticLevel(staticLevelBean.find((long) cell.getNumericCellValue()));
                        } else {
                            item.setStaticLevel(null);
                        }
                    } else if (cell.getColumnIndex() == 3) {
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setStaticClass(staticClassBean.find((long) cell.getNumericCellValue()));
                        } else {
                            item.setStaticClass(null);
                        }
                    } else if (cell.getColumnIndex() == 4) {
                        System.out.println("case 4");
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setStaticSubClass(staticSubClassBean.find((long) cell.getNumericCellValue()));
                        } else {
                            item.setStaticSubClass(null);
                        }
                    } else if (cell.getColumnIndex() == 5) {
                        System.out.println("case 5");
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setBranch(branchBean.find((long) cell.getNumericCellValue()));
                        } else {
                            item.setBranch(null);
                        }
                    } else if (cell.getColumnIndex() == 6) {
                        System.out.println("case 6");
                        if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                            item.setNoClass((short) cell.getNumericCellValue());
                        } else {
                            item.setNoClass(null);
                        }
                    }

                }
                System.out.println("set School");
                item.setSchool(loginMB.getUser().getSchool());
                System.out.println();
                items.add(item);
                System.out.println("afer school sets");
            }
        }
    }

    public String createItemsFromExcel() {
        for (int i = 0; i < items.size(); i++) {
            classBean.create(items.get(i));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        System.out.println("GET = " + download.getName());
        return download;
    }

    public void setDownload2(DefaultStreamedContent download) {
        this.download2 = download;
    }

    public DefaultStreamedContent getDownload2() throws Exception {
        System.out.println("GET = " + download2.getName());
        return download2;
    }

    public void prepDownload() throws Exception {
        File files = new File("C:\\templates\\classTemplate.xlsx");
        InputStream input = new FileInputStream(files);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(files.getName()), files.getName()));
        System.out.println("PREP = " + download.getName());
//        writeStaticTables();

    }

    public void writeStaticTables() throws FileNotFoundException, IOException {
        System.out.println("1");
        staticClasses = staticClassBean.findAll();
        staticSubClasses = staticSubClassBean.findAll();
        staticLevels = staticLevelBean.findAll();
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        System.out.println("2");
        /* CreationHelper helps us create instances for various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
// Start Static Class
        Sheet sheet = workbook.createSheet("staticData");
        System.out.println("3");
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Creating cells
        for (int i = 0; i < columnsSC.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnsSC[i]);
            cell.setCellStyle(headerCellStyle);
        }
        System.out.println("1");
        // Create Cell Style for formatting Date
//        CellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (StaticClass staticClass : staticClasses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(staticClass.getId());
            row.createCell(1).setCellValue(staticClass.getName_ar());
            row.createCell(2).setCellValue(staticClass.getName_en());
        }
        System.out.println("5");
        // Resize all columns to fit the content size
        for (int i = 0; i < columnsSC.length; i++) {
            sheet.autoSizeColumn(i);
        }
// End Static Classe
// Start Static Sub Classe
        Sheet sheet2 = workbook.createSheet("staticSubClass");
        System.out.println("3");
        // Create a Font for styling header cells
        //Font headerFont2 = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        //        CellStyle headerCellStyle = workbook.createCellStyle();
        //        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow2 = sheet2.createRow(0);

        // Creating cells
        for (int i = 0; i < columnsSSC.length; i++) {
            Cell cell = headerRow2.createCell(i);
            cell.setCellValue(columnsSC[i]);
            cell.setCellStyle(headerCellStyle);
        }
        System.out.println("1");
        // Create Cell Style for formatting Date
        //        CellStyle dateCellStyle = workbook.createCellStyle();
        //        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        // Create Other rows and cells with employees data
        int rowNum2 = 1;
        for (StaticSubClass staticSubClass : staticSubClasses) {
            Row row = sheet2.createRow(rowNum2++);
            row.createCell(0).setCellValue(staticSubClass.getId());
            row.createCell(1).setCellValue(staticSubClass.getName_ar());
            row.createCell(2).setCellValue(staticSubClass.getName_en());
        }
        System.out.println("5");
        // Resize all columns to fit the content size
        for (int i = 0; i < columnsSSC.length; i++) {
            sheet2.autoSizeColumn(i);
        }

// End Static Sub Class
// Start Static Level
        Sheet sheet3 = workbook.createSheet("staticLevel");
        System.out.println("3");
        // Create a Font for styling header cells
        //Font headerFont2 = workbook.createFont();

        headerRow2 = sheet3.createRow(0);

        // Creating cells
        for (int i = 0; i < columnsSL.length; i++) {
            Cell cell = headerRow2.createCell(i);
            cell.setCellValue(columnsSL[i]);
            cell.setCellStyle(headerCellStyle);
        }
        System.out.println("1");
        // Create Cell Style for formatting Date
        //        CellStyle dateCellStyle = workbook.createCellStyle();
        //        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        // Create Other rows and cells with employees data
        rowNum2 = 1;
        for (StaticLevel staticLevel : staticLevels) {
            Row row = sheet3.createRow(rowNum2++);
            row.createCell(0).setCellValue(staticLevel.getId());
            row.createCell(1).setCellValue(staticLevel.getName_ar());
            row.createCell(2).setCellValue(staticLevel.getName_en());
        }
        System.out.println("5");
        // Resize all columns to fit the content size
        for (int i = 0; i < columnsSL.length; i++) {
            sheet3.autoSizeColumn(i);
        }

// End Static Level
        System.out.println("1");
        // Write the output to a file
        System.out.println(new Date().getDate());
        System.out.println(new Date().getDay());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
        FileOutputStream fileOut = new FileOutputStream("C:\\templates\\staticData.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Done with" + fileOut.getFD());

////        InputStream input;
//        FileChannel outputChannel = fileOut.getChannel();
//        outputChannel.transferTo(0, input.size(), input);
//        
        System.out.println("222");
        File files = new File("C:\\templates\\staticData.xlsx");
        System.out.println("222");
        InputStream input = new FileInputStream(files);
        System.out.println("222");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload2(new DefaultStreamedContent(input, externalContext.getMimeType(files.getName()), files.getName()));
        System.out.println("222");
        System.out.println("PREP = " + download2.getName());

//        InputStream in = new InputStream(fileOut.getChannel());
//        FileOutputStream out = new FileOutputStream(in);
//        new Thread(
//                new Runnable() {
//            public void run() {
//                //put your code that writes data to the outputstream here.
//                putDataOnOutputStream(out);
//            }
//        }
//        ).start();
//        //data can be read from the pipedInputStream here.    
//        processDataFromInputStream(in);
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(files.getName()), files.getName()));
//        System.out.println("PREP = " + download.getName());
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {

        byte[] buffer = new byte[1024];
        while (true) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            out.write(buffer, 0, bytesRead);
        }
    }

    public Class_ getItem() {
        return item;
    }

    public void setItem(Class_ item) {
        this.item = item;
    }

    public List<Class_> getItems() {
        return items;
    }

    public void setItems(List<Class_> items) {
        this.items = items;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Branch> getBranchs() {
        return branchs;
    }

    public void setBranchs(List<Branch> branchs) {
        this.branchs = branchs;
    }

    public List<StaticClass> getStaticClasses() {
        return staticClasses;
    }

    public void setStaticClasses(List<StaticClass> staticClasses) {
        this.staticClasses = staticClasses;
    }

    public List<StaticLevel> getStaticLevels() {
        return staticLevels;
    }

    public void setStaticLevels(List<StaticLevel> staticLevels) {
        this.staticLevels = staticLevels;
    }

    public List<StaticSubClass> getStaticSubClasses() {
        return staticSubClasses;
    }

    public void setStaticSubClasses(List<StaticSubClass> staticSubClasses) {
        this.staticSubClasses = staticSubClasses;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Class_> getSelectedClasses() {
        return selectedClasses;
    }

    public void setSelectedClasses(List<Class_> selectedClasses) {
        this.selectedClasses = selectedClasses;
    }

}
