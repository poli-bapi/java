/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo;

import com.polidemo.db.Transactions;
import com.polidemo.db.TransactionsPK;
import com.polidemo.helpers.PersistenceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transaction;
import org.datacontract.schemas._2004._07.centricom_poli_services_merchantapi_dco.DetailedTransaction;

/**
 *
 * @author x
 */
public class test extends HttpServlet {

    
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

response.setContentType("text/plain");            

            out.println("<h1>Servlet test at " + request.getServerName()+ "- "+   request.getServerPort()+" "+request.getContextPath()+request.getScheme());  
Method [] mt=            DetailedTransaction.class.getMethods();
            
      for(Method m : mt){
          if(m.getName().startsWith("get"))
          out.println("out.println(\"<tr><th>"+m.getName().replace("get", "")+"</th><td>\");out.println(dt."+m.getName()+"());"+"out.println(\"</td></tr>\");") ;
          
      }      
            
            
            out.println("</body>");
            out.println("</html>");
            
        } 
        catch(Exception e){
            e.printStackTrace();
        }
        finally {            
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
