/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.dao;

/**
 *
 * @author root
 */
public class MeetingDetailBean {
    private String rmName = "";
    private String reminderTime = "";
        private String rmDisName = "";
    private String cmpnyName = "";
    private String turnOver = "";
    private String cntctPerson = "";
    private String mobNumber = "";
    private String dateTime = "";
    private String MeetingPlace = "";
    private String purpose = "";   
     private    String cc;
    private   String bcc;
    private    String to;
    private String mobileNo;
//    fields added by piyush------------------
    private String rmFullName = "";
    private String rmNumber = "";
//----------------------------
 
    
    public void setCompanyName(String cmpnyName){
        this.cmpnyName = cmpnyName;
    }
    
    public String getCompanyName(){
        return this.cmpnyName;
    }
    public void setTurnOver(String turnOver){
        this.turnOver = turnOver;
    }
    
    public String getTurnOver(){
        return this.turnOver;
    }
    public void setContactPerson(String cntctPerson){
        this.cntctPerson = cntctPerson;
    }
    
    public String getContactPerson(){
        return this.cntctPerson;
    }
    public void setMobNumber(String mobNumber){
        this.mobNumber = mobNumber;
    }
    
    public String getMobNumber(){
        return this.mobNumber;
    }
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }
    
    public String getDateTime(){
        return this.dateTime;
    }
    public void setMeetingPlace(String MeetingPlace){
        this.MeetingPlace = MeetingPlace;
    }
    
    public String getMeetingPlace(){
        return this.MeetingPlace;
    }
    public void setPurpose(String purpose){
        this.purpose = purpose;
    }
    
    public String getPurpose(){
        return this.purpose;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return the bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the rmName
     */
    public String getRmName() {
        return rmName;
    }

    /**
     * @param rmName the rmName to set
     */
    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    /**
     * @return the reminderTime
     */
    public String getReminderTime() {
        return reminderTime;
    }

    /**
     * @param reminderTime the reminderTime to set
     */
    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
//    methods added by piyush--------------
    public void setRmFullName(String rmFullName){
        this.rmFullName = rmFullName;
    }
    
    public String getRmFullName(){
        return this.rmFullName;
    }
    public void setRmNumber(String rmNumber){
        this.rmNumber = rmNumber;
    }
    
    public String getRmNumber(){
        return this.rmNumber;
    }
//    -----------------------------

    /**
     * @return the rmDisName
     */
    public String getRmDisName() {
        return rmDisName;
    }

    /**
     * @param rmDisName the rmDisName to set
     */
    public void setRmDisName(String rmDisName) {
        this.rmDisName = rmDisName;
    }
    
}
