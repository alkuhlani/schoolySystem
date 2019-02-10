/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import java.io.IOException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 *
 * @author said
 */
public class SendSMS {

    public static boolean setPhoneAndSMS(String phone, String sms) throws SOAPException  {
        try {
            System.out.println("phone"+phone);
            System.out.println("sms"+sms);
            System.out.println("1000");
            String endpoint = "http://209.126.68.156/WebService.asmx"; // "http://www.w3schools.com/webservices/tempconvert.asmx";
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();

            // Get Mime Header
            MimeHeaders mimeHeader = message.getMimeHeaders();
            mimeHeader.addHeader("Host", "209.126.68.156");
            mimeHeader.addHeader("Content-Type", "text/xml; charset=utf-8");

            mimeHeader.addHeader("SOAPAction",
                    "http://tempuri.org/sendmessage");

            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
            envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
            // Get soap body
            SOAPBody body = message.getSOAPBody();
            // Enter data into body
            SOAPElement temp = body.addChildElement("sendmessage", "",
                    "http://tempuri.org/");

            SOAPElement fahr = temp.addChildElement("message");
            fahr.addTextNode(sms);
            SOAPElement fahr1 = temp.addChildElement("receiver");
            fahr1.addTextNode(phone);
            SOAPElement fahr2 = temp.addChildElement("shortcode");
            if(phone.startsWith("96777")){
                fahr2.addTextNode("3117");
            }else if(phone.startsWith("96773")){
                fahr2.addTextNode("5000");
            }else if(phone.startsWith("96770")){
                fahr2.addTextNode("7334");
            }
//            fahr2.addTextNode("3116");
            SOAPElement fahr3 = temp.addChildElement("type");
            fahr3.addTextNode("0");

            // print what we are sending
//            message.writeTo(System.out);

            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage response = connection.call(message, endpoint);
            connection.close();

//            System.out.println("");
//            System.out.println("---------------");
//            response.writeTo(System.out);
            return true;
        } catch (SOAPException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
