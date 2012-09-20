/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.centricom.poli.services.merchantapiservice.InitiateTransactionDocument;
import com.centricom.poli.services.merchantapiservice.InitiateTransactionResponseDocument;
import com.polidemo.db.Transactions;
import com.polidemo.db.TransactionsPK;
import com.polidemo.helpers.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_contracts.InitiateTransactionRequest;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.InitiateTransactionInput;
import policlient.MerchantAPIService_InitiateTransaction_MerchantApiFaultFault_FaultMessage;

/**
 *
 * @author x
 */
public class initiatetransaction extends HttpServlet {

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

        if (request.getSession().getAttribute("order") == null) {
            out.println("Session expired");
            out.close();
            return;
        }
        String order = (String) request.getSession().getAttribute("order");


        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            String mcode = request.getServletContext().getInitParameter("merchantcode");
            String authcode = request.getServletContext().getInitParameter("authcode");
            String sp = request.getServletContext().getInitParameter("servicepoint");
            policlient.MerchantAPIServiceStub stub = new policlient.MerchantAPIServiceStub(sp);
            if (request.getParameter("CurrencyCode") == null || request.getParameter("PaymentAmount") == null) {
                out.print("amount and currency code missing");
                out.close();
                return;
            }
            if (request.getSession().getAttribute("order") == null) { /*
                 * you may also check if transaction is already initiated and pending
                 */
                out.print("Session Expired");
                out.close();
            }

            InitiateTransactionDocument doc = InitiateTransactionDocument.Factory.newInstance();
            InitiateTransactionRequest itr = InitiateTransactionRequest.Factory.newInstance();
            itr.setAuthenticationCode(authcode);
            InitiateTransactionInput input = InitiateTransactionInput.Factory.newInstance();
            input.setMerchantCode(mcode);



            input.setCurrencyAmount(BigDecimal.valueOf(new Double(request.getParameter("PaymentAmount").toString()).doubleValue()));
            input.setCurrencyCode(request.getParameter("CurrencyCode").toString());
            input.setUserIPAddress(request.getRemoteAddr());
            input.setMerchantData(request.getSession().getId());
            input.setMerchantCode(mcode);

            input.setMerchantRef(request.getSession().getAttribute("order").toString());//put order id 


            /*
             * build your own host url, you may use constants here
             */
            String urlm = request.getScheme() + "://" + request.getServerName();
            if (request.getServerPort() != 80) {
                urlm += ":" + request.getServerPort();
            }
            urlm += request.getContextPath() + "/";
            input.setMerchantCheckoutURL(urlm);
            input.setMerchantHomePageURL(urlm); //use your own values here and other urls.
            input.setNotificationURL(urlm + "notify");//nudge url
            input.setUnsuccessfulURL(urlm);
            input.setSuccessfulURL(urlm + "receipt");//page printed on success 

            if (request.getParameter("FinancialInstitutionCode") != null) {
                input.setSelectedFICode(request.getParameter("FinancialInstitutionCode").toString());
            } else {
                input.setNilSelectedFICode();
            }


            input.setMerchantDateTime(java.util.Calendar.getInstance());
            input.setTimeout(1000);




            itr.setTransaction(input);


            doc.addNewInitiateTransaction().setRequest(itr);

            InitiateTransactionResponseDocument initiateTransaction = stub.initiateTransaction(doc);




            if (initiateTransaction.getInitiateTransactionResponse().isNilInitiateTransactionResult()) {
                out.println("Failed to initiate transaction");
                out.close();
                return;
            }

            /*
             * print error message
             */
            if (initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getErrors().sizeOfErrorArray() > 0) {

                for (org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.Error e : initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getErrors().getErrorArray()) {

                    out.println("Error Code:" + e.getCode() + " Message: " + e.getMessage());

                }

                out.close();

            }

            if (initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getTransactionStatusCode().equalsIgnoreCase("initiated")) {
                String url = initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getTransaction().getNavigateURL();
                String token = initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getTransaction().getTransactionToken();
                String tref = initiateTransaction.getInitiateTransactionResponse().getInitiateTransactionResult().getTransaction().getTransactionRefNo();



                /*
                 * save the transaction values for later reference in notify
                 */
                TransactionsPK pk = new TransactionsPK(order, tref);

                Transactions t = new Transactions(pk, new Float(request.getParameter("PaymentAmount").toString()).floatValue(), request.getParameter("CurrencyCode").toString(), Short.parseShort("0"), token);
                EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(t);
                tx.commit();
                em.close();
                request.getSession().setAttribute("tref", tref);//save the transaction ref for additional safety if needed.

                response.sendRedirect(url);

            } else {
                out.print("invalid code");
            }




        } catch (MerchantAPIService_InitiateTransaction_MerchantApiFaultFault_FaultMessage ex) {
            Logger.getLogger(initiatetransaction.class.getName()).log(Level.SEVERE, null, ex);
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
