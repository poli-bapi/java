/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.centricom.poli.services.merchantapiservice.GetDailyTransactionsCSVDocument;
import com.centricom.poli.services.merchantapiservice.GetDailyTransactionsCSVResponseDocument;
import com.centricom.poli.services.merchantapiservice.GetDailyTransactionsDocument;
import com.centricom.poli.services.merchantapiservice.GetDailyTransactionsResponseDocument;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.GetDailyTransactionsCSVRequest;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.GetDailyTransactionsCSVResponse;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.GetDailyTransactionsRequest;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.DailyTransaction;
import policlient.MerchantAPIService_GetDailyTransactionsCSV_MerchantApiFaultFault_FaultMessage;
import policlient.MerchantAPIService_GetDailyTransactions_MerchantApiFaultFault_FaultMessage;

/**
 *
 * @author x
 */
public class merchant extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet merchant</title>");
            out.println("</head>");
            out.println("<body>");

            String mcode = request.getServletContext().getInitParameter("merchantcode");
            String authcode = request.getServletContext().getInitParameter("authcode");
            String sp = request.getServletContext().getInitParameter("servicepoint");
            policlient.MerchantAPIServiceStub stub = new policlient.MerchantAPIServiceStub(sp);

            if (request.getParameter("day") == null) {
                out.println("date requred");
                out.close();
                return;
            }
            String[] codes = request.getParameterValues("status");
            if (request.getParameter("submit") != null) {



                try {

                    GetDailyTransactionsDocument gtrs = GetDailyTransactionsDocument.Factory.newInstance();
                    GetDailyTransactionsRequest req = GetDailyTransactionsRequest.Factory.newInstance();

                    req.setMerchantCode(mcode);
                    req.setAuthenticationCode(authcode);

                    Date d = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(request.getParameter("day"));
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    req.setEstablishedDate(c);

                    if (codes != null && codes.length > 0) {
                        String code = codes[0];
                        for (String cc : codes) {
                            code += "," + cc;
                        }
                        req.setTransactionStatusCode(code);
                    }



                    gtrs.addNewGetDailyTransactions().setRequest(req);



                    GetDailyTransactionsResponseDocument dailyTransactions = stub.getDailyTransactions(gtrs);

                    if (dailyTransactions.getGetDailyTransactionsResponse().getGetDailyTransactionsResult().getErrors().sizeOfErrorArray() > 0) {

                        for (org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Error e : dailyTransactions.getGetDailyTransactionsResponse().getGetDailyTransactionsResult().getErrors().getErrorArray()) {

                            out.println("Error Code:" + e.getCode() + " Message: " + e.getMessage());

                        }

                        out.close();

                    }



                    DailyTransaction[] dailyts = dailyTransactions.getGetDailyTransactionsResponse().getGetDailyTransactionsResult().getDailyTransactionList().getDailyTransactionArray();





                    out.println("<table><tr><th>TransactionReference</th><th>MerchantRefNo</th<th>AmountPaid</th><th>BankReceiptNo</th><th>CurrencyCode</th><th>CurrencyName</th><th>EndDateTime</th><th>EstablishedDateTime</th><th>FinancialInstitutionCode</th><th>FinancialInstitutionName</th><th>MerchantCode</th><th>MerchantCommonName</th><th>MerchantDefinedData</th><th>PayerAcctNumber</th><th>PayerAcctSortCode</th><th>PayerAcctSuffix</th><th>PaymentAmount</th><th>TransactionStatus</th><th>TransactionStatusCode</th></tr>");
                    for (int i = 0; i < dailyts.length; i++) {

                        out.println("<tr><td><a href=\"transaction?tref=" + dailyts[i].getTransactionRefNo() + "\">" + dailyts[i].getTransactionRefNo() + "</a></td>");
                        out.println("<td><a href=\"transaction?mref=" + dailyts[i].getMerchantReference() + "\">" + dailyts[i].getMerchantReference() + "</td>");


                        out.println("<td>");
                        out.println(dailyts[i].getAmountPaid());
                        out.println("</td><td>");
                        out.println(dailyts[i].getBankReceiptNo());
                        out.println("</td><td>");
                        out.println(dailyts[i].getCurrencyCode());
                        out.println("</td><td>");
                        out.println(dailyts[i].getCurrencyName());
                        out.println("</td><td>");
                        out.println(dailyts[i].getEndDateTime());
                        out.println("</td><td>");
                        out.println(dailyts[i].getEstablishedDateTime());
                        out.println("</td><td>");
                        out.println(dailyts[i].getFinancialInstitutionCode());
                        out.println("</td><td>");
                        out.println(dailyts[i].getFinancialInstitutionName());
                        out.println("</td><td>");
                        out.println(dailyts[i].getMerchantCode());
                        out.println("</td><td>");
                        out.println(dailyts[i].getMerchantCommonName());
                        out.println("</td><td>");
                        out.println(dailyts[i].getMerchantDefinedData());
                        out.println("</td><td>");
                        out.println(dailyts[i].getPayerAcctNumber());
                        out.println("</td><td>");
                        out.println(dailyts[i].getPayerAcctSortCode());
                        out.println("</td><td>");
                        out.println(dailyts[i].getPayerAcctSuffix());
                        out.println("</td><td>");
                        out.println(dailyts[i].getPaymentAmount());
                        out.println("</td><td>");
                        out.println(dailyts[i].getTransactionStatus());
                        out.println("</td><td>");
                        out.println(dailyts[i].getTransactionStatusCode());
                        out.println("</td></tr>");


                    }

                    out.println("</table>");

                } catch (ParseException ex) {
                    ex.printStackTrace();
                    out.println("invalid date format " + request.getParameter("day"));
                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    ex.printStackTrace();

                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MerchantAPIService_GetDailyTransactions_MerchantApiFaultFault_FaultMessage ex) {
                    ex.printStackTrace();

                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                }



            } else if (request.getParameter("csv") != null) {

                try {




                    GetDailyTransactionsCSVDocument gtrs = GetDailyTransactionsCSVDocument.Factory.newInstance();
                    GetDailyTransactionsCSVRequest req = GetDailyTransactionsCSVRequest.Factory.newInstance();


                    req.setMerchantCode(mcode);
                    req.setAuthenticationCode(authcode);

                    Date d = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(request.getParameter("day"));
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    req.setEstablishedDate(c);

                    if (codes != null && codes.length > 0) {
                        String code = codes[0];
                        for (String cc : codes) {
                            code += "," + cc;
                        }
                        req.setTransactionStatusCode(code);
                    }



                    gtrs.addNewGetDailyTransactionsCSV().setRequest(req);
                    GetDailyTransactionsCSVResponseDocument dailyTransactionsCSV = stub.getDailyTransactionsCSV(gtrs);


                    if (dailyTransactionsCSV.getGetDailyTransactionsCSVResponse().getGetDailyTransactionsCSVResult().getErrors().sizeOfErrorArray() > 0) {

                        for (org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Error e : dailyTransactionsCSV.getGetDailyTransactionsCSVResponse().getGetDailyTransactionsCSVResult().getErrors().getErrorArray()) {

                            out.println("Error Code:" + e.getCode() + " Message: " + e.getMessage());

                        }

                        out.close();

                    }





                    out.println(dailyTransactionsCSV.getGetDailyTransactionsCSVResponse().getGetDailyTransactionsCSVResult().getCSVData());

                } catch (ParseException ex) {
                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MerchantAPIService_GetDailyTransactionsCSV_MerchantApiFaultFault_FaultMessage ex) {
                    Logger.getLogger(merchant.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
            out.println("done");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
