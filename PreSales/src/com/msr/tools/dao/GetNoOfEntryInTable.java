package com.msr.tools.dao;

import com.msr.tracking.UI.*;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.util.LinkedHashMap;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class GetNoOfEntryInTable {

    String response = "";

    /**
     *
     * @return
     */
    //  get the Counts and name of accordion
    public LinkedHashMap getCounts(String email) {
        LinkedHashMap countMap = new LinkedHashMap();
        try {
            callServerUrl serverUrl1 = new callServerUrl();


            StringBuffer meetingDetailsbuffer = new StringBuffer();
            meetingDetailsbuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(email);// 



            System.out.println("response before calling: " + response);
            // check from backend: there are the list of data from database, and then set the accordion name
            System.out.println(" inside Linked map");



            StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.operaionType).append("=").append("clientsCount");
            String cmpnyResponse = serverUrl1.urlProcessiong(bufer.toString().replaceAll(" ", "%20"));
            if(cmpnyResponse.equalsIgnoreCase("invalid")){
                cmpnyResponse = "0";
            }
            countMap.put("Manage Company", cmpnyResponse);
//            if(!SingletonClass.designation.equals("RM")){
//            http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=banksCount
           if(SingletonClass.privStr.toUpperCase().contains("MANAGE BANK")){
            StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.operaionType).append("=").append("banksCount");
            String bankResponse = serverUrl1.urlProcessiong(buffer.toString().replaceAll(" ", "%20"));
            if(bankResponse.equalsIgnoreCase("invalid")){
                bankResponse = "0";
            }
            countMap.put("Manage Bank", bankResponse);
           }
         
           if(SingletonClass.privStr.toUpperCase().contains("MANAGE USER")){
               response = "0";
            meetingDetailsbuffer.append("&").append(SingletonClass.operaionType).append("=").append("usersCount");
            response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));


            System.out.println("response after calling: " + response);
            if(response.equalsIgnoreCase("invalid")){
                response = "0";
            }
            countMap.put("Manage User", response);
            System.out.println(" inside Linked map " + countMap.size());
        }
         
           if(SingletonClass.privStr.toUpperCase().contains("MEETING MAIL")){
                response = "0";
            meetingDetailsbuffer.append("&").append(SingletonClass.operaionType).append("=").append("meetingCount");
            response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));


            System.out.println("response after calling: " + response);
            if(response.equalsIgnoreCase("invalid")){
                response = "0";
            }
            countMap.put("Meeting Mail", response);
            System.out.println(" inside Linked map " + countMap.size());
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMap;
    }
}
