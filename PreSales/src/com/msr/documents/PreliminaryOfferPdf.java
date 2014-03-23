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
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.text.DateFormat;

/**
 *
 * @author piyush chawla
 * @since feb05, 2013
 * @version V1.0 this class creates a pdf file of SLA in which various
 * information like laon amount LC issueing Bank, Company Name, Interest Rate,
 * our own charges etc details are given
 */
public class PreliminaryOfferPdf extends PdfPageEventHelper {

    Date date = new Date();
    String dateStr = new SimpleDateFormat("d MMMMM, yyyy.").format(date);
    private String companyName = "";
    private String companyAddrs = "";
    private String companycontact = "";
    private String usance = "";
    private String intRate = "";
    private String margin = "";
    private String ourCharges = "";
    private String hndlingChrge = "";
    PreliminaryOfferPdf agreementPdf = null;
    boolean isILC = false;
    boolean isFLC = false;
    PdfWriter writer = null;
    Document document = null;

    /**
     *
     * @param sLADataBean
     * @param path
     */
    public PreliminaryOfferPdf(SLADataBean sLADataBeanILC, SLADataBean sLADataBeanFLC, String path) {
//    public PreliminaryOfferPdf() {
        agreementPdf = this;
        if (sLADataBeanILC != null) {
            this.companyName = sLADataBeanILC.getCompanyName();
         companyAddrs = sLADataBeanILC.getClientAddrs();
         companycontact = sLADataBeanILC.getContactPerson();
            this.usance = sLADataBeanILC.getUsance() + " days";
            if (sLADataBeanILC.getOurChrgeType().equals("%Annual Rate")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" % Annual";
            } else if (sLADataBeanILC.getOurChrgeType().equals("%Rate Amount")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" % Amount";
            } else if (sLADataBeanILC.getOurChrgeType().equals("Flat Amount")) {
                this.ourCharges = sLADataBeanILC.getOurChrge()+" flat";
            }
            this.intRate = "Best Rate as per the market, presently around @" + sLADataBeanILC.getIntRate() + " pa.";
             this.hndlingChrge = "As per Bank (+ S. Tax)";
            this.margin = sLADataBeanILC.getMargin() + " days over the usance of the L.C.";
            isILC = true;
            

//        this.companyName = "MSr Technologies Pvt. Ltd.";
//        companyAddrs = "S-26,27,28, 3rd floor, Veera Tower, Green Park Ext., New Delhi-110016";
//        companycontact = "Mr. Rahul Chaturvedi";
//        this.usance = "30 days";
//        if ("%Annual Rate".equals("%Annual Rate")) {
//            this.ourCharges = "0.20 % Annual";
//        }
//        this.intRate = "Best Rate as per the market, presently around @" + 1.2 + " pa.";
//        this.hndlingChrge = "As per Bank (+ S. Tax)";
//        this.margin = 15 + " days over the usance of the L.C.";
//        isILC = true;
        }
//        if (sLADataBeanFLC != null) {
//            setFlcValues(sLADataBeanFLC);
//        }
        // call generate pdf method with file path and tx id.
        try {
            generatePdf("/root/offer.pdf", Integer.parseInt("1"));
//            generatePdf(path, Integer.parseInt(SingletonClass.txIdEdit));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        document = new Document(PageSize.A4, 50, 50, 100, 40);
        writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setPageEvent(agreementPdf);
        document.open();
        System.out.println("isILCisILCisILCisILCisILCisILC " + isILC);
        if (isILC) {
            getTransactionHeader();
            document.add(getTransactionTable());
        }

        document.add(getimpNote(document));
        document.newPage();      
        Paragraph paragraph = new Paragraph(25, "The Investeurs Effect", new Font(getcalibri(), 11, Font.BOLD));
        document.add(paragraph);
        paragraph = new Paragraph(25, "Trade Finance being our proficiency domain, we ensure that such transactions are accomplished through"
                + " minimum complexities and maximum value augmentation to the business."  , new Font(getcalibri(), 11, Font.NORMAL));
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        paragraph = new Paragraph(25, "Working with us attributes our client with following advantages, which we collectively refer to as “Investeurs Effect”: ", new Font(getcalibri(), 11, Font.BOLD));
        document.add(paragraph);
         List overview = new List(false, 10);
        overview.add(new ListItem(25, "Headway into multiple Banks" , new Font(getcalibri(), 11, Font.NORMAL)));
        overview.add(new ListItem(25, "Customized Solutions", new Font(getcalibri(), 11, Font.NORMAL)));
        overview.add(new ListItem(25, "Industry and Market updates, including a close watch on interest rate trajectory", new Font(getcalibri(), 11, Font.NORMAL)));
        overview.add(new ListItem(25, "Account Monitoring", new Font(getcalibri(), 11, Font.NORMAL)));
        document.add(overview);

        document.add(Chunk.NEWLINE);
        paragraph = new Paragraph(25, "Modus Operandi for Negotiation of ILC: ", new Font(getcalibri(), 11, Font.BOLD));
        document.add(paragraph);
        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Paragraph(" "));
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        Image image = Image.getInstance(getClass().getResource("/images/offer.png"));
        image.scalePercent(81);
         cell = new PdfPCell(image);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);
        document.newPage();
        paragraph = new Paragraph(25, "Trade finance services are not only about ensuring best pricing, but also providing quality services. "
                + "We understand the importance of cash flows, thus, services & "
                + "ensuring payments within the stipulated time becomes the essence of our collaboration with you. ", new Font(getcalibri(), 11, Font.NORMAL));
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        paragraph = new Paragraph(25, "We are grateful for showing interest and conviction in Investeurs.  "
                + "We assure you of an excellent quality of work clubbed with timely delivery. ", new Font(getcalibri(), 11, Font.NORMAL));
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        paragraph = new Paragraph(15, "Best regards,", new Font(getcalibri(), 11, Font.NORMAL));
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        Chunk chunk3 = new Chunk("Yours truly,\n" +
"for ", new Font(getcalibri(), 11, Font.NORMAL));
        Phrase ph3 = new Phrase(chunk3);

        Chunk chunk4 = new Chunk("Investeurs Consulting Pvt. Ltd.", new Font(getcalibri(), 11, Font.BOLD));
        Phrase ph4 = new Phrase(chunk4);

        paragraph = new Paragraph(15);
        paragraph.add(ph3);
        paragraph.add(ph4);        
        document.add(paragraph);
        
        paragraph = new Paragraph(15, "RM Name\n"+ SingletonClass.empDesignation+"\n"+SingletonClass.mobNo, new Font(getcalibri(), 11, Font.NORMAL));
        document.add(paragraph);
//        document.add(getsignature());
        document.close();
    }

//	---------------- for footer and header....------------------
    public void onEndPage(PdfWriter writer, Document document) {

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

            footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() + 44, writer.getDirectContent());

        } catch (Exception ex) {

            ex.printStackTrace();

        }


        // TODO Auto-generated method stub

    }

    private PdfPTable getHeader() throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);

        Image image = Image.getInstance(getClass().getResource("/images/SLA_header.png"));
        image.scalePercent(42);
        PdfPCell cell = new PdfPCell(image);
        cell.setBorder(0);
        cell.setPadding(0);
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
        cell = new PdfPCell(new Phrase(25, "S-26-27-28, III Floor, Veera Tower, Green Park Extn, New Delhi-110016",  new Font(getcalibri(), 9, Font.NORMAL)));
       // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Phone: +91 11 2652 2441/42, 2696 9767   Fax: 2651 2226",  new Font(getcalibri(), 9, Font.NORMAL)));
       // cell.setRowspan(1);
        cell.setColspan(2);
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setSpaceCharRatio(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        Anchor anchor;
        BaseColor baseColor = new BaseColor(0, 0, 255);
        anchor = new Anchor( "www.investeurs.com",new Font(getcalibri(), 11, Font.UNDERLINE, baseColor));
      anchor.setReference(
          "www.investeurs.com");
      Paragraph p =  new Paragraph(25, "");
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

    public BaseFont getcalibri() throws DocumentException, IOException {
        BaseFont f_cn = null;
        try {
            f_cn = BaseFont.createFont("c:\\windows\\fonts\\calibri.ttf",
                    BaseFont.CP1252, BaseFont.EMBEDDED);
        } catch (Exception e) {
        }
        return f_cn;
    }

    private void getTransactionHeader() throws DocumentException, IOException {


        Paragraph paragraphEmptyLine = new Paragraph(15, " ", new Font(getcalibri(), 12));
        // We add one empty line
        paragraphEmptyLine.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphEmptyLine);

        Paragraph paragraph = new Paragraph(15, "", new Font(getcalibri(), 11,Font.UNDERLINE));
        paragraph.setAlignment(Element.ALIGN_CENTER);
//        document.add(new Chunk(paragraph, BOLD_UNDERLINED));
        
//        paragraph.setAlignment(Element.ALIGN_RIGHT);
        
      Chunk underline = new Chunk("TRADE FINANCE SOLUTION - OFFER LETTER", new Font(getcalibri(), 11,Font.BOLD));
      underline.setUnderline(0.4f, -2f); //0.1 thick, -2 y-location
      
         paragraph = new Paragraph(25, underline);
        paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable table21 = new PdfPTable(2);
        // table1.setWidths(new int[]{24, 24, 2});
        table21.setTotalWidth(527);
        table21.setLockedWidth(true);
        table21.getDefaultCell().setFixedHeight(20);
        table21.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);


        Chunk chunk4 = new Chunk("To,\n\n" + companyName + companyAddrs, new Font(getcalibri(), 11, Font.NORMAL));
        chunk4.setTextRise(-10);
        Phrase ph4 = new Phrase(chunk4);

        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(ph4);

        PdfPCell firstCell = new PdfPCell(paragraph1);
        firstCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        firstCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        firstCell.setBorder(Rectangle.NO_BORDER);
        firstCell.setPadding(15);
        table21.addCell(firstCell);

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        PdfPCell secondCell = new PdfPCell(new Phrase(25, "\n"+df.format(new Date()), new Font(getcalibri(), 11, Font.NORMAL)));
        secondCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        secondCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        secondCell.setBorder(Rectangle.NO_BORDER);
        firstCell.setPadding(15);
        table21.addCell(secondCell);


        PdfPCell firstCell2 = new PdfPCell(new Phrase(25, "", new Font(getcalibri(), 9)));
        firstCell2.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        firstCell2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        firstCell2.setBorder(Rectangle.NO_BORDER);
        table21.addCell(firstCell2);

        PdfPCell secondCell2 = new PdfPCell(new Phrase(25, "", new Font(getcalibri(), 9)));
        secondCell2.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        secondCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        secondCell2.setBorder(Rectangle.NO_BORDER);
        table21.addCell(secondCell2);
        table21.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());


        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        paragraph = new Paragraph(25, "Kind Attention          : " + companycontact, new Font(getcalibri(), 11, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        paragraph = new Paragraph(25, " ");
        document.add(paragraph);
        paragraph = new Paragraph(25, "This is further to our discussion in regard to the negotiation of documents duly drawn under Inland Letter of Credit."
                + "\nA tailor made solution to best suit your business requirements is enclosed below:", new Font(getcalibri(), 11, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);


    }

    private PdfPTable getTransactionTable() throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{30, 70});
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(25, " "));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, ""));
        cell.setBorder(0);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Deal Specifics", new Font(getcalibri(), 11, Font.BOLD)));
//        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Nature of Transaction", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Domestic Bill Discounting under LC", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "L. C. Issuing Bank", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Any Bank", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Usance", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, usance, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Interest rate*", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, intRate, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Advising Charges", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "As per Bank", new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Handling Charges", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, hndlingChrge, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Margin**", new Font(getcalibri(), 11, Font.BOLD)));
        
        
        
        
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, margin, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, "Fund Raising Fees***", new Font(getcalibri(), 11, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(25, ourCharges, new Font(getcalibri(), 11, Font.NORMAL)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(5f);
        table.addCell(cell);
        return table;
    }

    private Paragraph getimpNote(Document document) throws DocumentException, IOException {

Paragraph p = new Paragraph(20, "Kindly read following terms in conjunction with the above mentioned deal specifics:", new Font(getcalibri(), 11, Font.NORMAL));
document.add(p);
Paragraph p1 = new Paragraph(20, "*Interest Cost is as per today’s market conditions, they may vary on booking date compared to that quoted today.\n"        
        + "**Margin shall be refunded after adjustment for any delay in payment on the maturity.\n"
        + "***Fund Raising fees is payable as and when the funds are remitted in favor of the Beneficiary. "
        + "\nFurther our charges would be revised subject to market conditions and mutual consent. ", new Font(getcalibri(), 10, Font.NORMAL));
return p1;
    }


    public static void main(String[] args) {
        try {
//            new PreliminaryOfferPdf1();
//            new ServiceAgreementPdf();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
