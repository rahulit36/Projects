/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

/**
 *
 * @author root
 */
public class HistoryBean {
    
            String txNo="";
            String txStatus="";
            String txType="";
            String clientName="";
            String bankName="";
            String beneficiary="";
            String limitSanctioned="";
            String santionDate="";
            String availableLimit="";

    public String getTxNo() {
        return txNo;
    }

    public void setTxNo(String txNo) {
        this.txNo = txNo;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getLimitSanctioned() {
        return limitSanctioned;
    }

    public void setLimitSanctioned(String limitSanctioned) {
        this.limitSanctioned = limitSanctioned;
    }

    public String getSantionDate() {
        return santionDate;
    }

    public void setSantionDate(String santionDate) {
        this.santionDate = santionDate;
    }

    public String getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(String availableLimit) {
        this.availableLimit = availableLimit;
    }
}
