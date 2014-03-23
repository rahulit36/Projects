/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.dao;


import com.itextpdf.text.Anchor;
import java.io.FileOutputStream;
import java.io.IOException;

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
import com.msr.util.SingletonClass;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author piyush chawla
 * @since feb05, 2013
 * @version V1.0 this class creates a pdf file of SLA in which various
 * information like laon amount LC issueing Bank, Company Name, Interest Rate,
 * our own charges etc details are given
 */
public class DocListPDF extends PdfPageEventHelper {

    com.msr.newTx.dao.DocListPDF agreementPdf = this;
    PdfWriter writer = null;
    Document document = null;

    /**
     *
     * @param sLADataBean
     * @param path
     */
//    public DocListPDF(SLADataBean sLADataBeanILC, SLADataBean sLADataBeanFLC, String path) {
    public DocListPDF() {

        try {
            ArrayList<String> docList = new ArrayList<String>();
            docList.add("KYC");
            docList.add("Passport Size Photograph");
            docList.add("Address Proof");
            docList.add("Curosity Mars rover Mission Document");
            docList.add("Indian Mars Mission Document");
            docList.add("Maven Mars Mission Document");
            docList.add("Antripology and Trisonlogy Document");
            docList.add("Homosapiens sapiens Document");
            docList.add("Anna Hazare and Arvind Keju Document");
            docList.add("Investeurs Business Process Document");
            docList.add("MSR Tech Snap2door Document");
            generatePdf(SingletonClass.sysFilePath+"DocumentList.pdf", docList);
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
    public void generatePdf(String file, ArrayList<String> docList) throws DocumentException, IOException {
        //  getReqdInfo(txId);
        document = new Document(PageSize.A4, 50, 50, 100, 650);
        writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setPageEvent(agreementPdf);
        document.open();
            getTransactionHeader(docList);

        document.close();
    }

//	---------------- for footer and header....------------------
    @Override
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

            footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin()-560 , writer.getDirectContent());

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

    private void getTransactionHeader(ArrayList<String> docList) throws DocumentException, IOException {



//        Paragraph paragraph = new Paragraph(15, "", new Font(getcalibri(), 11,Font.UNDERLINE));
//        paragraph.setAlignment(Element.ALIGN_CENTER);
//        document.add(new Chunk(paragraph, BOLD_UNDERLINED));
        
//        paragraph.setAlignment(Element.ALIGN_RIGHT);
        
      Chunk underline = new Chunk("Document List", new Font(getcalibri(), 11,Font.BOLD));
      underline.setUnderline(0.4f, -2f); //0.1 thick, -2 y-location
      
         Paragraph paragraph = new Paragraph(25, underline);
        paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable table21 = new PdfPTable(1);
        // table1.setWidths(new int[]{24, 24, 2});
        table21.setTotalWidth(507);
        table21.setLockedWidth(true);
        table21.getDefaultCell().setFixedHeight(20);
        table21.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);


        Iterator<String> it = docList.iterator();
        int i =1;
        while(it.hasNext()){
        PdfPCell secondCell = new PdfPCell(new Phrase(25, i+".  "+ it.next(), new Font(getcalibri(), 11, Font.NORMAL)));
        secondCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        secondCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        secondCell.setBorder(Rectangle.NO_BORDER);
        secondCell.setPadding(15);
        table21.addCell(secondCell);
        i++;
        }

        table21.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());

    }


    public static void main(String[] args) {
        try {
            new DocListPDF();
//            new ServiceAgreementPdf();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
