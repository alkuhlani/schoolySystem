/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs.utls;


import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Mohammed & Khalil
 */
@Named
@RequestScoped
public class JasperManager implements Serializable {


    private String report;
    private String title;
    private String firstText;
    private String seconedText;
    private String notes;
    private String footerText;
    private String user;
    private boolean showBasmalah;
    private boolean showNumbers;
    private boolean showPrintDate;
    private boolean showTitle;
    private String signDec1;
    private String signName1;
    private String signDec2;
    private String signName2;
    private String signDec3;
    private String signName3;
    private String signDec4;
    private String signName4;
    private String signDec5;
    private String signName5;
    private Entry<String, Object>[] paramList;

//    public static final String reportRootPath = "c:/reports/";
    public static final String reportRootPath = "C:/Users/said/JaspersoftWorkspace/schoolyReport/";

    public  void exportToPDF(List items, String reportPath, String reportHeaderPath, String reportFooterPath,Map additionalParams, boolean mirror) throws JRException, IOException {
        Map params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("i18n/msg", new Locale("ar")));
        if (additionalParams != null) {
            params.putAll(additionalParams);
        }

        JasperDesign jasperDesign = JRXmlLoader.load(reportRootPath + reportPath);
        if (reportHeaderPath != null) {
            jasperDesign.setPageHeader(JRXmlLoader.load(reportRootPath + reportHeaderPath).getPageHeader());
        }
        if (reportFooterPath != null) {
            jasperDesign.setPageFooter(JRXmlLoader.load(reportRootPath + reportFooterPath).getPageFooter());
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                new JRBeanCollectionDataSource(items));
        if (mirror) {
            mirrorLayout(jasperPrint);
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=report.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, httpServletResponse.getOutputStream());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public  void exportToPDF(String reportPath, String reportHeaderPath, String reportFooterPath,
            Map additionalParams, boolean mirror) throws JRException, IOException {
        Map params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("i18n/msg", new Locale("ar")));
        if (additionalParams != null) {
            params.putAll(additionalParams);
        }

        JasperDesign jasperDesign = JRXmlLoader.load(reportRootPath + reportPath);
        if (reportHeaderPath != null) {
            jasperDesign.setPageHeader(JRXmlLoader.load(reportRootPath + reportHeaderPath).getPageHeader());
        }
        if (reportFooterPath != null) {
            jasperDesign.setPageFooter(JRXmlLoader.load(reportRootPath + reportFooterPath).getPageFooter());
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Context context;
        JasperPrint jasperPrint = null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/schooly");
            Connection conn = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
            conn.close();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            context = new InitialContext();
//            DataSource dataSource = (DataSource) context.lookup("java:/schooly");
//            dataSource.getConnection();
//            jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
//        } catch (NamingException | SQLException ex) {
//            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if (mirror) {
            mirrorLayout(jasperPrint);
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=report.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, httpServletResponse.getOutputStream());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public  void exportToXls(List items, String reportPath, String reportHeaderPath, String reportFooterPath, Map additionalParams, boolean mirror) throws JRException, IOException {
        JasperDesign jasperDesign = JRXmlLoader.load(reportRootPath + reportPath);
        if (reportHeaderPath != null) {
            jasperDesign.setPageHeader(JRXmlLoader.load(reportRootPath + reportHeaderPath).getPageHeader());
        }
        if (reportFooterPath != null) {
            jasperDesign.setPageFooter(JRXmlLoader.load(reportRootPath + reportFooterPath).getPageFooter());
        }
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, additionalParams, new JRBeanCollectionDataSource(items));
        if (mirror) {
            mirrorLayout(jasperPrint);
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/xls");
        httpServletResponse.setHeader("Content-disposition", "inline; filename=report.xls");
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(httpServletResponse.getOutputStream()));
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    private  void mirrorLayout(JasperPrint print) {
        int pageWidth = print.getPageWidth();
        for (JRPrintPage page : print.getPages()) {
            mirrorLayout(page.getElements(), pageWidth);
        }
    }

    private void mirrorLayout(List<JRPrintElement> elements, int totalWidth) {
        for (JRPrintElement element : elements) {
            element.setX(totalWidth - element.getX() - element.getWidth());
            if (element instanceof JRPrintFrame) {
                JRPrintFrame frame = (JRPrintFrame) element;
                mirrorLayout(frame.getElements(), frame.getWidth());
            }
        }
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the firstText
     */
    public String getFirstText() {
        return firstText;
    }

    /**
     * @param firstText the firstText to set
     */
    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    /**
     * @return the seconedText
     */
    public String getSeconedText() {
        return seconedText;
    }

    /**
     * @param seconedText the seconedText to set
     */
    public void setSeconedText(String seconedText) {
        this.seconedText = seconedText;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the footerText
     */
    public String getFooterText() {
        return footerText;
    }

    /**
     * @param footerText the footerText to set
     */
    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the signDec1
     */
    public String getSignDec1() {
        return signDec1;
    }

    /**
     * @param signDec1 the signDec1 to set
     */
    public void setSignDec1(String signDec1) {
        this.signDec1 = signDec1;
    }

    /**
     * @return the signName1
     */
    public String getSignName1() {
        return signName1;
    }

    /**
     * @param signName1 the signName1 to set
     */
    public void setSignName1(String signName1) {
        this.signName1 = signName1;
    }

    /**
     * @return the signDec2
     */
    public String getSignDec2() {
        return signDec2;
    }

    /**
     * @param signDec2 the signDec2 to set
     */
    public void setSignDec2(String signDec2) {
        this.signDec2 = signDec2;
    }

    /**
     * @return the signName2
     */
    public String getSignName2() {
        return signName2;
    }

    /**
     * @param signName2 the signName2 to set
     */
    public void setSignName2(String signName2) {
        this.signName2 = signName2;
    }

    /**
     * @return the signDec3
     */
    public String getSignDec3() {
        return signDec3;
    }

    /**
     * @param signDec3 the signDec3 to set
     */
    public void setSignDec3(String signDec3) {
        this.signDec3 = signDec3;
    }

    /**
     * @return the signName3
     */
    public String getSignName3() {
        return signName3;
    }

    /**
     * @param signName3 the signName3 to set
     */
    public void setSignName3(String signName3) {
        this.signName3 = signName3;
    }

    /**
     * @return the signDec4
     */
    public String getSignDec4() {
        return signDec4;
    }

    /**
     * @param signDec4 the signDec4 to set
     */
    public void setSignDec4(String signDec4) {
        this.signDec4 = signDec4;
    }

    /**
     * @return the signName4
     */
    public String getSignName4() {
        return signName4;
    }

    /**
     * @param signName4 the signName4 to set
     */
    public void setSignName4(String signName4) {
        this.signName4 = signName4;
    }

    /**
     * @return the signDec5
     */
    public String getSignDec5() {
        return signDec5;
    }

    /**
     * @param signDec5 the signDec5 to set
     */
    public void setSignDec5(String signDec5) {
        this.signDec5 = signDec5;
    }

    /**
     * @return the signName5
     */
    public String getSignName5() {
        return signName5;
    }

    /**
     * @param signName5 the signName5 to set
     */
    public void setSignName5(String signName5) {
        this.signName5 = signName5;
    }

    /**
     * @return the showBasmalah
     */
    public boolean isShowBasmalah() {
        return showBasmalah;
    }

    /**
     * @param showBasmalah the showBasmalah to set
     */
    public void setShowBasmalah(boolean showBasmalah) {
        this.showBasmalah = showBasmalah;
    }

    /**
     * @return the showNumbers
     */
    public boolean isShowNumbers() {
        return showNumbers;
    }

    /**
     * @param showNumbers the showNumbers to set
     */
    public void setShowNumbers(boolean showNumbers) {
        this.showNumbers = showNumbers;
    }

    /**
     * @return the showPrintDate
     */
    public boolean isShowPrintDate() {
        return showPrintDate;
    }

    /**
     * @param showPrintDate the showPrintDate to set
     */
    public void setShowPrintDate(boolean showPrintDate) {
        this.showPrintDate = showPrintDate;
    }

    /**
     * @return the showTitle
     */
    public boolean isShowTitle() {
        return showTitle;
    }

    /**
     * @param showTitle the showTitle to set
     */
    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    /**
     * @return the report
     */
    public String getReport() {
        System.out.println("report" + report);
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * @return the paramList
     */
    public Entry<String, Object>[] getParamList() {
        return paramList;
    }

    /**
     * @param paramList the paramList to set
     */
    public void setParamList(Entry<String, Object>[] paramList) {
        this.paramList = paramList;
    }

}
