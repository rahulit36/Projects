/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;

/**
 *
 * @author root
 */
public class ReminderSettingThread extends Thread{
    private String urlTemp = "";
    public ReminderSettingThread(String url){
        this.urlTemp = url.replaceAll(" ", "%20");
    }
    
    public void run(){
        try {
             new callServerUrl().urlProcessiong(urlTemp);
             System.out.println(" thread is in running for reminder! "+urlTemp);
            } 
        catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }
}
