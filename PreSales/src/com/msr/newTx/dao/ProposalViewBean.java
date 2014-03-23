/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.dao;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author root
 */
public class ProposalViewBean {
    private List<HashMap> ilcList;
    private List<HashMap> flcList;

    /**
     * @return the ilcList
     */
    public List<HashMap> getIlcList() {
        return ilcList;
    }

    /**
     * @param ilcList the ilcList to set
     */
    public void setIlcList(List<HashMap> ilcList) {
        this.ilcList = ilcList;
    }

    /**
     * @return the flcList
     */
    public List<HashMap> getFlcList() {
        return flcList;
    }

    /**
     * @param flcList the flcList to set
     */
    public void setFlcList(List<HashMap> flcList) {
        this.flcList = flcList;
    }
}
