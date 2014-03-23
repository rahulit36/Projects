/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.ftp.bean;

import java.util.List;

/**
 * @author Satya
 * @version V1.0
 * @since V1.0
 * @details pojo meeting details
 */
public class MeetingsDetails 
{
    private String scheduleDate;
    private String scheduleTime;
    private String clientName;
    private String contactPerson;
    private String meetingComment;
    private String caseSummry;
    private String place;
    private String region;
    private String remienderTime;
    private String meetingId;
    private String turnOver;
    private String conDesgnation;

    public String getConDesgnation() {
        return conDesgnation;
    }

    public void setConDesgnation(String conDesgnation) {
        this.conDesgnation = conDesgnation;
    }

    public String getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }

    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
      private List<String> contactList;

    /**
     * @return the scheduleDate
     */
    public String getScheduleDate() {
        return scheduleDate;
    }

    /**
     * @param scheduleDate the scheduleDate to set
     */
    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    /**
     * @return the scheduleTime
     */
    public String getScheduleTime() {
        return scheduleTime;
    }

    /**
     * @param scheduleTime the scheduleTime to set
     */
    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the meetingComment
     */
    public String getMeetingComment() {
        return meetingComment;
    }

    /**
     * @param meetingComment the meetingComment to set
     */
    public void setMeetingComment(String meetingComment) {
        this.meetingComment = meetingComment;
    }

    public String getCaseSummry() {
        return caseSummry;
    }

    public void setCaseSummry(String caseSummry) {
        this.caseSummry = caseSummry;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the remienderTime
     */
    public String getRemienderTime() {
        return remienderTime;
    }

    /**
     * @param remienderTime the remienderTime to set
     */
    public void setRemienderTime(String remienderTime) {
        this.remienderTime = remienderTime;
    }

    /**
     * @return the contactList
     */
    public List<String> getContactList() {
        return contactList;
    }

    /**
     * @param contactList the contactList to set
     */
    public void setContactList(List<String> contactList) {
        this.contactList = contactList;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
}
