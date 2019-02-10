/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

//import com.maxmind.geoip.LookupService;
import com.megasourceye.schooly.ejbs.AccessTrackingBean;
import com.megasourceye.schooly.ejbs.DataAccessObject;
import com.megasourceye.schooly.ejbs.GetNetworkAddress;
import com.megasourceye.schooly.entities.AccessTracking;
import com.megasourceye.schooly.entities.Users;
import com.megasourceye.schooly.mbs.utls.Location_Use_Bean;
import com.megasourceye.schooly.mbs.utls.PrimeFaces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.barcelona.view.GuestPreferences;

import java.net.UnknownHostException;
import javax.faces.application.FacesMessage;

/**
 *
 * @author said
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    /**
     * Creates a new instance of LoginMB
     */
    @Inject
    private DataAccessObject dataAccessObject;
    @Inject
    private GuestPreferences guestPreferences;
    @Inject
    private AccessTrackingBean accessTrackingBean;

    private String username;
    private String password;
    private String role;
    private String AB;
    private char loginType = '0';
    private boolean visitorHint = false;
//    private Login sessionUser;
    private Users user;
    private AccessTracking accessTracking;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    public LoginMB() {
    }

    @PostConstruct
    public void init() {
        if (dataAccessObject == null) {
            dataAccessObject = new DataAccessObject();
        }
    }

    public void loginListner() {
        switch (loginType) {
            case '1':
                username = "adminV";
                password = "123456";
                break;
            case '2':
                username = "adminV";
                password = "admin";
                break;
            case '3':
                username = "ccc";
                password = "xxx";
                break;
            default:
                break;
        }
        visitorHint = true;
    }

    public String login() throws SocketException, Exception, UnknownHostException {

//        System.out.println("1");
//        System.out.println("1");
//        System.out.println("1");
//        System.out.println("1");
//        System.out.println("1");
//        System.out.println("Ip: " + GetNetworkAddress.GetAddress("ip"));
//        System.out.println("Mac: " + GetNetworkAddress.GetAddress("mac"));
//        System.out.println("IP" + IP());
//        System.out.println("1111111111");
//
//        
//        System.out.println("1111111111");
//        LookupService cl = new LookupService("E:\\locationDB\\GeoLiteCity.dat\\GeoLiteCity.dat",
//                LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
//
//        com.maxmind.geoip.Location location = cl.getLocation(IP());
//        System.out.println("countryName:" + location.countryName);
//        System.out.println("region:" + location.region);
//        System.out.println("latitude:" + location.latitude);
//        System.out.println("dma_code:" + location.dma_code);
//        System.out.println("postalCode:" + location.postalCode);
//        System.out.println("countryCode:" + location.countryCode);
//        System.out.println("metro_code:" + location.metro_code);
//        System.out.println("longitude:" + location.longitude);
//        System.out.println("city:" + location.city);
//        System.out.println("area_code:" + location.area_code);
//        System.out.println("area_code:" + location.area_code);
//        System.out.println("location.distance(location):" + location.distance(location));
//        System.out.println(getIp()); 
//        LoginMB obj = new LoginMB();
//	ServerLocation location = obj.getLocation("206.190.36.45");
//	System.out.println(location);
//        get_ip_Details(getIp());
        //
        /*
        LoginMB obj_Get_Location_From_IP = new LoginMB();
        Location_Use_Bean obj_Location_Use_Bean = obj_Get_Location_From_IP.get_ip_Details(IP());
        System.out.println("IP Address--" + obj_Location_Use_Bean.getIp_address());
        System.out.println("Country Code-- " + obj_Location_Use_Bean.getIp_address());
        System.out.println("Country--" + obj_Location_Use_Bean.getCountry());
        System.out.println("State--" + obj_Location_Use_Bean.getState());
        System.out.println("City--" + obj_Location_Use_Bean.getCity());
        System.out.println("ZIP--" + obj_Location_Use_Bean.getZip());
        System.out.println("Lat--" + obj_Location_Use_Bean.getLat());
        System.out.println("Lon--" + obj_Location_Use_Bean.getLon());
        System.out.println("Offset--" + obj_Location_Use_Bean.getUtc_offset());
         */
        //
        accessTracking = new AccessTracking();
        accessTracking.setAccessDate(new Date());
//        accessTracking.setIpAddress(IP());
//        accessTracking.setMacAddress(GetNetworkAddress.GetAddress("mac"));
        accessTracking.setUsername(username);
        accessTracking.setPassword(password);
//        IP();
        try {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            System.out.println("100");
            request.login(username, password);
            user = dataAccessObject.getUserInfo(username);
            guestPreferences.setOverlayMenu(false);
            guestPreferences.setTheme("teal");
            guestPreferences.setDarkMenu(true);
            accessTracking.setUsers(user);
            accessTracking.setStatus(true);
            accessTrackingBean.create(accessTracking);
            System.out.println("end 1");
            return "/index?faces-redirect=true";
        } catch (ServletException ex) {
            accessTracking.setStatus(false);
            accessTrackingBean.create(accessTracking);
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("end 2");
            return "/access.xhtml";
        }
//        return "access.xhtml";
    }

//    public String logout() {
//        try {
//            System.out.println("2");
//
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            System.out.println("3");
//
//            request.logout();
//            request.getSession().isNew();
//            System.out.println("4");
//
//            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//            System.out.println("5");
//            return "/landing?faces-redirect=true";
//        } catch (ServletException ex) {
//            System.out.println("6");
//            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("7");
//        }
//        return "error?faces-redirect=true";
//    }
    public void logout2() {
        System.out.println("log2out45");
        //}
        //try {
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //request.logout();
        //request.getSession().isNew();

//System.out.println(request.getSession().getLastAccessedTime() / 10);
//user = null;
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //System.out.println("logout");
        //return "/login?faces-redirect=true";
        //} catch (ServletException ex) {
        //System.out.println("6");
        //Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
        //System.out.println("7");
        //}
        //return "/error?faces-redirect=true";
    }

    public String logout3() {
//        System.out.println("log2out45");
        //}
        try {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            request.getSession().isNew();

//System.out.println(request.getSession().getLastAccessedTime() / 10);
            user = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            System.out.println("logout");
            return "/login?faces-redirect=true";
        } catch (ServletException ex) {
            System.out.println("6");
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("7");
        }
        return "/error?faces-redirect=true";
    }

    public String IP() {
        URL ipAdress;

        try {
            ipAdress = new URL("http://myexternalip.com/raw");

            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));

            String ip = in.readLine();
            System.out.println(ip);
            return ip;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void test() {
//        System.out.println("ddddd");
//        System.out.println("ddddd");
//        System.out.println("ddddd");
//        System.out.println("ddddd");
//        System.out.println("ddddd");
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        System.out.println(facesContext.getViewRoot().getViewId()); 
//    }

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getIpAddress() {
        URL myIP;
        try {
            myIP = new URL("http://api.externalip.net/ip/");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myIP.openStream())
            );
            return in.readLine();
        } catch (Exception e) {
            try {
                myIP = new URL("http://myip.dnsomatic.com/");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(myIP.openStream())
                );
                return in.readLine();
            } catch (Exception e1) {
                try {
                    myIP = new URL("http://icanhazip.com/");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(myIP.openStream())
                    );
                    return in.readLine();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        return null;
    }

    public void usernameListner() {
//        if(username.length()>0){
//            System.out.println("ssseee");
        visitorHint = false;
        loginType = '0';
//        }
    }

//    public Location_Use_Bean get_ip_Details(String ip) {
//        String key = "9d6jinufcfdfacc213c7ddf4ef911dfe97b55e4696be3732bf8302876c09ebad06b";
//        ip = ip.trim();
//        Location_Use_Bean obj_Location_Use_Bean = new Location_Use_Bean();
//        System.out.println("The ip adress is before " + ip + "  split");
//        try {
//            if (ip.contains(",")) {
//                String temp_ip[] = ip.split(",");
//                ip = temp_ip[1].trim();
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        System.out.println("The ip adress is after " + ip + " split");
//        URL url;
//        try {
//            url = new URL("http://api.ipinfodb.com/v3/ip-city/?key=" + key + "&ip=" + ip);
//            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//            String strTemp = "";
//            String temporaray = "";
//            String temp_array[] = null;
//            while (null != (strTemp = br.readLine())) {
//                temporaray = strTemp;
//            }
//            temp_array = temporaray.split(";");
//            System.out.println("Str length is " + temp_array.length);
//            int length = temp_array.length;
//
//            System.out.println(temp_array[3]);
//            System.out.println(temp_array[4]);
//            System.out.println(temp_array[5]);
//            System.out.println(temp_array[6]);
//            System.out.println(temp_array[7]);
//            System.out.println(temp_array[8]);
//            System.out.println(temp_array[9]);
//            System.out.println(temp_array[10]);
//            System.out.println(temp_array[3]);
//            if (length == 11) {
//                obj_Location_Use_Bean.setIp_address(ip);
//                if (temp_array[3] != null) {
//                    obj_Location_Use_Bean.setCountry_code(temp_array[3]);
//                    System.out.println(temp_array[3]);
//                }
//                if (temp_array[4] != null) {
//                    obj_Location_Use_Bean.setCountry(temp_array[4]);
//                    System.out.println(temp_array[4]);
//                }
//                if (temp_array[5] != null) {
//                    obj_Location_Use_Bean.setState(temp_array[5]);
//                    System.out.println(temp_array[5]);
//                }
//                if (temp_array[6] != null) {
//                    obj_Location_Use_Bean.setCity(temp_array[6]);
//                    System.out.println(temp_array[6]);
//                }
//                if (temp_array[7] != null) {
//                    obj_Location_Use_Bean.setZip(temp_array[7]);
//                    System.out.println(temp_array[7]);
//                }
//                if (temp_array[8] != null) {
//                    obj_Location_Use_Bean.setLat(temp_array[8]);
//                    System.out.println(temp_array[8]);
//                }
//                if (temp_array[9] != null) {
//                    obj_Location_Use_Bean.setLon(temp_array[9]);
//                    System.out.println(temp_array[9]);
//
//                }
//                if (temp_array[10] != null) {
//                    obj_Location_Use_Bean.setUtc_offset(temp_array[10]);
//                    System.out.println(temp_array[10]);
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return obj_Location_Use_Bean;
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Login getSessionUser() {
//        return sessionUser;
//    }
//
//    public void setSessionUser(Login sessionUser) {
//        this.sessionUser = sessionUser;
//    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAB() {
        return AB;
    }

    public void setAB(String AB) {
        this.AB = AB;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public char getLoginType() {
        return loginType;
    }

    public void setLoginType(char loginType) {
        this.loginType = loginType;
    }

    public boolean isVisitorHint() {
        return visitorHint;
    }

    public void setVisitorHint(boolean visitorHint) {
        this.visitorHint = visitorHint;
    }

}
