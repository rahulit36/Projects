package com.msr.documents;

import com.itextpdf.text.Anchor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.msr.newTx.dao.SLADataBean;
import java.awt.Color;

/**
 *
 * @author piyush chawla
 * @since feb05, 2013
 * @version V1.0 this class creates a pdf file of SLA in which various
 * information like laon amount LC issueing Bank, Company Name, Interest Rate,
 * our own charges etc details are given
 */
public class ServiceAgreementPdf extends PdfPageEventHelper {

    Date date = new Date();
    String dateStr = new SimpleDateFormat("dd-MMM- yyyy").format(date);
    private String scheduleBank = "";
    private String companyName = "";
    private String usance = "";
    private String amntPerMnth = "";
    private String intRate = "";
    private String margin = "";
    private String ourCharges = "";
    private String hndlingChrge = "";
    ServiceAgreementPdf agreementPdf = null;
    boolean isILC = false;
    boolean isFLC = false;

    /**
     *
     * @param sLADataBean
     * @param path
     */
    public ServiceAgreementPdf(SLADataBean sLADataBeanILC, SLADataBean sLADataBeanFLC, String path) {
//    public ServiceAgreementPdf() {
        agreementPdf = this;
        if (sLADataBeanILC != null) {
            this.scheduleBank = "Any Schedule Bank";
            this.companyName = sLADataBeanILC.getCompanyName();
            this.amntPerMnth = sLADataBeanILC.getExpBusiness();
//            this.profileInfo = sLADataBeanILC.getProfileInfo();
//            this.solutionInfo = sLADataBeanILC.getSolutionInfo();
//            this.frgnIntRate = sLADataBeanILC.getFrgnIntRate();
            this.usance = sLADataBeanILC.getUsance() +  " days";
            if (sLADataBeanILC.getOurChrgeType().equals("%Annual Rate")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" % Annual";
            } else if (sLADataBeanILC.getOurChrgeType().equals("%Rate Amount")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" % Amount";
            } else if (sLADataBeanILC.getOurChrgeType().equals("Flat Amount")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" flat";
            }
            this.intRate = "Best Rate as per the market, presently around " + sLADataBeanILC.getIntRate() + " pa.";
            this.hndlingChrge = sLADataBeanILC.getHndlingChrge() + " per bill of exchange. (+S.Tax)";
            this.margin = sLADataBeanILC.getMargin() + " days over the usance of the L.C.\\n\\n(Shall be refunded after adjustment of any delay in payment on the maturity)";
            isILC = true;


//        this.scheduleBank = "Any Schedule Bank";
//        this.companyName = "MSR Technologies Pvt. Ltd.";
//        this.amntPerMnth = "12000000";
//        this.usance = "34 days";
//
//        this.ourCharges = "30000 ";
//        this.intRate = "Best Rate as per the market, presently around 10.7 pa.";
//        this.hndlingChrge = "2500 per bill of exchange. (+S.Tax)";
//        this.margin = "36 days over the usance of the L.C.\n\n(Shall be refunded after adjustment of any delay in payment on the maturity)";
//        isILC = true;
        }


        // call generate pdf method with file path and tx id.
        try {
            generatePdf("e:\\root\\SLA.pdf", Integer.parseInt("1"));
//            generatePdf("/root/SLA.pdf", Integer.parseInt("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseFont getcalibri() throws DocumentException, IOException {
        BaseFont f_cn = null;
        try {
            f_cn = BaseFont.createFont("c:\\windows\\fonts\\calibri.ttf",
                    BaseFont.CP1252, BaseFont.EMBEDDED);
        } catch (Exception e) {
        }
        return f_cn;
    }

    /**
     *
     * @param file
     * @param txId
     * @throws DocumentException
     * @throws IOException
     */
    public void generatePdf(String file, int txId) throws DocumentException, IOException {
        //  getReqdInfo(txId);


        Document document = new Document(PageSize.A4, 50, 50, 70, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setPageEvent(agreementPdf);
        document.open();
        document.add(getFirstPage());
        document.newPage();
        document.add(getCompanyNameTable());
        document.add(getComprehensionData());
        System.out.println("isILCisILCisILCisILCisILCisILC " + isILC);
        if (isILC) {
            document.add(getTransactionHeader());
            document.add(getTransactionTable());
        }

//        document.newPage();
//        if (isFLC) {
//            document.add(getForeignLetterHeader());
//            document.add(getForeignTransactionTable());
//        }

        document.add(getimpNote());
        document.add(getProfHeader());
        document.add(getPreDiscountingStage());
        document.add(getProfChargesTable());
        document.add(getProfChrgsNote());
        document.newPage();
        document.add(getTradeHeader());
        document.add(getTradeFinData());
        document.add(getScopeHeader());
        document.add(getPrestageData());
        document.add(getNegotiationDiscountingStageData());
        document.add(getPoststageData());
        document.newPage();
        document.add(getObligationsOfClientHeader());
        document.add(getObligationsOfClient());
        document.add(getOtherTermsCondHeader());
        document.add(getOtherTermsCondData());
        document.newPage();
        document.add(getConfidentialityData());
        document.add(getLookingFrwrdLine());
        document.add(getsignature());
        document.close();
    }

//	---------------- for footer and header....------------------
    public void onEndPage(PdfWriter writer, Document document) {


        if (document.getPageNumber() != 1) {
            try {

                Rectangle page = document.getPageSize();

                //we will print the header using the code below

                PdfPTable headTable = getHeader();
                headTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());


                //the table is printed at the spcific location

                headTable.writeSelectedRows(0, -1, document.leftMargin(), 830, writer.getDirectContent());
                //we will print the footer using the code below
                PdfPTable footTable = getFooter();

                footTable.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());

                footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() + 84, writer.getDirectContent());


            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }

        // TODO Auto-generated method stub

    }

    private PdfPTable getHeader() throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);


        Image image = Image.getInstance(getClass().getResource("/images/SLA_header.png"));
        image.scalePercent(42);
        image.setAlignment(Element.ALIGN_CENTER);
        
        PdfPCell cell = new PdfPCell(new Phrase("", new Font(getcalibri(), 12, Font.BOLD)));
        cell.setBorder(0);
        cell.setPadding(0);
        cell.addElement(image);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        return table;

    }

    private PdfPTable getFooter() throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{50, 50});
        table.setWidthPercentage(100);

        Chunk chunk3 = new Chunk("INVESTEURS CONSULTING PVT LTD", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph3 = new Phrase(chunk3);

        Chunk chunk4 = new Chunk(" (Building Businesses)", new Font(getcalibri(), 8, Font.NORMAL));
        Phrase ph4 = new Phrase(chunk4);

        Paragraph paragraph = new Paragraph(15);
        paragraph.add(ph3);
        paragraph.add(ph4);


        PdfPCell cell = new PdfPCell(paragraph);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(1);
        cell.setPaddingTop(15);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(25, "S-26-27-28, III Floor, Veera Tower, Green Park Extn, New Delhi-110016", new Font(getcalibri(), 9, Font.NORMAL)));
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Phone: +91 11 2652 2441/42, 2696 9767   Fax: 2651 2226", new Font(getcalibri(), 9, Font.NORMAL)));
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(25, "Phone: +91 11 2652 2441/42, 2696 9767   Fax: 2651 2226", new Font(getcalibri(), 11, Font.NORMAL)));
//        // cell.setRowspan(1);
//        cell.setColspan(2);
//        cell.setBorder(0);
//        cell.setPadding(0);
//        cell.setSpaceCharRatio(0);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);
        Anchor anchor = new Anchor("www.investeurs.com", new Font(getcalibri(), 9, Font.UNDERLINE, new BaseColor(Color.BLUE)));
        anchor.setReference(
                "www.investeurs.com");
        Paragraph p = new Paragraph(25, "");
        p.add(anchor);
        cell = new PdfPCell(p);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);



        return table;

    }

    private PdfPTable getFirstPage() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        Rectangle layoutTop = new Rectangle(PageSize.A4);
        layoutTop.setBorderWidthTop(5);
        layoutTop.setBorderWidthLeft(4);
        layoutTop.setBorderWidthRight(4);

        Rectangle layoutMid = new Rectangle(PageSize.A4);
        layoutMid.setBorderWidthLeft(4);
        layoutMid.setBorderWidthRight(4);

        Rectangle layoutBottom = new Rectangle(PageSize.A4);
        layoutBottom.setBorderWidthBottom(2);
        layoutBottom.setBorderWidthLeft(2);
        layoutBottom.setBorderWidthRight(2);


        Image image = Image.getInstance(getClass().getResource("/images/ic.png"));
        image.scalePercent(70);
        image.setAlignment(Element.ALIGN_CENTER);
        BaseColor cellColor = new BaseColor(0, 165, 255);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setPadding(45);
        cell.setBorder(layoutTop.getBorder());
        cell.setBorderWidth(1);
        cell.addElement(image);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  \n "));
//        cell.setPadding(10);
//        cell.setBorderWidth(1);
//        cell.setBorder(layoutMid.getBorder());
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setBorderColor(cellColor);
//        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Trade Finance Solution Mandate\n ", new Font(getcalibri(), 20, Font.BOLD)));
        cell.setPadding(15);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setBorderColor(cellColor);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("For", new Font(getcalibri(), 22, Font.BOLD)));
        cell.setPadding(6);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        
          
        Chunk chunk4 = new Chunk(companyName.toUpperCase() , new Font(getcalibri(), 20, Font.BOLD));
        chunk4.setTextRise(-10);
        Phrase ph4 = new Phrase(chunk4);

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(ph4);

//        PdfPCell firstCell = new PdfPCell(paragraph1);
        
        cell = new PdfPCell(paragraph1);
        cell.setPadding(2);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(" & ARRANGING OF BUYER’S CREDIT", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 16)));
//        cell.setPadding(15);
//        cell.setBorder(layoutMid.getBorder());
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 15)));
        cell.setPadding(15);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 15)));
        cell.setPadding(20);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 15)));
        cell.setPadding(20);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  ", new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 15)));
        cell.setPadding(20);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Submitted by:", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setPadding(10);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Investeurs Consulting Pvt. Ltd.", new Font(getcalibri(), 20, Font.BOLD)));
        cell.setPadding(5);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(dateStr, new Font(getcalibri(), 12, Font.BOLD)));
        cell.setPadding(1);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" ", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 15)));
        cell.setPadding(20);
        cell.setBorderWidth(1);
        cell.setBorder(layoutMid.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" \n ", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 15)));
        cell.setPadding(20);
        cell.setBorderWidth(1);
        cell.setBorder(layoutBottom.getBorder());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(cellColor);
        table.addCell(cell);



        return table;

    }

//		content of second page------------------
    private PdfPTable getCompanyNameTable() throws DocumentException, IOException {

        Rectangle layoutTop = new Rectangle(PageSize.A4);
        layoutTop.setBorderWidthTop(5);
        layoutTop.setBorderWidthRight(4);

        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell(new Phrase(" ", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(companyName.toUpperCase() + "  ", new Font(getcalibri(), 16, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(layoutTop.getBorder());
        cell.setBorderWidthTop(2f);
        cell.setBorderWidthRight(2f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorRight(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Business Solution Outline", new Font(getcalibri(), 15, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(12);
        cell.setPaddingLeft(5);
        table.addCell(cell);


        return table;
    }

    private PdfPTable getComprehensionData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{15, 85});
        table.setWidthPercentage(100);



        Chunk chunk3 = new Chunk("Investeurs Consulting Private Limited (ICPL) ", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph3 = new Phrase(chunk3);

        Chunk chunk4 = new Chunk("offers to provide Advisory, Consulting  & Solution on Trade finance to Client. \n"
                + "We are pleased to offer an optimal solution best suited for nature of your business and its corresponding requirements.\n"
                + "Outline of the proposed solution is enclosed below for your reference: ", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase ph4 = new Phrase(chunk4);

        Paragraph paragraph = new Paragraph(25);
        paragraph.add(ph3);
        paragraph.add(ph4);


        PdfPCell cell = new PdfPCell(paragraph);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(15);
        cell.setPaddingLeft(5);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(5, 1);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getTransactionHeader() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        Chunk underLineNegotiate = new Chunk("Solution/ Product:  Advisory and Discounting - Inland Letter of Credit ", new Font(getcalibri(), 11, Font.BOLD));
        underLineNegotiate.setUnderline(BaseColor.BLACK, 0.2f, 0.1f, -0.5f, -0.3f, 1);
        cell = new PdfPCell(new Paragraph(underLineNegotiate));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        cell.setPaddingLeft(5);
        table.addCell(cell);
        underLineNegotiate = new Chunk("Deal Specifics", new Font(getcalibri(), 11, Font.BOLD));
//        underLineNegotiate.setUnderline(BaseColor.BLACK, 0.2f, 0.1f, -0.5f, -0.3f, 1);
        cell = new PdfPCell(new Paragraph(underLineNegotiate));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        cell.setPaddingTop(20f);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getTransactionTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{30, 70});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Particulars", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Terms", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("L.C. Issuing Bank", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(scheduleBank, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("Amount per Month", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        cell.setPadding(5f);
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase(amntPerMnth, new Font(BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false), 11)));
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        cell.setPadding(5f);
//        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Usance", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(usance, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Interest rate*", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(intRate, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Handling Charges", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(hndlingChrge, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Margin", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(margin, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
//		cell = new PdfPCell(new Phrase(" "));
//		table.addCell(cell);



        return table;
    }

    private PdfPTable getimpNote() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Paragraph("* Interest rates are contingent upon market conditions and shall be applicable on booking. It is to the account "
                + "of the applicant", new Font(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, "cp1252", false), 9)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getProfHeader() throws DocumentException, IOException {


        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);


        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{40, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Professional Charges", new Font(getcalibri(), 15, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(12);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getPreDiscountingStage() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("", new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false), 11)));
        cell.setBorder(0);
        table.addCell(cell);
//		cell = new PdfPCell(new Phrase(" "));
//		cell.setBorder(0);
//		table.addCell(cell);
        return table;
    }

    private PdfPTable getProfChargesTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{30, 70});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Limit Set up Fee", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("NIL", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Transaction Charges", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("@ " + ourCharges + " % PA/Flat of transaction value subject to a minimum of INR " + amntPerMnth + " per Bills of Exchange with Service Tax", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Billing Cycle", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Transaction Basis/ Weekly Basis", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Payment terms", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("7 Days from the date of Invoice", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);



        return table;
    }

    private PdfPTable getProfChrgsNote() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Paragraph("*Our Charges would be revised subject to market conditions and mutual consent.", new Font(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, "cp1252", false), 9)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getTradeHeader() throws DocumentException, IOException {


        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);


        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{40, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Trade Finance Solution", new Font(getcalibri(), 16, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getTradeFinData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("Our trade finance solutions will facilitate in controlling your raw material cost."
                + " We understand that you may have different credit policies for raw material procurement "
                + "i.e. through CC, BG or LC. Based on our experience and current market scenario, "
                + "it has been factually observed that the cost of raw material purchases through Negotiation / "
                + "Discounting of LC is lower than the CC cost.", new Font(getcalibri(), 11, Font.NORMAL)));
        // cell.setRowspan(1);
//        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(11);
        cell.setPaddingLeft(5);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getScopeHeader() throws DocumentException, IOException {


        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);


        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{40, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Scope of Services ", new Font(getcalibri(), 15, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getPrestageData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{15, 85});
        table.setWidthPercentage(100);



        Chunk chunk3 = new Chunk("Pre Negotiation / Discounting Stage:", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph3 = new Phrase(chunk3);


        Paragraph paragraph = new Paragraph(25);
        paragraph.add(ph3);


        PdfPCell cell = new PdfPCell(paragraph);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(15);
        cell.setPaddingLeft(5);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(5, 1);
        table.addCell(cell);

        Phrase listElement1 = new Phrase(20, "Creation of structured solution as per the business trade cycle and requirements", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement2 = new Phrase(20, "Identification of the Negotiating Bank based on the overall competitive pricing and services", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement3 = new Phrase(20, "Setting up of LCBD (LC Bill Discounting) Facility with the nominated Discounting Bank, as per the Bank’s guidelines", new Font(getcalibri(), 11, Font.NORMAL));

        List unorderedList = new List(List.UNORDERED);
        unorderedList.setListSymbol("\u2022  ");

        unorderedList.add(new ListItem(listElement1));
        unorderedList.add(new ListItem(listElement2));
        unorderedList.add(new ListItem(listElement3));
//

        cell = new PdfPCell();
        cell.addElement(unorderedList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(2);
        cell.setPaddingLeft(25);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getNegotiationDiscountingStageData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{15, 85});
        table.setWidthPercentage(100);



        Chunk chunk3 = new Chunk("Negotiation / Discounting Stage:", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph3 = new Phrase(chunk3);


        Paragraph paragraph = new Paragraph(25);
        paragraph.add(ph3);


        PdfPCell cell = new PdfPCell(paragraph);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(15);
        cell.setPaddingLeft(5);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(5, 1);
        table.addCell(cell);

        Phrase listElement1 = new Phrase(20, "Collection of documents & Vetting of the same as per the LC terms", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement2 = new Phrase(20, "Forwarding of Documents from Negotiating Banks to LC Issuing Bank", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement3 = new Phrase(20, "Following up for Acceptance / Confirmation Letter", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement4 = new Phrase(20, "Timely discounting of documents and remittance of funds via RTGS/Pay Order", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement5 = new Phrase(20, "Timely information – RTGS details, Advises provided through our in-house build up Software “Bills Master”", new Font(getcalibri(), 11, Font.NORMAL));

        List unorderedList = new List(List.UNORDERED);
        unorderedList.setListSymbol("\u2022  ");

        unorderedList.add(new ListItem(listElement1));
        unorderedList.add(new ListItem(listElement2));
        unorderedList.add(new ListItem(listElement3));
        unorderedList.add(new ListItem(listElement4));
        unorderedList.add(new ListItem(listElement5));
//

        cell = new PdfPCell();
        cell.addElement(unorderedList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(2);
        cell.setPaddingLeft(25);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;

    }

//	content of fouth page--------
    private PdfPTable getPoststageData() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{15, 85});
        table.setWidthPercentage(100);



        Chunk chunk3 = new Chunk("Post Negotiation / Discounting Stage:", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph3 = new Phrase(chunk3);


        Paragraph paragraph = new Paragraph(20);
        paragraph.add(ph3);


        PdfPCell cell = new PdfPCell(paragraph);
        // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(15);
        cell.setPaddingLeft(5);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(5, 1);
        table.addCell(cell);

        Phrase listElement1 = new Phrase(20, "Arranging credit advice", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement2 = new Phrase(20, "Tracking Due Date Payments", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement3 = new Phrase(20, "Arranging Margin Money Refund from the Negotiating Bank", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement4 = new Phrase(20, "Providing Monthly MIS Report", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement5 = new Phrase(20, "Updating short term market conditions at regular intervals", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement6 = new Phrase(20, "Maintaining Discounting Log through our  Software “Bills Master”", new Font(getcalibri(), 11, Font.NORMAL));

        List unorderedList = new List(List.UNORDERED);
        unorderedList.setListSymbol("\u2022  ");

        unorderedList.add(new ListItem(listElement1));
        unorderedList.add(new ListItem(listElement2));
        unorderedList.add(new ListItem(listElement3));
        unorderedList.add(new ListItem(listElement4));
        unorderedList.add(new ListItem(listElement5));
        unorderedList.add(new ListItem(listElement6));
//

        cell = new PdfPCell();
        cell.addElement(unorderedList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(2);
        cell.setPaddingLeft(25);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;

    }

    private PdfPTable getObligationsOfClientHeader() throws DocumentException, IOException {


        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);


        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{40, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Obligations of Client – M/s "+companyName.toUpperCase(), new Font(getcalibri(), 15, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }

    private PdfPTable getObligationsOfClient() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();

        Phrase listElement1 = new Phrase(20, "The Client will submit signed copy of the service agreement within 7 working days from the date of receipt. "
                + "If the signed copy is not returned and the job continues without any disruption from either the Client or ICPL side, "
                + "the offer letter will be considered accepted", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement2 = new Phrase(20, "The Client to submit all relevant & true information to be submitted to the Bank, as per the KYC norms - "
                + "corporate information, supporting documents required for facilitating the LCBD faciltiy ", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement3 = new Phrase(20, "The Client will submit all documents and information required for processing the "
                + "proposal within a reasonable time from the date of intimation", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase listElement4 = new Phrase(20, "The Client will designate one point of contact for ICPL to "
                + "coordinate for required documents/information", new Font(getcalibri(), 11, Font.NORMAL));

        List unorderedList = new List(List.UNORDERED);
        unorderedList.setListSymbol("\u2022  ");

        unorderedList.add(new ListItem(listElement1));
        unorderedList.add(new ListItem(listElement2));
        unorderedList.add(new ListItem(listElement3));
        unorderedList.add(new ListItem(listElement4));

        cell = new PdfPCell();
        cell.addElement(unorderedList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(2);
        cell.setPaddingLeft(25);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getOtherTermsCondHeader() throws DocumentException, IOException {
      

        Rectangle layoutTop2 = new Rectangle(PageSize.A4);
        layoutTop2.setBorderWidthTop(5);
        layoutTop2.setBorderWidthLeft(4);

        BaseColor cellColor = new BaseColor(51, 74, 147);


        PdfPTable table = new PdfPTable(1);
//        table.setWidths(new int[]{40, 60});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Other Terms & Conditions ", new Font(getcalibri(), 15, Font.BOLD, cellColor)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(layoutTop2.getBorder());
        cell.setBorderWidthTop(0.7f);
        cell.setBorderWidthLeft(0.7f);
        cell.setBorderColorTop(cellColor);
        cell.setBorderColorLeft(cellColor);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        table.addCell(cell);

        return table;
    }


    private PdfPTable getOtherTermsCondData() throws DocumentException, IOException {
     PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();

        Phrase listElement1 = new Phrase(20, "The said Agreement is valid for 1 year. It would be continuously extended till the time "
                + "it’s mutually agreed to discontinue services in writing.\n", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element1 = new Phrase(20, "Information, ideas, suggestions given to the client shall remain privileged information to "
                + "be used only by the consultant and will not be disclosed to other parties", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element2 = new Phrase(20, "The client will provide exclusivity to process the transactions, represent to the Financial "
                + "Institution/Banks, nominated by Investeurs for LCBD ", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element3 = new Phrase(20, "ICPL would be entitled to mention the client’s name in their web-site or any other"
                + " print media including promotional brochures, which will only to announce the successful execution of transactions", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element4 = new Phrase(20, "The client should be transparent in all its dealing with ICPL", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element5 = new Phrase(20, "The client should provide information and documents as required by ICPL only after"
                + " ensuring the authenticity of the same.", new Font(getcalibri(), 11, Font.NORMAL));

        
        List unorderedList = new List(List.UNORDERED);
        unorderedList.setIndentationLeft(45);
        unorderedList.setListSymbol("o  ");

        unorderedList.add(new ListItem(listElement1));
        
        List unorderedList2 = new List(List.UNORDERED);
        unorderedList2.setIndentationLeft(45);
        unorderedList2.setListSymbol("o  ");

        unorderedList2.add(new ListItem(list2Element1));
        unorderedList2.add(new ListItem(list2Element2));
        unorderedList2.add(new ListItem(list2Element3));
        unorderedList2.add(new ListItem(list2Element4));
        unorderedList2.add(new ListItem(list2Element5));
        
        List validityList = new List(List.UNORDERED);
        validityList.add(new ListItem("  Validity:", new Font(getcalibri(), 12, Font.NORMAL)));
        validityList.add(unorderedList);
        validityList.add(new ListItem("  General Terms & conditions: ", new Font(getcalibri(), 12, Font.NORMAL)));
        validityList.add(unorderedList2);
        validityList.setIndentationLeft(25);
        
        cell = new PdfPCell();
        cell.addElement(validityList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(0);
        cell.setPaddingLeft(0);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getConfidentialityData() throws DocumentException, IOException {
     PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();

        Phrase list2Element1 = new Phrase(20, "ICPL undertakes to keep confidential any information which has been provided to ICPL by or on behalf of ICPL "
                + "and which is not in public knowledge and shall not without the Client’s prior consent copy, distribute or disclose any confidential information to any person"
                + " other than ICPL employees and other persons who will be subject to a similar confidentiality obligation and Investeurs "
                + "or their professional advisors", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element2 = new Phrase(20, "Either  Party, ICPL or the client shall be excused from the performance of any of his obligations  under this Agreement"
                + " if the performance thereof is prevented  or delayed  by  the  acts of God, riots, wars, accidents, embargo  or "
                + " requisition (acts of government)", new Font(getcalibri(), 11, Font.NORMAL));

        Phrase list2Element3 = new Phrase(20, "The Client will make the full payment to ICPL irrespective of acceptance of sanction letter by Client if terms "
                + "& conditions of sanction letter are in sync with in principal term sheet.", new Font(getcalibri(), 11, Font.NORMAL));

        
        List unorderedList2 = new List(List.UNORDERED);
        unorderedList2.setIndentationLeft(45);
        unorderedList2.setListSymbol("o  ");

        unorderedList2.add(new ListItem(list2Element1));
        unorderedList2.add(new ListItem(list2Element2));
        unorderedList2.add(new ListItem(list2Element3));
        
        List validityList = new List(List.UNORDERED);
        validityList.add("  Confidentiality: ");
        validityList.add(unorderedList2);
        validityList.setIndentationLeft(25);
        
        cell = new PdfPCell();
        cell.addElement(validityList);
        cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPaddingTop(0);
        cell.setPaddingLeft(0);
        cell.setSpaceCharRatio(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setLeading(10, 1);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getLookingFrwrdLine() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
         cell = new PdfPCell(new Phrase("Looking forward to a synergetic relationship.", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        return table;
    }

    private PdfPTable getsignature() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{65, 35});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("  "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(" "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("   "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("   "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("  "));
        cell.setPadding(0);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("   "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Accepted Terms & Conditions", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("For Investeurs Consulting Pvt. Ltd.", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("For " + companyName, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Sumanta Mahakul", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Contact Name", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Vice President-Trade Finance", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Designation", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingLeft(0);
        cell.setPaddingTop(7);
        cell.setBorder(0);
        table.addCell(cell);

        return table;

    }

    public static void main(String[] args) {

        try {
//            new ServiceAgreementPdf();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
