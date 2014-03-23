/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Piyush
 * @since feb20, 2013
 * @version V1.0
 */
public class AllClientXMLParser extends DefaultHandler{
    
    AllClientDetailBean clientBean = null;
    List<AllClientDetailBean> clientsList = new ArrayList<AllClientDetailBean>();
    
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
//        client name="piyush2000" address="" landline="" faxNo="" category="null" turnOver="12345.00" applimitAmount="22000.00"/>
         if(tagName.equals("client")){
             clientBean = new AllClientDetailBean();
             clientBean.setName(atts.getValue("name"));
             clientBean.setAddress(atts.getValue("address"));
             clientBean.setLandline(atts.getValue("landline"));
             clientBean.setCategory(atts.getValue("category"));
             clientBean.setApplimitAmount(atts.getValue("applimitAmount"));
             clientBean.setTurnOver(atts.getValue("turnOver"));
             clientsList.add(clientBean);
             
         }
     }
    
    public List<AllClientDetailBean> getclientsList(){
        return clientsList;
    }
}
