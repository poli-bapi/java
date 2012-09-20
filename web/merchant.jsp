<%-- 
    Document   : merchant
    Created on : Sep 19, 2012, 3:08:10 PM
    Author     : x
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Merchant Console</h1>
        <%  new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"); %>

        <form method="POST" action="merchant">Date:<input type="text" name="day" value="<% out.println(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date())); %>"/>
<select multiple="multiple" name="status[]">
<option value=""></option><option value="Initiated">Initiated</option><option value="FinancialInstitutionSelected">FinancialInstitutionSelected</option><option value="EULAAccepted">EULAAccepted</option><option value="InProcess">InProcess</option><option value="Completed">Completed</option><option value="Unknown">Unknown</option><option value="Failed">Failed</option><option value="ReceiptUnverified">ReceiptUnverified</option><option value="Cancelled">Cancelled</option><option value="TimedOut">TimedOut</option></select>
    
    
    <input type="submit" name="submit" value="submit"/><input type="submit" name="csv" value="CSV Report"/>
</form>


    </body>
</html>
