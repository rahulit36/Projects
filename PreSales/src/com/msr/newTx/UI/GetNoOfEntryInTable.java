package com.msr.newTx.UI;

import com.msr.tracking.UI.*;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class GetNoOfEntryInTable {

    /**
     *
     * @return
     */
    public LinkedHashMap getCounts() {
        LinkedHashMap countMap = new LinkedHashMap();
        try {
            // fetch the data from url hit for all the below lists and then check the list is empty or not and according to them set the accordion name and count

            countMap.put("Client Details", " ");
            countMap.put("Meeting Details", " ");
            countMap.put("Proposal Details", " " );
            countMap.put("Send E-Mail", " ");
            countMap.put("Document List", " " );
            countMap.put("Bank Queries", " " );
            countMap.put("Comments", " " );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMap;
    }
}
