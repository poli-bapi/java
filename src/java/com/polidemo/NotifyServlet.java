/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.centricom.poli.services.merchantapiservice.GetTransactionDocument;
import com.centricom.poli.services.merchantapiservice.GetTransactionResponseDocument;
import com.polidemo.db.Receipts;
import com.polidemo.db.Transactions;
import com.polidemo.db.TransactionsPK;
import com.polidemo.helpers.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.GetTransactionRequest;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Transaction;
import policlient.MerchantAPIService_GetTransaction_MerchantApiFaultFault_FaultMessage;

/**
 *
 * @author x
 */
public class NotifyServlet extends HttpServlet {

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

            if (request.getParameter("Token") == null) {
                out.print("invalid");
                out.close();
                return;
            }

            String token = request.getParameter("Token").toString();


            String mcode = request.getServletContext().getInitParameter("merchantcode");
            String authcode = request.getServletContext().getInitParameter("authcode");
            String sp = request.getServletContext().getInitParameter("servicepoint");



            policlient.MerchantAPIServiceStub stub = new policlient.MerchantAPIServiceStub(sp);
            GetTransactionDocument trans = GetTransactionDocument.Factory.newInstance();
            GetTransactionRequest req = GetTransactionRequest.Factory.newInstance();
            req.setAuthenticationCode(authcode);
            req.setTransactionToken(token);
            req.setMerchantCode(mcode);


            trans.addNewGetTransaction().setRequest(req);
            GetTransactionResponseDocument transaction = stub.getTransaction(trans);

            /*
             * print error message
             */
            if (transaction.getGetTransactionResponse().getGetTransactionResult().getErrors().sizeOfErrorArray() > 0) {

                for (org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Error e : transaction.getGetTransactionResponse().getGetTransactionResult().getErrors().getErrorArray()) {
                    Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, "Error Code:" + e.getCode() + " Message: " + e.getMessage());

                    out.println("Error Code:" + e.getCode() + " Message: " + e.getMessage());/*
                     * note output of this servlet is not used
                     */

                }

                out.close();

            }



            String code = transaction.getGetTransactionResponse().getGetTransactionResult().getTransactionStatusCode();
            if (!code.equals("Completed")) {
                out.println("Incomplete transaction");
                out.close();
                return;
            }

            Transaction transaction1 = transaction.getGetTransactionResponse().getGetTransactionResult().getTransaction();

            TransactionsPK pk = new TransactionsPK(transaction1.getMerchantReference(), transaction1.getTransactionRefNo());

            EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
            EntityManager em = emf.createEntityManager();
            Transactions t = (Transactions) em.find(Transactions.class, pk);
            if (t == null) {
                Logger.getLogger(NotifyServlet.class.getName()).log(Level.INFO, "Transaction not found");
                em.close();
                out.close();
                return;
            }
            if (t.getStatus() == 1) {
                /*
                 * transaction already processed
                 */
                em.close();
                out.close();
            }

            /**
             * check amount ,currency for safety
             */
            if (t.getAmount() != transaction1.getAmountPaid().floatValue() || !t.getCurrency().equalsIgnoreCase(transaction1.getCurrencyCode())) {
                Logger.getLogger(NotifyServlet.class.getName()).log(Level.INFO, "suspect");
                out.close();
                em.close();
                return;
            }



            EntityTransaction tx = em.getTransaction();
            tx.begin();
            t.setStatus(Short.parseShort("1"));

            em.persist(t);

            /*
             * save the values for receipt generation
             */

            Receipts rt = new Receipts(transaction1.getTransactionID());
            rt.setAmountPaid(transaction1.getAmountPaid().floatValue());
            rt.setBankReceipt(transaction1.getBankReceipt());
           
            Date dt = new SimpleDateFormat("d MMMM yyyy hh:mm:ss").parse(transaction1.getBankReceiptDateTime());
            rt.setBankReceiptDateTime(dt);
            rt.setCountryCode(transaction1.getCountryCode());
            rt.setCountryName(transaction1.getCountryName());
            rt.setCurrencyCode(transaction1.getCurrencyCode());
            rt.setCurrencyName(transaction1.getCurrencyName());
            rt.setEndDateTime(transaction1.getEndDateTime().getTime());
            rt.setErrorCode(transaction1.getErrorCode());
            rt.setErrorMessage(transaction1.getErrorMessage());
            rt.setEstablishedDateTime(transaction1.getEstablishedDateTime().getTime());
            rt.setFinancialInstitutionCode(transaction1.getFinancialInstitutionCode());
            rt.setFinancialInstitutionCountryCode(transaction1.getFinancialInstitutionCountryCode());
            rt.setFinancialInstitutionName(transaction1.getFinancialInstitutionName());
            rt.setMerchantAcctName(transaction1.getMerchantAcctName());
            rt.setMerchantAcctNumber(transaction1.getMerchantAcctNumber());
            rt.setMerchantAcctSortCode(transaction1.getMerchantAcctSortCode());
            rt.setMerchantAcctSuffix(transaction1.getMerchantAcctSuffix());
            rt.setMerchantDefinedData(transaction1.getMerchantDefinedData());
            rt.setMerchantEstablishedDateTime(transaction1.getMerchantEstablishedDateTime().getTime());
            rt.setMerchantReference(transaction1.getMerchantReference());
            rt.setPaymentAmount(transaction1.getPaymentAmount().floatValue());
            rt.setStartDateTime(transaction1.getStartDateTime().getTime());
            rt.setTransactionID(transaction1.getTransactionID());
            rt.setTransactionRefNo(transaction1.getTransactionRefNo());

            em.persist(rt);
            tx.commit();
            em.close();










            out.println("OK");





        } catch (ParseException ex) {
            ex.printStackTrace();
            Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MerchantAPIService_GetTransaction_MerchantApiFaultFault_FaultMessage ex) {
            ex.printStackTrace();
            Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
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
