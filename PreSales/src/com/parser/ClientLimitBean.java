/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

/**
 *
 * @author root
 */
public class ClientLimitBean {
    
    private String beneficiary;
    private String bank;
    private String clientLimit;
    private String availableLimit;
    private String clientName;
    private String sanctionDate;
    private String limitType;
    private String limitId;

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }
    public String getSanctionDate() {
        return sanctionDate;
    }

    public void setSanctionDate(String sanctionDate) {
        this.sanctionDate = sanctionDate;
    }
    /**
     * @return the beneficiary
     */
    public String getBeneficiary() {
        return beneficiary;
    }

    /**
     * @param beneficiary the beneficiary to set
     */
    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * @return the clientLimit
     */
    public String getClientLimit() {
        return clientLimit;
    }

    /**
     * @param clientLimit the clientLimit to set
     */
    public void setClientLimit(String clientLimit) {
        this.clientLimit = clientLimit;
    }

    /**
     * @return the availableLimit
     */
    public String getAvailableLimit() {
        return availableLimit;
    }

    /**
     * @param availableLimit the availableLimit to set
     */
    public void setAvailableLimit(String availableLimit) {
        this.availableLimit = availableLimit;
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
     * @return the limitId
     */
    public String getLimitId() {
        return limitId;
    }

    /**
     * @param limitId the limitId to set
     */
    public void setLimitId(String limitId) {
        this.limitId = limitId;
    }
}
