/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author piyush chawla
 * @since  march 08, 2013
 * @version V1.0
 */
public class RmTxExcel {

	private String inputFile = null;
	XSSFWorkbook hwb = null;
    XSSFSheet sheet = null;
    String fileName = null;
	List<RMTransactionBean> rmTxList = null;
	
	
	public RmTxExcel(String input,  List<RMTransactionBean> meetingList) {
//		hwb = new XSSFWorkbook();
		inputFile = input;
                this.rmTxList = meetingList;
		try {
			makingExcelReport();
                        createData(rmTxList);
			writeData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	int i = 3;
	public void makingExcelReport() throws FileNotFoundException, IOException {

//		  fileName = "/home/Reports/GeneratedRepots/FlC Daily Report.xlsx";
		  
		  hwb = new XSSFWorkbook();
	        sheet = hwb.createSheet("Transaction Report");
	        XSSFFont font1 = hwb.createFont();
	        //apply color to the font
	        font1.setColor(XSSFFont.COLOR_NORMAL);
	        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        
	        font1.setFontHeightInPoints((short) 10);
	        
	        // apply fore ground color to the cell
	        XSSFColor preHeader = new XSSFColor(new java.awt.Color(215, 228, 189));
	        XSSFColor headColor = new XSSFColor(new java.awt.Color(255,255,255));
	        // style2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        
//	        Meeting Report from January 2012
	        XSSFCellStyle mainHeaderStyleLeft = hwb.createCellStyle();
	        mainHeaderStyleLeft.setBorderTop(CellStyle.BORDER_MEDIUM);
                mainHeaderStyleLeft.setBorderBottom(CellStyle.BORDER_MEDIUM);
                mainHeaderStyleLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);
                mainHeaderStyleLeft.setBorderRight(CellStyle.BORDER_NONE);
                mainHeaderStyleLeft.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                mainHeaderStyleLeft.setFillForegroundColor(headColor);

	        XSSFCellStyle mainHeaderStyleright = hwb.createCellStyle();
	        mainHeaderStyleright.setBorderTop(CellStyle.BORDER_MEDIUM);
	        mainHeaderStyleright.setBorderBottom(CellStyle.BORDER_MEDIUM);
	        mainHeaderStyleright.setBorderRight(CellStyle.BORDER_MEDIUM);
	        mainHeaderStyleright.setBorderLeft(CellStyle.BORDER_NONE);
	        mainHeaderStyleright.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        mainHeaderStyleright.setFillForegroundColor(headColor);
	        
	        XSSFCellStyle mainHeaderStylemidle = hwb.createCellStyle();
	        mainHeaderStylemidle.setBorderTop(CellStyle.BORDER_MEDIUM);
	        mainHeaderStylemidle.setBorderBottom(CellStyle.BORDER_MEDIUM);
	        mainHeaderStylemidle.setBorderRight(CellStyle.BORDER_NONE);
	        mainHeaderStylemidle.setBorderLeft(CellStyle.BORDER_NONE);
	        mainHeaderStylemidle.setFont(font1);
                mainHeaderStylemidle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        mainHeaderStylemidle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        mainHeaderStylemidle.setFillForegroundColor(headColor);
	        
	        XSSFRow rowhead = sheet.createRow((short) i);
	        XSSFCell cell0h = rowhead.createCell((short) 0);
	        sheet.setColumnWidth((short) 0, (short) 2000);
	       
	        XSSFCell cell1h = rowhead.createCell((short) 1);
	        sheet.setColumnWidth((short) 1, (short) 2000);
	        cell1h.setCellStyle(mainHeaderStyleLeft);
	        XSSFCell cell2h = rowhead.createCell((short) 2);
	        sheet.setColumnWidth((short) 2, (short) 3000);
	        cell2h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell3h = rowhead.createCell((short) 3);
	        sheet.setColumnWidth((short) 3, (short) 5000);
	        cell3h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell4h = rowhead.createCell((short) 4);
	        sheet.setColumnWidth((short) 4, (short) 5000);
	        cell4h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell5h = rowhead.createCell((short) 5);
	        sheet.setColumnWidth((short) 5, (short) 2000);
	        cell5h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell6h = rowhead.createCell((short) 6);
	        cell6h.setCellValue("RM Transaction Report");
	        cell6h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 6, (short) 4000);
	        XSSFCell cell7h = rowhead.createCell((short) 7);
	        cell7h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 7, (short) 4000);
	        XSSFCell cell8h = rowhead.createCell((short) 8);
	        sheet.setColumnWidth((short) 8, (short) 3000);
	        cell8h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 9, (short) 4000);
                XSSFCell cell9h = rowhead.createCell((short) 9);
                cell9h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 10, (short) 4000);
                XSSFCell cell10h = rowhead.createCell((short) 10);
                cell10h.setCellStyle(mainHeaderStylemidle);
                sheet.setColumnWidth((short) 11, (short) 4000);
                XSSFCell cell11h = rowhead.createCell((short) 11);
                cell11h.setCellStyle(mainHeaderStylemidle);
                sheet.setColumnWidth((short) 12, (short) 4000);
                XSSFCell cell12h = rowhead.createCell((short) 12);
	        cell12h.setCellStyle(mainHeaderStyleright);
	        
	        
	        XSSFCellStyle headingTagStyle = hwb.createCellStyle();
	        headingTagStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        headingTagStyle.setBorderTop(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderBottom(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderRight(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderLeft(CellStyle.BORDER_HAIR);
	        headingTagStyle.setFont(font1);
	        headingTagStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        headingTagStyle.setFillForegroundColor(preHeader);
                headingTagStyle.setWrapText(true);
                
	        
	        i= i+2;
	        XSSFRow headerRow = sheet.createRow((short) i);
//	        S.No.	Date	RM	Company	Contact Person	Mobile	 ILC/FLC	Discussion Points (Min describe in 50 Words)
	        XSSFCell cell0 = headerRow.createCell((short) 0);
	        XSSFCell cell1 = headerRow.createCell((short) 1);
	        cell1.setCellValue("S.No.");
	        cell1.setCellStyle(headingTagStyle);
	        XSSFCell cell2 = headerRow.createCell((short) 2);
	        cell2.setCellValue("Nature Of Transaction");
                cell2.setCellStyle(headingTagStyle);
	        XSSFCell cell3 = headerRow.createCell((short) 3);
	        cell3.setCellValue("Client Name");
	        cell3.setCellStyle(headingTagStyle);
	        XSSFCell cell4 = headerRow.createCell((short) 4);
	        cell4.setCellValue("Bank Name");
	        cell4.setCellStyle(headingTagStyle);
	        XSSFCell cell5 = headerRow.createCell((short) 5);
	        cell5.setCellValue("Type of Limit");
	        cell5.setCellStyle(headingTagStyle);
	        XSSFCell cell6 = headerRow.createCell((short) 6);
	        cell6.setCellValue("Proposed Limit");
	        cell6.setCellStyle(headingTagStyle);
	        XSSFCell cell7 = headerRow.createCell((short) 7);
	        cell7.setCellValue("Sanction Limit");
	        cell7.setCellStyle(headingTagStyle);
	        XSSFCell cell8 = headerRow.createCell((short) 8);
	        cell8.setCellValue("Usance");
	        cell8.setCellStyle(headingTagStyle);
	        XSSFCell cell9 = headerRow.createCell((short) 9);
	        cell9.setCellValue("interest Rate");
	        cell9.setCellStyle(headingTagStyle);
                XSSFCell cell10 = headerRow.createCell((short) 10);
	        cell10.setCellValue("margin");
	        cell10.setCellStyle(headingTagStyle);
                XSSFCell cell11 = headerRow.createCell((short) 11);
	        cell11.setCellValue("our Charges");
	        cell11.setCellStyle(headingTagStyle);
                XSSFCell cell12 = headerRow.createCell((short) 12);
	        cell12.setCellValue("Our Charges Type");
	        cell12.setCellStyle(headingTagStyle);
                XSSFCell cell13 = headerRow.createCell((short) 13);
	        cell13.setCellValue("handling Charges");
	        cell13.setCellStyle(headingTagStyle);
                i++;
	       
	}
	
	public void createData(List<RMTransactionBean> rmTxList)  throws FileNotFoundException, IOException {
		XSSFDataFormat fmt = hwb.createDataFormat();
            
                XSSFFont font = hwb.createFont();
	        //apply color to the font
	        font.setColor(XSSFFont.COLOR_NORMAL);
	        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        
	        font.setFontHeightInPoints((short) 10);
                XSSFCellStyle monthLeft = hwb.createCellStyle();
	        monthLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
	        monthLeft.setBorderTop(CellStyle.BORDER_HAIR);
	        monthLeft.setBorderBottom(CellStyle.BORDER_HAIR);
	        monthLeft.setBorderRight(CellStyle.BORDER_NONE);
	        monthLeft.setBorderLeft(CellStyle.BORDER_HAIR);
                monthLeft.setFont(font);
                
                XSSFCellStyle monthRight = hwb.createCellStyle();
	        monthRight.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        monthRight.setBorderTop(CellStyle.BORDER_HAIR);
	        monthRight.setBorderBottom(CellStyle.BORDER_HAIR);
	        monthRight.setBorderRight(CellStyle.BORDER_HAIR);
	        monthRight.setBorderLeft(CellStyle.BORDER_NONE);
                    
                XSSFCellStyle monthMiddle = hwb.createCellStyle();
	        monthMiddle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        monthMiddle.setBorderTop(CellStyle.BORDER_HAIR);
	        monthMiddle.setBorderBottom(CellStyle.BORDER_HAIR);
	        monthMiddle.setBorderRight(CellStyle.BORDER_NONE);
	        monthMiddle.setBorderLeft(CellStyle.BORDER_NONE);
                
                
                XSSFCellStyle dataStyle = hwb.createCellStyle();
	        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        dataStyle.setBorderTop(CellStyle.BORDER_HAIR);
	        dataStyle.setBorderBottom(CellStyle.BORDER_HAIR);
	        dataStyle.setBorderRight(CellStyle.BORDER_HAIR);
	        dataStyle.setBorderLeft(CellStyle.BORDER_HAIR);
                
                XSSFCellStyle numericDataStyle = hwb.createCellStyle();
	        numericDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        numericDataStyle.setBorderTop(CellStyle.BORDER_HAIR);
	        numericDataStyle.setBorderBottom(CellStyle.BORDER_HAIR);
	        numericDataStyle.setBorderRight(CellStyle.BORDER_HAIR);
	        numericDataStyle.setBorderLeft(CellStyle.BORDER_HAIR);
                numericDataStyle.setDataFormat(fmt.getFormat("#,##0.00"));
                    int sno = 1;
                    String[] statusArr = new String[]{"Identification Of Client" ,"Awaiting Internal Approval", "Send Offer Letter","Awaiting Client Approval","Awaiting Maindate", "Awaiting Documents", "Create Limit", "Awaiting LC Setup","Limit Sanctioned", "Portfolio Client", "Expiry Transition", "Bussiness Lost"};
                    for(String status :statusArr){
                        i++;
                        
                        XSSFRow statusRow = sheet.createRow((short) i);
                        XSSFCell statusCell = statusRow.createCell((short)1);
                        statusCell.setCellValue(status);
                        statusCell.setCellStyle(monthLeft);
                        
                        statusCell = statusRow.createCell((short)2);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)3);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)4);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)5);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)6);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)7);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)8);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)9);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)10);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)11);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)12);
                        statusCell.setCellStyle(monthMiddle);
                        statusCell = statusRow.createCell((short)13);
                        statusCell.setCellStyle(monthRight);
                        
                        i++;
                        
                      boolean isRecordExist=false;  
                        
                        Iterator itr2 = rmTxList.iterator();
                         while(itr2.hasNext()){
                             RMTransactionBean rmTxBean = (RMTransactionBean)itr2.next();
                             
                    try {
                       
                        if(status.equalsIgnoreCase(rmTxBean.getTxnStatus())){
                            isRecordExist=true;
                        XSSFRow row = sheet.createRow((short) i);
                        XSSFCell cell0 = row.createCell((short)0);
                        
                        XSSFCell cell1 = row.createCell((short)1);
                        cell1.setCellValue(sno);
                        cell1.setCellStyle(dataStyle);
                        XSSFCell cell2 = row.createCell((short)2);
                        cell2.setCellValue(rmTxBean.getTxnType());
                        cell2.setCellStyle(dataStyle);
                        XSSFCell cell3 = row.createCell((short)3);
                        cell3.setCellValue(rmTxBean.getClientName());
                        cell3.setCellStyle(dataStyle);
                        XSSFCell cell4 = row.createCell((short)4);
                        cell4.setCellValue(rmTxBean.getBankName());
                        cell4.setCellStyle(dataStyle);
                        XSSFCell cell5 = row.createCell((short)5);
                        cell5.setCellValue(rmTxBean.getLimitType());
                        cell5.setCellStyle(dataStyle);
                        XSSFCell cell6 = row.createCell((short)6);
                        if(rmTxBean.getProposedLimit().equals("")){
                            cell6.setCellValue(0);
                        }else{
                            cell6.setCellValue(new Double(rmTxBean.getProposedLimit()));
                        }
                        cell6.setCellStyle(numericDataStyle);
                        
                        XSSFCell cell7 = row.createCell((short)7);
                         if(rmTxBean.getActualLimit().equals("")){
                            cell7.setCellValue(0);
                        }else{
                            cell7.setCellValue(new Double(rmTxBean.getActualLimit()));
                        }
                        cell7.setCellStyle(numericDataStyle);
                        
                        XSSFCell cell8 = row.createCell((short)8);
                         if(rmTxBean.getUsance().equals("")){
                            cell8.setCellValue(0);
                        }else{
                            cell8.setCellValue(new Double(rmTxBean.getUsance()));
                        }
                        cell8.setCellStyle(numericDataStyle);
                       
                        XSSFCell cell9 = row.createCell((short)9);
                         if(rmTxBean.getInterestRate().equals("")){
                            cell9.setCellValue(0);
                        }else{
                          cell9.setCellValue(new Double(rmTxBean.getInterestRate()));
                        }
                        cell9.setCellStyle(numericDataStyle);
                        
                        XSSFCell cell10 = row.createCell((short)10);
                         if(rmTxBean.getMargin().equals("")){
                            cell10.setCellValue(0);
                        }else{
                           cell10.setCellValue(new Double(rmTxBean.getMargin()));
                        }
                        cell10.setCellStyle(numericDataStyle);
                        
                        XSSFCell cell11 = row.createCell((short)11);
                         if(rmTxBean.getOurCharge().equals("")){
                            cell11.setCellValue(0);
                        }else{
                            cell11.setCellValue(new Double(rmTxBean.getOurCharge()));
                        }
                        cell11.setCellStyle(numericDataStyle);
                        
                        XSSFCell cell12 = row.createCell((short)12);
                        cell12.setCellValue(rmTxBean.getOurChargetype());
                        cell12.setCellStyle(dataStyle);
                        
                        XSSFCell cell13 = row.createCell((short)13);
                        if(rmTxBean.getHandlingCharges().equals("")){
                            cell13.setCellValue(0);
                        }else{
                           cell13.setCellValue(new Double(rmTxBean.getHandlingCharges()));
                        }
                        cell13.setCellStyle(numericDataStyle);
                        
                        
                        i++;
                         sno++;
                        }
                            
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                            
                        }
                        if(!isRecordExist){
                            
                            i--;
                            i--;
                             XSSFRow row = sheet.createRow((short) i);
                        }
                    }
             
	}
	
	public void writeData() throws FileNotFoundException, IOException {

        FileOutputStream fileOut = new FileOutputStream(inputFile);
        hwb.write(fileOut);
        fileOut.close();
    }
	
	
}
