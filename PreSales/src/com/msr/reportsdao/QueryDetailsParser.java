/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author piyush
 * @Since march 06, 2013
 * @version V1.0
 */
public class QueryDetailsParser extends DefaultHandler{
    
    QueryDetailBean queryBean = null;
    List<QueryDetailBean> queryList = new ArrayList<QueryDetailBean>();
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {
     
  if(qName.equals("queries")){
            queryBean =  new QueryDetailBean();
            queryBean.setClientName(atts.getValue("clientName"));
            queryBean.setBankName(atts.getValue("bankName"));
            queryBean.setRmName(atts.getValue("rm"));
            queryBean.setRaiseDate(atts.getValue("queryRaiseDate"));
            queryBean.setQueryDetails(atts.getValue("queryDetails"));
            queryBean.setQueryStatus(atts.getValue("queryStatus"));
            queryBean.setComments(atts.getValue("queryComments"));
            queryBean.setCloseDate(atts.getValue("queryClosedDate"));
        }
    }


    public void endElement(String uri, String name, String qName)
    {
       
        if(qName.equals("queries")){
            queryList.add(queryBean);
        }
           
        
    }
    
    public List<QueryDetailBean> getQueryList(){
        return queryList;
    }
}