/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.centricom.poli.services.merchantapiservice.GetDetailedTransactionDocument;
import com.centricom.poli.services.merchantapiservice.GetDetailedTransactionResponseDocument;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.GetDetailedTransactionRequest;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.DetailedTransaction;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.TransactionStepsList;
import policlient.MerchantAPIService_GetDetailedTransaction_MerchantApiFaultFault_FaultMessage;

/**
 *
 * @author x
 */
public class transaction extends HttpServlet {

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
            out.println("<title>Servlet transaction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet transaction at " + request.getContextPath() + "</h1>");

            String mcode = request.getServletContext().getInitParameter("merchantcode");
            String authcode = request.getServletContext().getInitParameter("authcode");
            String sp = request.getServletContext().getInitParameter("servicepoint");
            policlient.MerchantAPIServiceStub stub = new policlient.MerchantAPIServiceStub(sp);

            if (request.getParameter("tref") == null && request.getParameter("tref") == null) {
                out.println("date requred");
                out.close();
                return;
            }
            try {
                GetDetailedTransactionDocument gdt = GetDetailedTransactionDocument.Factory.newInstance();
                GetDetailedTransactionRequest req = GetDetailedTransactionRequest.Factory.newInstance();

                req.setMerchantCode(mcode);
                req.setAuthenticationCode(authcode);
                req.setIncludeSteps(true);
                if (request.getParameter("tref") != null) {
                    req.setTransactionRefNo(request.getParameter("tref").toString());
                }
                if (request.getParameter("mref") != null) {
                    req.setMerchantReference(request.getParameter("mref"));
                }




                gdt.addNewGetDetailedTransaction().setRequest(req);
                GetDetailedTransactionResponseDocument detailedTransaction = stub.getDetailedTransaction(gdt);

                 /*print error message */
 if(detailedTransaction.getGetDetailedTransactionResponse().getGetDetailedTransactionResult().getErrors().sizeOfErrorArray()>0){
     
               for(org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Error e:detailedTransaction.getGetDetailedTransactionResponse().getGetDetailedTransactionResult().getErrors().getErrorArray()){
          
              out.println("Error Code:"+e.getCode()+" Message: "+e.getMessage());
              
      }
          
          out.close();

 }

                
                
                
                DetailedTransaction dt = detailedTransaction.getGetDetailedTransactionResponse().getGetDetailedTransactionResult().getDetailedTransaction();

                TransactionStepsList[] tls = detailedTransaction.getGetDetailedTransactionResponse().getGetDetailedTransactionResult().getTransactionStepList().getTransactionStepsListArray();
                out.println("<table><tr><th>CurrencyCode</th><td>");
                out.println(dt.getCurrencyCode());
                out.println("</td></tr>");
                out.println("<tr><th>FinancialInstitutionCode</th><td>");
                out.println(dt.getFinancialInstitutionCode());
                out.println("</td></tr>");
                out.println("<tr><th>FinancialInstitutionName</th><td>");
                out.println(dt.getFinancialInstitutionName());
                out.println("</td></tr>");
                out.println("<tr><th>TransactionRefNo</th><td>");
                out.println(dt.getTransactionRefNo());
                out.println("</td></tr>");
                out.println("<tr><th>MerchantReference</th><td>");
                out.println(dt.getMerchantReference());
                out.println("</td></tr>");
                out.println("<tr><th>AmountPaid</th><td>");
                out.println(dt.getAmountPaid());
                out.println("</td></tr>");
                out.println("<tr><th>BankReceiptNo</th><td>");
                out.println(dt.getBankReceiptNo());
                out.println("</td></tr>");
                out.println("<tr><th>CurrencyName</th><td>");
                out.println(dt.getCurrencyName());
                out.println("</td></tr>");
                out.println("<tr><th>EndDateTime</th><td>");
                out.println(dt.getEndDateTime());
                out.println("</td></tr>");
                out.println("<tr><th>EstablishedDateTime</th><td>");
                out.println(dt.getEstablishedDateTime());
                out.println("</td></tr>");
                out.println("<tr><th>MerchantCode</th><td>");
                out.println(dt.getMerchantCode());
                out.println("</td></tr>");
                out.println("<tr><th>MerchantCommonName</th><td>");
                out.println(dt.getMerchantCommonName());
                out.println("</td></tr>");
                out.println("<tr><th>MerchantDefinedData</th><td>");
                out.println(dt.getMerchantDefinedData());
                out.println("</td></tr>");
                out.println("<tr><th>PayerAcctNumber</th><td>");
                out.println(dt.getPayerAcctNumber());
                out.println("</td></tr>");
                out.println("<tr><th>PayerAcctSortCode</th><td>");
                out.println(dt.getPayerAcctSortCode());
                out.println("</td></tr>");
                out.println("<tr><th>PayerAcctSuffix</th><td>");
                out.println(dt.getPayerAcctSuffix());
                out.println("</td></tr>");
                out.println("<tr><th>PaymentAmount</th><td>");
                out.println(dt.getPaymentAmount());
                out.println("</td></tr>");
                out.println("<tr><th>TransactionStatus</th><td>");
                out.println(dt.getTransactionStatus());
                out.println("</td></tr>");
                out.println("<tr><th>TransactionStatusCode</th><td>");
                out.println(dt.getTransactionStatusCode());
                out.println("</td></tr>");
                out.println("<tr><th>FailureReason</th><td>");
                out.println(dt.getFailureReason());
                out.println("</td></tr>");
                out.println("<tr><th>UserIPAddress</th><td>");
                out.println(dt.getUserIPAddress());
                out.println("</td></tr>");
                out.println("<tr><th>UserPlatform</th><td>");
                out.println(dt.getUserPlatform());
                out.println("</td></tr></table>");

                if (tls.length > 0) {

                    out.println("<table><caption>Transaction Step List</caption><thead><tr><th>Step Name</th><th>Created Date</th></tr></thead><tbody>");
                    for (TransactionStepsList t : tls) {
                        out.println("<tr><td>" + t.getTransactionStepTypeName() + "</td><td>" + t.getCreatedDateTime() + "</td></tr>");
                    }
                    out.println("</table>");

                }
                out.println("done");

            } catch (MerchantAPIService_GetDetailedTransaction_MerchantApiFaultFault_FaultMessage ex) {
                Logger.getLogger(transaction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(transaction.class.getName()).log(Level.SEVERE, null, ex);
            }







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
