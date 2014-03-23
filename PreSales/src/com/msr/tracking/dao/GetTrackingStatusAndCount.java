package com.msr.tracking.dao;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.util.LinkedHashMap;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class GetTrackingStatusAndCount {

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




            // check from backend: there are the list of data from database, and then set the accordion name

            System.out.println(" inside Linked map for: " + SingletonClass.designation);
//            if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
//
//                StringBuffer meetingDetailsbuffer = new StringBuffer();
//                meetingDetailsbuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append("txnCounts").append("&").append(SingletonClass.userName).append("=").append(email);// 
//
//                meetingDetailsbuffer.append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Documents");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Awaiting Documents", response);
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Documents"), meetingDetailsbuffer.indexOf("Awaiting Documents") + "Awaiting Documents".length(), "Create Limit");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Create Limit", response);
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Create Limit"), meetingDetailsbuffer.indexOf("Create Limit") + "Create Limit".length(), "Awaiting Limit Setup");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Awaiting Limit Setup", response);
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Limit Setup"), meetingDetailsbuffer.indexOf("Awaiting Limit Setup") + "Awaiting Limit Setup".length(), "Limit Sanctioned");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Limit Sanctioned", response);
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Limit Sanctioned"), meetingDetailsbuffer.indexOf("Limit Sanctioned") + "Limit Sanctioned".length(), "Portfolio Client");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Portfolio Client", response);
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Portfolio Client"), meetingDetailsbuffer.indexOf("Portfolio Client") + "Portfolio Client".length(), "Expiry Transition");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Expiry Transition", response);
//
//                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Expiry Transition"), meetingDetailsbuffer.indexOf("Expiry Transition") + "Expiry Transition".length(), "Opportunity Lost");
//                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
//                if (response.equals("invalid")) {
//                    response = "0";
//                }
//                countMap.put("Opportunity Lost", response );
//                System.out.println("inside if response: " + response+ " url: " + meetingDetailsbuffer.toString());
//            }
        
        
        //            else  if (SingletonClass.designation.equalsIgnoreCase("rm")) {
            //                StringBuffer meetingDetailsbuffer = new StringBuffer();
            //                meetingDetailsbuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append("txnCounts").append("&").append(SingletonClass.userName).append("=").append(email);// 
            //
            //                meetingDetailsbuffer.append("&").append(SingletonClass.txStatus).append("=").append("Identification Of Client");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Identification Of Client", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Identification Of Client"), meetingDetailsbuffer.indexOf("Identification Of Client") + "Identification Of Client".length(), "Awaiting Internal Approval");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Awaiting Internal Approval", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Internal Approval"), meetingDetailsbuffer.indexOf("Awaiting Internal Approval") + "Awaiting Internal Approval".length(), "Send Offer Letter");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Send Offer Letter", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Send Offer Letter"), meetingDetailsbuffer.indexOf("Send Offer Letter") + "Send Offer Letter".length(), "Awaiting Client Approval");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Awaiting Client Approval", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Client Approval"), meetingDetailsbuffer.indexOf("Awaiting Client Approval") + "Awaiting Client Approval".length(), "Awaiting Mandate");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Awaiting Mandate", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Mandate"), meetingDetailsbuffer.indexOf("Awaiting Mandate") + "Awaiting Mandate".length(), "Awaiting Documents");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Awaiting Documents", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Documents"), meetingDetailsbuffer.indexOf("Awaiting Documents") + "Awaiting Documents".length(), "Create Limit");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Create Limit", response);
            //                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Create Limit"), meetingDetailsbuffer.indexOf("Create Limit") + "Create Limit".length(), "Opportunity Lost");
            //                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
            //                countMap.put("Opportunity Lost", response);
            //                
            //            }
//            else {
                StringBuffer meetingDetailsbuffer = new StringBuffer();
                meetingDetailsbuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append("txnCounts").append("&").append(SingletonClass.userName).append("=").append(email);// 

                meetingDetailsbuffer.append("&").append(SingletonClass.txStatus).append("=").append("Identification Of Client");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Identification Of Client", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Identification Of Client"), meetingDetailsbuffer.indexOf("Identification Of Client") + "Identification Of Client".length(), "Awaiting Internal Approval");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Awaiting Internal Approval", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Internal Approval"), meetingDetailsbuffer.indexOf("Awaiting Internal Approval") + "Awaiting Internal Approval".length(), "Send Offer Letter");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Send Offer Letter", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Send Offer Letter"), meetingDetailsbuffer.indexOf("Send Offer Letter") + "Send Offer Letter".length(), "Awaiting Client Approval");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Awaiting Client Approval", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Client Approval"), meetingDetailsbuffer.indexOf("Awaiting Client Approval") + "Awaiting Client Approval".length(), "Awaiting Mandate");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Awaiting Mandate", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Mandate"), meetingDetailsbuffer.indexOf("Awaiting Mandate") + "Awaiting Mandate".length(), "Awaiting Documents");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Awaiting Documents", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Documents"), meetingDetailsbuffer.indexOf("Awaiting Documents") + "Awaiting Documents".length(), "Create Limit");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Create Limit", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Create Limit"), meetingDetailsbuffer.indexOf("Create Limit") + "Create Limit".length(), "Awaiting Limit Setup");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Awaiting Limit Setup", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Awaiting Limit Setup"), meetingDetailsbuffer.indexOf("Awaiting Limit Setup") + "Awaiting Limit Setup".length(), "Limit Sanctioned");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Limit Sanctioned", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Limit Sanctioned"), meetingDetailsbuffer.indexOf("Limit Sanctioned") + "Limit Sanctioned".length(), "Portfolio Client");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Portfolio Client", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Portfolio Client"), meetingDetailsbuffer.indexOf("Portfolio Client") + "Portfolio Client".length(), "Expiry Transition");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Expiry Transition", response);
                meetingDetailsbuffer.replace(meetingDetailsbuffer.indexOf("Expiry Transition"), meetingDetailsbuffer.indexOf("Expiry Transition") + "Expiry Transition".length(), "Opportunity Lost");
                response = serverUrl1.urlProcessiong(meetingDetailsbuffer.toString().replaceAll(" ", "%20"));
                if (response.equals("invalid")) {
                    response = "0";
                }
                countMap.put("Opportunity Lost", response);

//            }
            System.out.println(" inside Linked map " + countMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMap;
    }
}
