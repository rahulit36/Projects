/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

/**
 *
 * @author root
 */
public class ApprovalEmailText {
    public String getApprovalText(EmailScreen1 es){
        String str = "<html>\n" +
"<head>\n" +
"<meta charset=\"utf-8\">\n" +
"<title>Meeting Shedule</title>\n" +
"</head>\n" +
"\n" +
"<body style=\"margin:0px; padding:0px;font:normal 12px arial;\">\n" +
"<table align=\"center\" width=\"98%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"  <tr>\n" +
"    <td width=\"80%\">&nbsp;</td>\n" +
"    <td width=\"20%\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"\n" +
"  <tr>\n" +
"    <td colspan=\"2\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">Dear Sir,</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">Kindly approve the below mentioned client for LCBD limit setup.</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\"><strong>1. Company at a Glance</strong></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\"><table style=\" border:1px solid #4bacc6;\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"  <tr>\n" +
"    <td width=\"45%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Particulars</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold;border-right:1px solid #ccc; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#FFF; border-right:1px solid #fff; padding:5px 5px;\">Company Name </td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.companyNameStr+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td valign=\"top\" height=\"50\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Address</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.addString+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Product Manufacture/Trade</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.tradText+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Industry</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.industryText+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Year Of Incorporation</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.estd+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">External Credit Rating (If Any)</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.extStringTxt+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Proposed Facility</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px; font-weight:bold;\">Purchase</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px; font-weight:bold;\">Sale</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Proposed Amount</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.proAmount1Txt+"</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.proAmount2Txt+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Usance Days</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.usString+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Proposed Bank</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.propoStringBank+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Proposed Bank Rate (%)</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.proBankPercTxt+"</td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr>\n" +
"    <td style=\"background:#fff; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; padding:5px 5px;\"></td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#fff; border-bottom:1px solid #fff; text-align:center; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Our Charges</td>\n" +
"    \n" +
"    <td colspan=\"2\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; font-weight:bold; text-align:left; border-right:1px solid #fff; padding:5px 5px;\">"+es.ourChrgsText+"% Flat/P.A with Service Tax</td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Projections</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\"><strong>Monthly</strong></td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\"><strong>Yearly</strong></td>\n" +
"    </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:right; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Volume</td>\n" +
"    \n" +
"    <td width=\"30%\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.volumeTxt+"</td>\n" +
"    <td width=\"25%\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.vol2Txt+"</td>\n" +
"  </tr>\n" +
"  \n" +
"   <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:right; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Revenue (INR)</td>\n" +
"    \n" +
"    <td width=\"30%\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.rev1Txt+"</td>\n" +
"    <td width=\"25%\" style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.rev2Txt+"</td>\n" +
"  </tr>\n" +
"  \n" +
"</table>\n" +
"</td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr><td colspan=\"2\" height=\"20\"></td></tr>\n" +
"  <tr>\n" +
"  \n" +
"  <tr><td><strong>2. Financials </strong></td>\n" +
"    <td> Figs in Cr.</td>\n" +
"  </tr>\n" +
"  <tr><td colspan=\"2\"><table style=\" border:1px solid #4bacc6;\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"  <tr>\n" +
"    <td width=\"45%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Particulars</td>\n" +
"    \n" +
"    <td width=\"21%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:center; color:#FFF; font:bold 12px arial;border-right:1px solid #fff; padding:5px 5px;\">FY <span style=\"font:bold 11px arial;\">2011-12</span></td>\n" +
"    <td width=\"18%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:center;color:#FFF; font-weight:bold;border-right:1px solid #fff; padding:5px 5px;\">FY <span style=\"font:bold 11px arial;\">2012-13</span></td>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align: center; font-weight:bold;border-right:1px solid #fff;color:#FFF; padding:5px 5px;\">FY <span style=\"font:bold 11px arial;\">2013-14</span></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#FFF; border-right:1px solid #fff; padding:5px 5px;\">  </td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px; font-weight:bold;\">Audited</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px; font-weight:bold;\">Audited</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px; font-weight:bold;\">Provisional</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Turnover\n" +
"</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.turn1Txt+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.turn2Txt+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.turn3Txt+"</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Purchases</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.purchases1+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.purchases2+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.purchases3+"</td>\n" +
"  </tr>\n" +
"  \n" +
"  \n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Domestic ( %)</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.domesticTxt1+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.domesticTxt2+"</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">"+es.domesticTxt3+"</td>\n" +
"  </tr>\n" +
"  \n" +
" \n" +
"</table></td>\n" +
"</tr>\n" +
"  <tr>\n" +
"  \n" +
"    <td colspan=\"2\" height=\"20\"></td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr>\n" +
"  \n" +
"    <td><strong>3. Working Capital Arrangement </strong></td>\n" +
"    <td>Figs in Cr.</td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr>\n" +
"  \n" +
"    <td colspan=\"2\"><table style=\" border:1px solid #4bacc6;\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"  <tr>\n" +
"    <td width=\"45%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:center; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">Bank Name</td>\n" +
"    \n" +
"    <td width=\"21%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:center; color:#FFF; font-weight:bold;border-right:1px solid #fff; padding:5px 5px;\">FB Limit</td>\n" +
"    <td width=\"18%\" style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:center;color:#FFF; font-weight:bold;border-right:1px solid #fff; padding:5px 5px;\">NFB Limit</td>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align: center; font-weight:bold;border-right:1px solid #fff;color:#FFF; padding:5px 5px;\">Total</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#FFF; border-right:1px solid #fff; padding:5px 5px;\">  </td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:left; font-weight:bold; color:#fff; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  \n" +
"  \n" +
"  <tr>\n" +
"    <td style=\"background:#4bacc6; border-bottom:1px solid #fff; text-align:right; font-weight:bold; color:#fff; border-right:1px solid #ccc; padding:5px 5px;\">Total</td>\n" +
"    \n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"    <td style=\"background:#d2eaf1; border-bottom:1px solid #fff; text-align:center; border-right:1px solid #fff; padding:5px 5px;\">&nbsp;</td>\n" +
"  </tr>\n" +
"  \n" +
" \n" +
"</table></td>\n" +
"  </tr>\n" +
"  \n" +
"  \n" +
"  <tr>\n" +
"  \n" +
"    <td colspan=\"2\" height=\"20\"></td>\n" +
"  </tr>\n" +
"  \n" +
"  <tr><td><strong>4. Special Note:-</strong></td></tr>\n" +
"  \n" +
"  <tr><td colspan=\"2\" style=\"width:400px; height:200px; padding:5px; border:1px solid #ccc;\"></td></tr>\n" +
"   \n" +
"  <tr><td><pre style=\"font:normal 12px arial;\">\n" +
"    \n" +
"Best Regards,\n" +
"Relationship Manager\n" +
"Designation\n" +
"    \n" +
"    </pre></td></tr>\n" +
"  <tr>\n" +
"    <td><img src=\"cid:imgPart\"/>\n" +
"    <pre style=\"font:normal 12px arial;\">Corporate Office: S-26,27,28, IIIrd Floor, Veera Tower, Green Park Extn, New Delhi-110016, India.					\n" +
"Mobile: +91 9910555399 I Office: +91-2652 2441/42 I Fax: + 91-2651 2226	</pre>\n" +
"<a style=\"color:#666;\" target=\"_blank\" href=\"http://www.investeurs.com/\">Website: www.investeurs.com</a>\n" +
"    </td>\n" +
"  </tr>\n" +
"  <tr><td height=\"10\"></td></tr>\n" +
"  <tr>\n" +
"    <td>										\n" +
"</td>\n" +
"  </tr>\n" +
"    </table>\n" +
"</td>\n" +
"    <td></td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td colspan=\"2\"><div style=\"text-align:justify; border:3px solid #000; padding:5px 5px; line-height:20px;\">This email and any files transmitted with it are confidential and intended solely for the use of the individual or entity to whom they are addressed. If you are not the named addressee you should not distribute or copy this e-mail. Please notify the sender immediately if you have received this e-mail by mistake and delete it from your system. The recipient should check this email and any attachments for the presence of viruses. ICPL makes no warranty that this e-mail is error or virus free and accepts no liability for any damage caused by any virus transmitted by this mail.</div></td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"</body>\n" +
"</html>";
        
        
        return str;
    }
}
