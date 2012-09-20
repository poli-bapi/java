/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.polidemo.db.Receipts;
import com.polidemo.helpers.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x
 */
public class receipt extends HttpServlet {

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



            if (request.getSession().getAttribute("order") == null || request.getSession().getAttribute("tref") == null) {
                out.println("Session Expired");
                out.close();
                return;
            }

            EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
            EntityManager em = emf.createEntityManager();

            /*
             * find the receipt
             */
            List<Receipts> recs = em.createNamedQuery("Receipts.findReceipt", Receipts.class).setParameter("merchantReference", request.getSession().getAttribute("order")).setParameter("transactionRefNo", request.getSession().getAttribute("tref")).setMaxResults(1).getResultList();

            if (recs.isEmpty()) {
                out.println("NO receipt");
                out.close();
                return;
            }
            Receipts r = recs.get(0);



            out.println("<html>");
            out.println("<head>");
            out.println("<title>receipt</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("<tr><td>CurrencyCode</td><td>" + r.getCurrencyCode() + "</td></tr>");
            out.print("<tr><td>ErrorCode</td><td>" + r.getErrorCode() + "</td></tr>");
            out.print("<tr><td>FinancialInstitutionCode</td><td>" + r.getFinancialInstitutionCode() + "</td></tr>");
            out.print("<tr><td>FinancialInstitutionName</td><td>" + r.getFinancialInstitutionName() + "</td></tr>");
            out.print("<tr><td>MerchantReference</td><td>" + r.getMerchantReference() + "</td></tr>");
            out.print("<tr><td>TransactionRefNo</td><td>" + r.getTransactionRefNo() + "</td></tr>");
            out.print("<tr><td>TransactionID</td><td>" + r.getTransactionID() + "</td></tr>");
            out.print("<tr><td>AmountPaid</td><td>" + r.getAmountPaid() + "</td></tr>");
            out.print("<tr><td>BankReceipt</td><td>" + r.getBankReceipt() + "</td></tr>");
            out.print("<tr><td>BankReceiptDateTime</td><td>" + r.getBankReceiptDateTime() + "</td></tr>");
            out.print("<tr><td>CountryCode</td><td>" + r.getCountryCode() + "</td></tr>");
            out.print("<tr><td>CountryName</td><td>" + r.getCountryName() + "</td></tr>");
            out.print("<tr><td>CurrencyName</td><td>" + r.getCurrencyName() + "</td></tr>");
            out.print("<tr><td>EndDateTime</td><td>" + r.getEndDateTime() + "</td></tr>");
            out.print("<tr><td>ErrorMessage</td><td>" + r.getErrorMessage() + "</td></tr>");
            out.print("<tr><td>EstablishedDateTime</td><td>" + r.getEstablishedDateTime() + "</td></tr>");
            out.print("<tr><td>FinancialInstitutionCountryCode</td><td>" + r.getFinancialInstitutionCountryCode() + "</td></tr>");
            out.print("<tr><td>MerchantAcctName</td><td>" + r.getMerchantAcctName() + "</td></tr>");
            out.print("<tr><td>MerchantAcctNumber</td><td>" + r.getMerchantAcctNumber() + "</td></tr>");
            out.print("<tr><td>MerchantAcctSortCode</td><td>" + r.getMerchantAcctSortCode() + "</td></tr>");
            out.print("<tr><td>MerchantAcctSuffix</td><td>" + r.getMerchantAcctSuffix() + "</td></tr>");
            out.print("<tr><td>MerchantDefinedData</td><td>" + r.getMerchantDefinedData() + "</td></tr>");
            out.print("<tr><td>MerchantEstablishedDateTime</td><td>" + r.getMerchantEstablishedDateTime() + "</td></tr>");
            out.print("<tr><td>PaymentAmount</td><td>" + r.getPaymentAmount() + "</td></tr>");
            out.print("<tr><td>StartDateTime</td><td>" + r.getStartDateTime() + "</td></tr>");

            /*
             * clear session
             */
            request.getSession().removeAttribute("tref");
            request.getSession().removeAttribute("order");

            em.close();

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
