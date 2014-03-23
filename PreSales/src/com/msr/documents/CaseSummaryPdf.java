package com.msr.documents;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * 
 * @author piyush chawla
 * @since feb06, 2013
 * @version V1.0
 * this class creates a pdf file of case summary of the client which include his financial history for past 3yrs,
 * banks from which it has taken up the loan, shows loan amount in total......
 * and also tells for which proposal we are proposing it i.e for ILC or FLC.....
 */

public class CaseSummaryPdf {

	private String CompanyName = null;
	private String CompanyAdd = null;
	private String businessDetails = null;
	private String contactPerson = null;
	private String designation = null;
	private String promotorName = null;
	private String discussionDetails = null;
	private String isILC = null;
	private String isFLC = null;
	private String isWC = null;
	private String actionProposed = null;
	private String remark = null;
	private String banksName = null;
	private String ContactId = null;
	
	/**
     *
     */
    public CaseSummaryPdf(){
		this.CompanyName = "Jay Polychem India Limited";
		this.CompanyAdd = "";
		this.businessDetails = "";
		this.contactPerson = "";
		this.designation = "";
		this.promotorName = "";
		this.discussionDetails = "Domestic ILC Business for Applicant base. Limit requirement for Rs 50 Cr. LC's 90 to 180 Days";
		this.isILC = "yes";
		this.isFLC = "yes";
		this.isWC = "yes";
		this.actionProposed = "Discounting of ILC";
		this.remark = "SBBJ - Rs 20 Cr";
		this.banksName = "SBI, Canara Bank, Central Bank, BOB, Allahabad Bank, Federal Bank,Corporation Bnak, HDFC, Deutche Bank";
	}
	
//	this function will generate Pdf file with the data---------
	/**
     *
     * @param file
     * @throws DocumentException
     * @throws IOException
     */
    public void generatePdf(String file) throws DocumentException, IOException{
		Document document = new Document(PageSize.A4, 50,50,100,40);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(getCompanyName());
        document.add(getClientDetailsTable());
        document.add(getInCroreTag());
        document.add(getFinancialDetails());
        System.out.println("printed financial details");
        document.add(getCreditFacilityTag());
        document.add(getBankHeader());
        document.add(getROITag());
        document.add(getDiscussionTag());
        document.add(getInland());
        document.add(getEmptyTag());
        document.add(getActionProposed());
        document.add(getEmptyTag());
        document.add(getRemark());
//        document.add();
        document.close();
	
	
	
	}
	
	private PdfPTable getCompanyName() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase(CompanyName, new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		return table;
	}
	
	private PdfPTable getClientDetailsTable() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{30,70});
		table.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Date / Corp. Off. Add.", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(CompanyAdd, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Contact Person with Designation", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(contactPerson+" - "+designation, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Management / Promoter", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(promotorName, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Business", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(businessDetails, new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		return table;
		}
	
	private PdfPTable getInCroreTag() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("In Crore", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		return table;
	}

	private PdfPTable getFinancialDetails() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{30,20,20,30});
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Financial", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2009-10", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2010-11", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2011-12", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Turnover\nPAT\nRaw Material Consumption\n     Domestic\n     Imports", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2009-10", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2010-11", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FY 2011-12", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		return table;
	}
	
	private PdfPTable getCreditFacilityTag() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("  "));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Credit Facilities", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		
		return table;
	}
	
	private PdfPTable getBankHeader() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[]{30,45,25 });
		table.setWidthPercentage(100);
		PdfPTable subTable = new PdfPTable(2);
		subTable.setWidths(new int[]{30,15 });
		subTable.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Fund Based", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		subTable.addCell(cell);
		cell = new PdfPCell(new Phrase("Non Fund Based", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		subTable.addCell(cell);
		PdfPTable subTable1 = new PdfPTable(2);
		subTable1.setWidths(new int[]{20,10});
		subTable1.setWidthPercentage(100);
		cell = new PdfPCell(new Phrase("Term Loan", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(8);
		subTable1.addCell(cell);
		cell = new PdfPCell(new Phrase("CC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(4);
		subTable1.addCell(cell);
		PdfPTable subTable2 = new PdfPTable(2);
		subTable2.setWidths(new int[]{10,5 });
		subTable2.setWidthPercentage(100);
		cell = new PdfPCell(new Phrase("LC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(8);
		subTable2.addCell(cell);
		cell = new PdfPCell(new Phrase("BG", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(4);
		subTable2.addCell(cell);
		subTable.addCell(subTable1);
		subTable.addCell(subTable2);
		cell = new PdfPCell(new Phrase("Banks", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		table.addCell(subTable);
		cell = new PdfPCell(new Phrase("Total-WC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		return table;
	}
	
	
	private PdfPTable getROITag() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(6);
		table.setWidths(new int[]{30,20,10,10,5,25 });
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase(banksName, new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(" \n\n230Cr\n\n ", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(" \n\n920Cr\n\n ", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		
		
		cell = new PdfPCell(new Phrase("ROI", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		return table;
	}
	
	private PdfPTable getDiscussionTag() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Discussion Points", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(discussionDetails+"\n ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("  "));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Potential Business Assessment/ Requirement", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		return table;
	}
	
	
	private PdfPTable getEmptyTag() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase(" "));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		
		
		return table;
	}
	
	
	private PdfPTable getInland() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{30,70});
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Inland LC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(isILC, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("FLC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(isFLC, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("WC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(isWC, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		return table;
	}
	
	
	private PdfPTable getActionProposed() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{30,70});
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Action you propose", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(actionProposed, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		return table;
	}
	
	
	private PdfPTable getRemark() throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{30,70});
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(new Phrase("Remarks", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 224, 236));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(remark, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 12)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		return table;
	}
	
	
	private void getData(String clientId){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://10.0.0.40:3306/pre_sales";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,"root","msr123");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM pre_sales.pre_client_master where client_id = "+clientId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CompanyName = rs.getString("client_name");
				CompanyAdd = rs.getString("address");
				promotorName = rs.getString("promoters");
				rs.getString("");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	/**
     *
     * @param args
     */
    public static void main(String[] args){
		try {
			try {
				new CaseSummaryPdf().generatePdf("/root/Case_Summary_Test.pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
