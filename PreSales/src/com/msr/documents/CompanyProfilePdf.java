package com.msr.documents;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author piyush chawla
 * @since feb06, 2013
 * @version V1.0 this class creates a pdf file of company profile which contains
 * various informations regarding company like its current conditions, History,
 * products and details, its suppliers client tally, management details, its
 * financial performance, its overView...etc..........
 */
public class CompanyProfilePdf extends PdfPageEventHelper {

    private String companyName = null;
    private String borrowerName = "";
    private String constitutionName = "";
    private String dateOfBusiness = "";
    private String headOffAdd = "";
    private String branchOffAdd = "";
    private String principleActivity = "";
    private String faceValue = "";
    private String maxValue = "";
    private String lowValue = "";
    private String currentValue = "";
    private String promoterName = "";
    private String creditRating = "";
    private String balSheet = "";
    private String backgroundPara = "";
    private String productNAppPara = "";
    private String supplierPara = "";
    private String clientPara = "";
    private String manufPara = "";
    private String mgmtPara = "";
    private String indOverView = "";
    private String financePerformance = "";

    /**
     *
     */
    public CompanyProfilePdf() {
        this.companyName = "<company Name>";
        try{
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @throws DocumentException
     * @throws IOException
     */
    public void generatePdf(String companyName, String file) throws DocumentException, IOException {
        this.companyName = companyName;
        Document document = new Document(PageSize.A4, 50, 50, 100, 60);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        writer.setPageEvent(new CompanyProfilePdf());
        document.add(getFirstPage());
        document.newPage();
        document.add(getCompanyGlance());
        document.add(getSNoable());
        document.add(getTableData());
        document.add(getBackground());
        document.add(getBackgroundPara());
        document.add(getProductNApplications());
        document.add(getproductNAppPara());
        document.add(getSuppliers());
        document.add(getSupplierPara());
        document.add(getClients());
        document.add(getClientPara());
        document.add(getManufacturingSetUp());
        document.add(getManuSetUpPara());
        document.add(getManagementDetails());
        document.add(getMgmtDetailPara());
        document.add(getIndustryOverview());
        document.add(getIndOverviewPara());
        document.add(getFinancialPerformance());
        document.add(getFinancialPerformancePara());
        document.add(getBankingArrangement());
        document.add(getBankingPara());
        document.add(getAmountTag());
        document.add(getBankDetailTable());
        document.add(getTotalTable());
//        document.add(getPara());



        document.close();
    }

//	---------------- for footer and header....------------------
    @Override
    public void onEndPage(PdfWriter writer, Document document) {



        try {

            Rectangle page = document.getPageSize();
            int pageCount = writer.getPageNumber();
            //we will print the header using the code below
            if (pageCount != 1) {
                PdfPTable headTable = getHeader();
                headTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());



                //the table is printed at the spcific location

                headTable.writeSelectedRows(0, -1, document.leftMargin(), 830, writer.getDirectContent());
                //we will print the footer using the code below

                PdfPTable footTable = getFooter(pageCount);

                footTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());

                footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() + 20, writer.getDirectContent());
            }
        } catch (Exception ex) {

            ex.printStackTrace();

        }


        // TODO Auto-generated method stub

    }

    private PdfPTable getHeader() throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("\n \n " + companyName, new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setBorder(0);
        cell.setPadding(0);

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        return table;

    }

    private PdfPTable getFooter(int pageCount) throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell(new Phrase(new SimpleDateFormat("MMMMM, yyyy").format(new Date()), new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" Page  " + (pageCount - 1), new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);


        return table;

    }

    private PdfPTable getBackground() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Background	", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getBackgroundPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("(Mention brief about the history, business, products, installed capacity, one line about the marketing arrangement and about the exports, quality standards.) ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getProductNApplications() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Product & Applications", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getproductNAppPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("(Mention about the list of products and its applications) ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getSuppliers() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Suppliers", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getSupplierPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("(Mention top suppliers and raw material) ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getClients() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Clients", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getClientPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph(" (Mention top customers and about the marketing arrangement in short)", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getManufacturingSetUp() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Manufacturing Set-Up", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getManuSetUpPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("   ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getManagementDetails() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Management Details", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getMgmtDetailPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("  ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getFinancialPerformance() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Financial Performance", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private Paragraph getFinancialPerformancePara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("The company is estimating to achieve revenues of Rs………………….Crores in Current Financial Year and has achieved ………………….. Crores till December 2011. The Company is projecting to achieve revenues of Rs………………… Crores in FY 2012-13. ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getBankingArrangement() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Banking Arrangement", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPaddingBottom(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private PdfPTable getIndustryOverview() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Industry Overview", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14, 0, BaseColor.WHITE)));
        cell.setBackgroundColor(new BaseColor(54, 130, 162));
        cell.setBorder(15);
        cell.setPadding(6f);
        cell.setBorderWidth(2.0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    private PdfPTable getCompanyGlance() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        Chunk underLineheader = new Chunk("Company at a Glance", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 12));
        underLineheader.setUnderline(BaseColor.BLACK, 0.2f, 0.1f, -0.5f, -0.3f, 1);
        PdfPCell cell = new PdfPCell(new Phrase(underLineheader));
        cell.setBorder(1);
        cell.setBorderWidth(1f);
        table.addCell(cell);


        return table;

    }

    private Paragraph getIndOverviewPara() throws DocumentException, IOException {
        Paragraph para = new Paragraph("<Structure and size of the industry>\n(in pointers) ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11));
        para.setAlignment(Element.ALIGN_JUSTIFIED);


        return para;
    }

    private PdfPTable getSNoable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{7, 93});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("S.No", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Particulars", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getTableData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{7, 33, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("1", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Name of the Borrower", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("2", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Constitution", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("3", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date of Incorporation and Commencement of Business", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("4", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Address:\na) Head Office/Regd Office\nb) Branch Office/ Factory", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("5", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Principle Activity", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("6", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Share Price, if Listed", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);



        PdfPTable subTable = new PdfPTable(4);
        subTable.setWidths(new int[]{15, 15, 15, 15});
        cell = new PdfPCell(new Phrase("face value", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("High", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("Low", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("Current share Price", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);

//		for entering data
        cell = new PdfPCell(new Phrase(" ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("  ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("  ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTable.addCell(cell);

        table.addCell(subTable);



        cell = new PdfPCell(new Phrase("7", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Names of the Promoters", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("8", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("External Credit Rating if any", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("9", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Bal. sheet ending Dec./March/June", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        return table;


    }

//	private Paragraph getPara(){
//		Paragraph para = new Paragraph("aaaa");
//		return para;
//	}
//	
    private Paragraph getBankingPara() {
        Paragraph para = new Paragraph("The Company is availing fund based and non fund based Facilities of Rs. ………… ……………Crores with the ……………….. under sole/multiple/consortium banking arrangement.");
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        return para;
    }

    private PdfPTable getAmountTag() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("(Amount in Cr)", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        return table;
    }

    private PdfPTable getBankDetailTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{7, 30, 20, 24, 19});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("S. No.", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Bank Name", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Fund Based", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Non Fund Based", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("CC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        PdfPTable subTable = new PdfPTable(2);
        subTable.setWidths(new int[]{50, 50});
        subTable.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase("LC", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);
        cell = new PdfPCell(new Phrase("BG", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 10)));
        cell.setBorder(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTable.addCell(cell);
        table.addCell(subTable);
        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


//		data cells starts from here and are in for loop 
        for (int i = 0; i < 5; i++) {
            cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("CC" + i, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            subTable = new PdfPTable(2);
            subTable.setWidths(new int[]{50, 50});
            subTable.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("LC" + i, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            subTable.addCell(cell);
            cell = new PdfPCell(new Phrase("BG" + i, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            subTable.addCell(cell);
            table.addCell(subTable);
            cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
            cell.setBorder(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        return table;
    }

    private PdfPTable getTotalTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{37, 20, 12, 12, 19});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("Total", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 10)));
        cell.setBorder(15);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getFirstPage() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("    \t  " + companyName, new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("              Proposal for LC Bill Discounting", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("                    Facility", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

//		cell = new PdfPCell(new Phrase("CONTENTS", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD,"Cp1252",false),11)));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(0);
//		table.addCell(cell);
//		
//		cell = new PdfPCell(new Phrase("Company at a Glance\nBackground\nProducts & Application\nProject Details\nRaw Material Used\nSupplier\nClients\nManagement Details\nIndustry Overview", new Font(BaseFont.createFont(BaseFont.HELVETICA,"Cp1252",false),11)));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(0);
//		table.addCell(cell);
        Image image = Image.getInstance(getClass().getResource("/images/cmpny_profile1.png"));
        table.addCell(image);

        return table;
    }

    private void getData(String clientId) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://10.0.0.40:3306/pre_sales";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, "root", "msr123");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM pre_sales.pre_client_master where client_id = " + clientId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                companyName = rs.getString("client_name");
                borrowerName = rs.getString("client_name");
                constitutionName = rs.getString("constitution");
                dateOfBusiness = rs.getString("date of Incorporation");
                headOffAdd = rs.getString("headOffice_addr");
                branchOffAdd = rs.getString("address");
                principleActivity = rs.getString("");
                promoterName = rs.getString("promoters");
                creditRating = rs.getString("Credit Rating");
                backgroundPara = rs.getString("history");
                productNAppPara = rs.getString("productsandApps");
                supplierPara = rs.getString("suppliers");
                clientPara = rs.getString("slientele");
                mgmtPara = rs.getString("management_details");
                manufPara = rs.getString("manufacturing_setup");


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
    public static void main(String[] args) {
//        try {
//            try {
////                new CompanyProfilePdf().generatePdf("/root/Company_profile_Test.pdf");
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } catch (DocumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
