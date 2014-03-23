/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.util.SingletonClass;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class DocParser extends DefaultHandler {
//    <docs clientName="" bankName="abc bank" docName="docsName" docType=" docsType" docsUrl=" docsPath" serverUrl=" serverUrl"/>
    DocumentsDetailBean docBean = null;
    List<DocumentsDetailBean> docList = new ArrayList<DocumentsDetailBean>();
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName+" docName: "+atts.getValue("docName")+"dirName: "+atts.getValue("dirName"));
        if(tagName.equals("docs")){
            docBean = new DocumentsDetailBean();
            docBean.setClientName(SingletonClass.replaceEmptySpace(atts.getValue("clientName")));
            docBean.setDocName(SingletonClass.replaceEmptySpace(atts.getValue("docName")));
            docBean.setDocType(SingletonClass.replaceEmptySpace(atts.getValue("docType")));
            docBean.setStatus(SingletonClass.replaceEmptySpace(atts.getValue("docStatus")));
            docBean.setDocFTPPath(SingletonClass.replaceEmptySpace(atts.getValue("docsUrl")));
            docBean.setDocId(SingletonClass.replaceEmptySpace(atts.getValue("docId")));
            docBean.setBenName(SingletonClass.replaceEmptySpace(atts.getValue("beneficiary")));
            docBean.setDocLocation(SingletonClass.replaceEmptySpace(atts.getValue("dirName")));
            docList.add(docBean);
            
        }
    }
    
    public List<DocumentsDetailBean> getdocList(){
        return docList;
    }
}
