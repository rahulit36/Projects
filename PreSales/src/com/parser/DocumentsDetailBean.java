/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

/**
 *
 * @author root
 */
public class DocumentsDetailBean {
    
    private String docName;
    private String docType;
    private String docFTPPath;
    private String status;
    private String clientName;
    private String docId;
    private String benName;
    private String docLocation;
    private String docsUrlPath;

    public String getDocsUrlPath() {
        return docsUrlPath;
    }

    public void setDocsUrlPath(String docsUrlPath) {
        this.docsUrlPath = docsUrlPath;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
    

    /**
     * @return the docName
     */
    public String getDocName() {
        return docName;
    }

    /**
     * @param docName the docName to set
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the docFTPPath
     */
    public String getDocFTPPath() {
        return docFTPPath;
    }

    /**
     * @param docFTPPath the docFTPPath to set
     */
    public void setDocFTPPath(String docFTPPath) {
        this.docFTPPath = docFTPPath;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the benName
     */
    public String getBenName() {
        return benName;
    }

    /**
     * @param benName the benName to set
     */
    public void setBenName(String benName) {
        this.benName = benName;
    }

    /**
     * @return the docLocation
     */
    public String getDocLocation() {
        return docLocation;
    }

    /**
     * @param docLocation the docLocation to set
     */
    public void setDocLocation(String docLocation) {
        this.docLocation = docLocation;
    }
}
