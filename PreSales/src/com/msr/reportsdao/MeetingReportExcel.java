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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MeetingReportExcel {

	private String inputFile = null;
	XSSFWorkbook hwb = null;
    XSSFSheet sheet = null;
    String fileName = null;
	List<MeetingDetailBean> meetingList = null;
	
	
	public MeetingReportExcel(String input,  List<MeetingDetailBean> meetingList) {
//		hwb = new XSSFWorkbook();
		inputFile = input;
                this.meetingList = meetingList;
		try {
			makingExcelReport();
                        createData(meetingList);
			writeData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	int i = 0;
	public void makingExcelReport() throws FileNotFoundException, IOException {

//		  fileName = "/home/Reports/GeneratedRepots/FlC Daily Report.xlsx";
		  
		  hwb = new XSSFWorkbook();
	        sheet = hwb.createSheet("Meeting update");
	        XSSFFont font1 = hwb.createFont();
	        //apply color to the font
	        font1.setColor(XSSFFont.COLOR_NORMAL);
	        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        
	        font1.setFontHeightInPoints((short) 10);
	        
	        // apply fore ground color to the cell
	        XSSFColor preHeader = new XSSFColor(new java.awt.Color(215, 228, 189));
	        XSSFColor headColor = new XSSFColor(new java.awt.Color(215, 228, 189));
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
	        sheet.setColumnWidth((short) 0, (short) 1000);
	       
	        XSSFCell cell1h = rowhead.createCell((short) 1);
	        sheet.setColumnWidth((short) 1, (short) 2000);
	        cell1h.setCellStyle(mainHeaderStyleLeft);
	        XSSFCell cell2h = rowhead.createCell((short) 2);
	        sheet.setColumnWidth((short) 2, (short) 3000);
	        cell2h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell3h = rowhead.createCell((short) 3);
	        sheet.setColumnWidth((short) 3, (short) 3000);
	        cell3h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell4h = rowhead.createCell((short) 4);
	        sheet.setColumnWidth((short) 4, (short) 3000);
	        cell4h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell5h = rowhead.createCell((short) 5);
	        sheet.setColumnWidth((short) 5, (short) 7000);
	        cell5h.setCellStyle(mainHeaderStylemidle);
	        XSSFCell cell6h = rowhead.createCell((short) 6);
	        cell6h.setCellValue("Meeting Report");
	        cell6h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 6, (short) 7000);
	        XSSFCell cell7h = rowhead.createCell((short) 7);
	        cell7h.setCellStyle(mainHeaderStylemidle);
	        sheet.setColumnWidth((short) 7, (short) 7000);
	        XSSFCell cell8h = rowhead.createCell((short) 8);
	        sheet.setColumnWidth((short) 8, (short) 7000);
	        cell8h.setCellStyle(mainHeaderStyleright);
	        sheet.setColumnWidth((short) 9, (short) 8000);
	        
	        
	        
	        
	        XSSFCellStyle headingTagStyle = hwb.createCellStyle();
	        headingTagStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	        headingTagStyle.setBorderTop(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderBottom(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderRight(CellStyle.BORDER_HAIR);
	        headingTagStyle.setBorderLeft(CellStyle.BORDER_HAIR);
	        headingTagStyle.setFont(font1);
	        headingTagStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        headingTagStyle.setFillForegroundColor(preHeader);
	        
	        i= i+2;
	        XSSFRow headerRow = sheet.createRow((short) i);
//	        S.No.	Date	RM	Company	Contact Person	Mobile	 ILC/FLC	Discussion Points (Min describe in 50 Words)
	        XSSFCell cell0 = headerRow.createCell((short) 0);
	        XSSFCell cell1 = headerRow.createCell((short) 1);
	        cell1.setCellValue("S.No.");
	        cell1.setCellStyle(headingTagStyle);
	        XSSFCell cell2 = headerRow.createCell((short) 2);
	        cell2.setCellValue("Date");
	        cell2.setCellStyle(headingTagStyle);
	        XSSFCell cell3 = headerRow.createCell((short) 3);
	        cell3.setCellValue("Time");
	        cell3.setCellStyle(headingTagStyle);
	        XSSFCell cell4 = headerRow.createCell((short) 4);
	        cell4.setCellValue("RM");
	        cell4.setCellStyle(headingTagStyle);
	        XSSFCell cell5 = headerRow.createCell((short) 5);
	        cell5.setCellValue("Company");
	        cell5.setCellStyle(headingTagStyle);
	        XSSFCell cell6 = headerRow.createCell((short) 6);
	        cell6.setCellValue("Contact Person");
	        cell6.setCellStyle(headingTagStyle);
	        XSSFCell cell7 = headerRow.createCell((short) 7);
	        cell7.setCellValue("Mobile");
	        cell7.setCellStyle(headingTagStyle);
	        XSSFCell cell8 = headerRow.createCell((short) 8);
	        cell8.setCellValue("ILC/FLC");
	        cell8.setCellStyle(headingTagStyle);
	        XSSFCell cell9 = headerRow.createCell((short) 9);
	        cell9.setCellValue("Discussion Points (Min describe in 50 Words)");
	        cell9.setCellStyle(headingTagStyle);
	        XSSFCell cell10 = headerRow.createCell((short) 10);
	        cell10.setCellValue("Case Summary");
	        cell10.setCellStyle(headingTagStyle);
	        i++;
	        
	}
	
	public void createData(List<MeetingDetailBean> meetingList)  throws FileNotFoundException, IOException {
		
            
                XSSFFont font = hwb.createFont();
	        //apply color to the font
	        font.setColor(XSSFFont.COLOR_NORMAL);
	        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	        
	        font.setFontHeightInPoints((short) 10);
                XSSFCellStyle monthLeft = hwb.createCellStyle();
	        monthLeft.setAlignment(XSSFCellStyle.ALIGN_CENTER);
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
                    
                    String[] month = new String[]{"January","February","March","April","May","June", "July", "August","September","October","November","December"};
                    for(String mnth :month){
                        int sno = 1;
                        XSSFRow monthRow = sheet.createRow((short) i);
                        XSSFCell monthCell = monthRow.createCell((short)1);
                        monthCell.setCellValue(mnth);
                        monthCell.setCellStyle(monthLeft);
                        
                        monthCell = monthRow.createCell((short)2);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)3);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)4);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)5);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)6);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)7);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)8);
                        monthCell.setCellStyle(monthMiddle);
                        monthCell = monthRow.createCell((short)9);
                        monthCell.setCellStyle(monthRight);
                        
                        i++;
                      boolean isRecordExist=false;  
                        
                        Iterator itr2 = meetingList.iterator();
                         while(itr2.hasNext()){
                             MeetingDetailBean meetingBean = (MeetingDetailBean)itr2.next();
                             String dateStr = meetingBean.getDate();
                    try {
                        Date dt = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
                        if(mnth.equalsIgnoreCase(new SimpleDateFormat("MMMMM").format(dt))){
                            isRecordExist=true;
                        XSSFRow row = sheet.createRow((short) i);
                        XSSFCell cell0 = row.createCell((short)0);
                        
                        XSSFCell cell1 = row.createCell((short)1);
                        cell1.setCellValue(Integer.toString(sno));
                        cell1.setCellStyle(dataStyle);
                        XSSFCell cell2 = row.createCell((short)2);
                        cell2.setCellValue(meetingBean.getDate());
                        cell2.setCellStyle(dataStyle);
                        XSSFCell cell3 = row.createCell((short)3);
                        cell3.setCellValue(meetingBean.getTime());
                        cell3.setCellStyle(dataStyle);
                        XSSFCell cell4 = row.createCell((short)4);
                        cell4.setCellValue(meetingBean.getRmName());
                        cell4.setCellStyle(dataStyle);
                        XSSFCell cell5 = row.createCell((short)5);
                        cell5.setCellValue(meetingBean.getClientName());
                        cell5.setCellStyle(dataStyle);
                        XSSFCell cell6 = row.createCell((short)6);
                        cell6.setCellValue(meetingBean.getContactPerson());
                        cell6.setCellStyle(dataStyle);
                        XSSFCell cell7 = row.createCell((short)7);
                        cell7.setCellValue(meetingBean.getMobileNumber());
                        cell7.setCellStyle(dataStyle);
                        XSSFCell cell8 = row.createCell((short)8);
                        cell8.setCellValue(meetingBean.getType());
                        cell8.setCellStyle(dataStyle);
                        XSSFCell cell9 = row.createCell((short)9);
                        cell9.setCellValue(meetingBean.getComments());
                        cell9.setCellStyle(dataStyle);
                        XSSFCell cell10 = row.createCell((short)10);
                        cell10.setCellValue(meetingBean.getCaseSummary());
                        cell10.setCellStyle(dataStyle);
                        
                        
                        i++;
                         sno++;
                        }
                            
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                            
                        }
                        if(!isRecordExist){
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
	
	
	public static void main(String[] args){
             List<MeetingDetailBean> meetingList = null;
		new MeetingReportExcel("/root/Report.xlsx", meetingList);
	}
	
}
