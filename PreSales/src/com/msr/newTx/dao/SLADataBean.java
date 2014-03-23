/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.dao;

/**
 *
 * @author root
 */
public class SLADataBean {

    private String scheduleBank = "";
    private String companyName = "";
    private String amntPerMnth = "";
    private String clientAddrs = "";
    private String contactPerson = "";
    private String profileInfo = "";
    private String solutionInfo = "";
    private String frgnIntRate = "";
    private String usance = "";
    private String intRate = "";
    private String hndlingChrge = "";
    private String ourChrge = "";
    private String ourChrgeType = "";
    private String expBusiness = "";

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    
    public String getClientAddrs() {
        return clientAddrs;
    }

    public void setClientAddrs(String clientAddrs) {
        this.clientAddrs = clientAddrs;
    }

    public String getExpBusiness() {
        return expBusiness;
    }

    public void setExpBusiness(String expBusiness) {
        this.expBusiness = expBusiness;
    }

    public String getOurChrgeType() {
        return ourChrgeType;
    }

    public void setOurChrgeType(String ourChrgeType) {
        this.ourChrgeType = ourChrgeType;
    }
    private String margin = "";
    private String approvedBankCheck = "";
    private boolean isFlc = false;

    public boolean getIsFlc() {
        return isFlc;
    }

    public void setIsFlc(boolean isFlc) {
        this.isFlc = isFlc;
    }

    public String getOurChrge() {
        return ourChrge;
    }

    public void setOurChrge(String ourChrge) {
        this.ourChrge = ourChrge;
    }
    public String getApprovedBankCheck() {
        return approvedBankCheck;
    }

    public void setApprovedBankCheck(String approvedBankCheck) {
        this.approvedBankCheck = approvedBankCheck;
    }
    

    /**
     * @return the scheduleBank
     */
    public String getScheduleBank() {
        return scheduleBank;
    }

    /**
     * @param scheduleBank the scheduleBank to set
     */
    public void setScheduleBank(String scheduleBank) {
        this.scheduleBank = scheduleBank;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the amntPerMnth
     */
    public String getAmntPerMnth() {
        return amntPerMnth;
    }

    /**
     * @param amntPerMnth the amntPerMnth to set
     */
    public void setAmntPerMnth(String amntPerMnth) {
        this.amntPerMnth = amntPerMnth;
    }

    /**
     * @return the profileInfo
     */
    public String getProfileInfo() {
        return profileInfo;
    }

    /**
     * @param profileInfo the profileInfo to set
     */
    public void setProfileInfo(String profileInfo) {
        this.profileInfo = profileInfo;
    }

    /**
     * @return the solutionInfo
     */
    public String getSolutionInfo() {
        return solutionInfo;
    }

    /**
     * @param solutionInfo the solutionInfo to set
     */
    public void setSolutionInfo(String solutionInfo) {
        this.solutionInfo = solutionInfo;
    }

    /**
     * @return the frgnIntRate
     */
    public String getFrgnIntRate() {
        return frgnIntRate;
    }

    /**
     * @param frgnIntRate the frgnIntRate to set
     */
    public void setFrgnIntRate(String frgnIntRate) {
        this.frgnIntRate = frgnIntRate;
    }

    /**
     * @return the usance
     */
    public String getUsance() {
        return usance;
    }

    /**
     * @param usance the usance to set
     */
    public void setUsance(String usance) {
        this.usance = usance;
    }

    /**
     * @return the intRate
     */
    public String getIntRate() {
        return intRate;
    }

    /**
     * @param intRate the intRate to set
     */
    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    /**
     * @return the hndlingChrge
     */
    public String getHndlingChrge() {
        return hndlingChrge;
    }

    /**
     * @param hndlingChrge the hndlingChrge to set
     */
    public void setHndlingChrge(String hndlingChrge) {
        this.hndlingChrge = hndlingChrge;
    }

    /**
     * @return the margin
     */
    public String getMargin() {
        return margin;
    }

    /**
     * @param margin the margin to set
     */
    public void setMargin(String margin) {
        this.margin = margin;
    }

}
