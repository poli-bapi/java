<%-- 
    Document   : index
    Created on : Sep 18, 2012, 5:18:33 AM
    Author     : x
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

   <style type="text/css">  
        BODY { font-family: verdana,arial,helvetica,sans-serif; font-size: x-small;}
        TD, TH {font-family: verdana,arial,helvetica,sans-serif; font-size: x-small;}
        .serif { font-family: times,serif; font-size: small; }
        .sans { font-family: verdana,arial,helvetica,sans-serif; font-size: small; }
        .small { font-family: verdana,arial,helvetica,sans-serif; font-size: x-small; }
        .h1 { font-family: verdana,arial,helvetica,sans-serif; color: #CC6600; font-size: small; }
        .h3color { font-family: verdana,arial,helvetica,sans-serif; color: #CC6600; font-size: x-small; }
        .tiny { font-family: verdana,arial,helvetica,sans-serif; font-size: xx-small; }
        .listprice { font-family: arial,verdana,sans-serif; text-decoration: line-through; font-size: x-small; }
        .price { font-family: verdana,arial,helvetica,sans-serif; color: #990000; font-size: x-small; }
        .ewise3P { font-family: verdana,arial,helvetica,sans-serif; color: #CC6600; font-size: small; }
        .ltgrey { color: #666666; }
        .orange { color: #CC6600; }   
        fieldset.input label.label { display:block; width:200px; }
        fieldset.input input.value { width:500px; color:Blue }
    </style>
    <title>
	Merchant ABCD - Checkout Page
</title>


    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="CheckoutForm" method="post" action="soap/initiatetransaction.php" id="CheckoutForm">

        <fieldset class="input">
            <legend>Test Input</legend>
            <ul>
                <li>
                    <label for="fi" id="lblFinancialInstitutionCode" class="label">Financial Institution Code:</label>
                    <select name="FinancialInstitutionCode" id="fi" class="value">
						<option value="">- none -</option>
					
                            <% for(Map.Entry<String,String> entry:((HashMap<String,String>)request.getAttribute("finlist")).entrySet()){ %>
                            <option value="<%out.print(entry.getKey());  %>" ><%  out.println(entry.getValue());  %></option>

        <% }%>

                    
                    </select>
                    [Leave as blank if you do not want to preselect the bank]
                </li>
                <li>
                    <label for="txtCurrencyCode" id="lblCurrencyCode" class="label">Currency Code:</label>
                    <input name="CurrencyCode" type="text" value="AUD" id="txtCurrencyCode" class="value" />
                </li>
                
            </ul>
        </fieldset>
        <table>
            <tr>
                <td class="orange">
                    Amount:
                    <input name="PaymentAmount" type="text" value="1.68" id="PaymentAmount" class="ltgrey" Value="1.00" />
                </td>
                <td class="orange">
                    Order #: 
                   <span id="orderno"><% out.println(session.getAttribute("order")); %></span>
                    
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr>
                <td class="tiny">
                    <img src="images/paymentmethod.gif" alt="Payment Method" /><br />
                    Items marked <font color="red">*</font> are required
                </td>
                <td align="right">
                    <a href="javascript:history.back()">
                        <img border="0" src="images/cart.gif" alt="" /></a>
                </td>
            </tr>
        </table>
        <table id="bodytable" border="0" cellpadding="0" cellspacing="0" width="800">
            <tr>
                <td>
                    &nbsp;
                </td>
                <td colspan="3">
                    <hr noshade size="1"/>
                </td>
            </tr>
            <tr>
                <td width="50">
                </td>
                <td colspan="3">
                    <input id="paysdd" type="radio" checked="checked" value="poli" name="payment-method" onclick="selectSecureDD()" />
                    <span style="font-size:large; font-style:italic;">Pay with a bank account</span> 
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td></td>
                <td width="500" align="left">
                    <img src="images/poli_logo_small.jpg" border="0" alt="POLi Pay Online" title="POLi Pay Online">
                    <br />
                    <span style="color:Blue; font-size:small; font-weight:bold;">Shop online using your internet banking</span>
                    <ul>
                        <li>Quick and easy to pay online</li>
                        <li>Secure, using your internet banking</li>
                        <li>No fees involved</li>
                        <li>No need to register</li>
                    </ul>
                </td>
                <td align="center" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor=''">
                    <span style="font-size:small;">Which bank will you pay with?</span>
                    <br />
                    <span id="lblFISupported" onclick="window.open('https://transaction.apac-test.paywithpoli.com/POLiFISupported.aspx?merchantcode=PriceBusterDVD_AU_AN','','width=700,height=450,scrollbars=yes');" style="color:Blue;background-color:White;border-color:White;font-family:Arial;font-size:X-Small;font-weight:bold;text-decoration:underline;">Check the list of available banks</span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <a id="linkFaq" href="https://transaction.apac-test.paywithpoli.com/POLiFaq.aspx?merchantcode=PriceBusterDVD_AU" target="_blank">
                        <font size="1pts">Find out more about POLi</font></a>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;&nbsp;
                </td>
            </tr>
            <tr height="40">
                <td colspan="2">
                    &nbsp;
                </td>
                <td colspan="2">
                    <!-- centrciom bank selection - required select object -->
                    <table id="polipaytable" style="display:block">
                        <tr>
                            <td align="left">
                                <!--<input type="submit" name="PayWithPOLi" value="Pay with POLi" id="PayWithPOLi" />!-->
                                <input type="image" name="PayWithPOLi1" id="PayWithPOLi1" Title="Pay with POLi" src="images/btn_pay_with_poli.gif" alt="Pay with POLi" style="border-width:0px;" />
                            </td>
                        </tr>
                    </table>
                    <!-- end centricom bank selection -->
                </td>
            </tr>
        </table>
        
        <div class="footer">© 2012 Centricom Pty Ltd. All Rights Reserved.</div>
    </form>

    </body>
</html>
