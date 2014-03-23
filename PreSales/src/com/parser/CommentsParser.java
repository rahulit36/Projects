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
public class CommentsParser extends DefaultHandler{
    commentBean cBean = null;
    List<commentBean> comentsList = new ArrayList<commentBean>();
    public List<commentBean> getCommentsList(){
        
        return comentsList;
    }
    
     public void startElement(String uri, String localName, String tagName, Attributes atts) {
         if(tagName.equals("comment")){
         cBean = new commentBean();
         cBean.setCmntId(SingletonClass.replaceEmptySpace(atts.getValue("id")));
         cBean.setCmntby(SingletonClass.replaceEmptySpace(atts.getValue("commentsBy")));
         cBean.setCmnton(SingletonClass.replaceEmptySpace(atts.getValue("date")));
         cBean.setComment(SingletonClass.replaceEmptySpace(atts.getValue("comments")));
         comentsList.add(cBean);
         }
     }
}
