/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs.utls;


import java.io.File;
import java.io.IOException;

import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

/**
 *
 * @author said
 */
public class GetLocationExample {

    public GetLocationExample(String ipAddress) {
        this.ipAddress = ipAddress;
        File file = new File(
                "E:\\locationDB\\GeoLit\\GeoLiteCity.dat");
         getLocation(ipAddress, file);
//        GetLocationExample obj = new GetLocationExample();
//        ServerLocation location = obj.getLocation(ipAddress);
//        System.out.println(location);
    }

    public GetLocationExample() {
    }

    private static String ipAddress;

    public static void main(String[] args) {
        GetLocationExample obj = new GetLocationExample();
//        ServerLocation location = obj.getLocation(ipAddress);
//        System.out.println(location);
    }

    public void getLocation(String ipAddress) {

        File file = new File(
                "E:\\locationDB\\GeoLit\\GeoLiteCity.dat");
         getLocation(ipAddress, file);

    }

    public void getLocation(String ipAddress, File file) {

//        ServerLocation serverLocation = null;

        try {

//            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            com.maxmind.geoip.Location locationServices = lookup.getLocation(ipAddress);

            System.out.println(locationServices.countryCode);
            System.out.println(locationServices.countryName);
            System.out.println(locationServices.region);
            System.out.println(regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            System.out.println(locationServices.city);
            System.out.println(locationServices.postalCode);
            System.out.println(String.valueOf(locationServices.latitude));
            System.out.println(String.valueOf(locationServices.longitude));
//
//	serverLocation.setCountryCode(locationServices.countryCode);
//	serverLocation.setCountryName(locationServices.countryName);
//	serverLocation.setRegion(locationServices.region);
//	serverLocation.setRegionName(regionName.regionNameByCode(
//             locationServices.countryCode, locationServices.region));
//	serverLocation.setCity(locationServices.city);
//	serverLocation.setPostalCode(locationServices.postalCode);
//	serverLocation.setLatitude(String.valueOf(locationServices.latitude));
//	serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

//        return serverLocation;

    }
}
