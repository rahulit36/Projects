/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

/**
 *
 * @author root
 */
public class BankTransactionBean {
    private String txnType;
    private String rmName;
    private String clientName;
    private String txnStatus;
    private String actualLimit;
    private String proposedLimit;
    private String limitType;
    private String limitInitiated;
    private String limitCompleted;

    /**
     * @return the txnType
     */
    public String getTxnType() {
        return txnType;
    }

    /**
     * @param txnType the txnType to set
     */
    public void setTxnType(String txnType) {
        this.txnType = txnType;
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
     * @return the txnStatus
     */
    public String getTxnStatus() {
        return txnStatus;
    }

    /**
     * @param txnStatus the txnStatus to set
     */
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    /**
     * @return the actualLimit
     */
    public String getActualLimit() {
        return actualLimit;
    }

    /**
     * @param actualLimit the actualLimit to set
     */
    public void setActualLimit(String actualLimit) {
        this.actualLimit = actualLimit;
    }

    /**
     * @return the proposedLimit
     */
    public String getProposedLimit() {
        return proposedLimit;
    }

    /**
     * @param proposedLimit the proposedLimit to set
     */
    public void setProposedLimit(String proposedLimit) {
        this.proposedLimit = proposedLimit;
    }

    /**
     * @return the limitType
     */
    public String getLimitType() {
        return limitType;
    }

    /**
     * @param limitType the limitType to set
     */
    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    /**
     * @return the limitInitiated
     */
    public String getLimitInitiated() {
        return limitInitiated;
    }

    /**
     * @param limitInitiated the limitInitiated to set
     */
    public void setLimitInitiated(String limitInitiated) {
        this.limitInitiated = limitInitiated;
    }

    /**
     * @return the limitCompleted
     */
    public String getLimitCompleted() {
        return limitCompleted;
    }

    /**
     * @param limitCompleted the limitCompleted to set
     */
    public void setLimitCompleted(String limitCompleted) {
        this.limitCompleted = limitCompleted;
    }
    
    
}
