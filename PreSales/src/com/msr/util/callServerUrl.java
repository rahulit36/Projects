package com.msr.util;

import java.net.URLEncoder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author Satya
 * @version V1.0
 * @since 25 JAN
 */
public class callServerUrl {

    /**
     *
     * @param httpUrl URL OF SERVER FOR CALLING
     * @return STRING AS RESPONSE OF URL
     */
    public String urlProcessiong(String httpUrl) {
        HttpClient httpClient = null;
        PostMethod method = null;
        String response = "invalid";
        try {
            httpClient = new HttpClient();
            method = new PostMethod(httpUrl);
            httpClient.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
                System.out.println("Real Response = " + response);
//                httpClient.set
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally
        {
            if(method!=null)method.releaseConnection();
        }
       
        System.out.println("httpUrl " + httpUrl + " =================== " + response);
        return response;
    }

    public static void main(String argp[]) {
        System.out.println(new callServerUrl().urlProcessiong("http://google.com"));
    }
}
