<%-- 
    Document   : index
    Created on : Jan 4, 2013, 5:06:27 PM
    Author     : andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.sql.*, com.homecredit.studyproject.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%
        String title = null;
        String text = null;
        SqlQuery sqText = new SqlQuery();
        ResultSet rsText = sqText.executeStatement("SELECT * FROM posted_text WHERE name = 'first page'");            
        ResultSetMetaData rsmText = rsText.getMetaData();            
        while (rsText.next()) {
            title = rsText.getString("name");
            text = rsText.getString("text");
        }
        List<ContensElement> cel = new ArrayList<ContensElement>();
        ResultSet celbase = sqText.executeStatement("SELECT * FROM tree_contens ORDER BY order_number");
        while (celbase.next()) {
            ContensElement ce = new ContensElement();
            ce.setId(celbase.getInt("id"));
            ce.setName(celbase.getString("name"));
            ce.setParentId(celbase.getInt("parent"));
            ce.setOrderNumber(celbase.getInt("order_number"));
            ce.setHasChildren(false);
            ce.setIsLast(true);
            ce.setHasUl(true);
            if (ce.getParentId() == 0) ce.setIsRoot(true); else ce.setIsRoot(false);
            cel.add(ce);
        }        
        sqText.closeConnection(); 
        List<ContensElement> celCopy = cel;
        for (ContensElement celor:cel) {
            int ulRefference = 0;
            int k = 0;
            for (ContensElement celorCopy:celCopy) {
                if (celor.getId() == celorCopy.getParentId()) {
                    celor.setHasChildren(true);
                }
                if (celor.getParentId() == celorCopy.getParentId() && k == 0) {
                    ulRefference = celorCopy.getId();
                    k++;
                }
                if (celor.getParentId() == celorCopy.getParentId() && celor.getOrderNumber() < celorCopy.getOrderNumber()) {
                    celor.setIsLast(false);
                }
                if (celor.getParentId() == celorCopy.getParentId() && celor.getOrderNumber() > celorCopy.getOrderNumber()) {
                    celor.setHasUl(false);
                }                
            }
            if (celor.isIsRoot()) {
                celor.setUlRef(0);
            } else if (celor.isHasUl()) {
                celor.setUlRef(celor.getId());
            } else {
                celor.setUlRef(ulRefference);
            }
        }
 %>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/confluence/css/indexstyle.css" type="text/css">
        <link rel="stylesheet" href="/confluence/css/tree.css" type="text/css">
        <script type="text/javascript">
            <c:import url="/javascript/tree.js" />
        </script>
    </head>
        
    <body> 
        <table class="outertable" cellpadding="0" cellspacing="0">
            <tr>
                <td id="headertd">
                    <%@include file="/jspf/header.jspf"%>
                </td>
            </tr>
            <tr>
                <td class="contenttd">
                    <table  cellpadding="0" cellspacing="0">
                        <tr>
                            <td id="treetd" valign="top">
                                <div id="treediv">
                                    <div onclick="tree_toggle(arguments[0])" id="treev">
                                        <%
                                            String javascript = "";
                                            for (ContensElement celor : cel) {
                                                javascript = javascript + celor.getJavaScriptCodeOfElement();
                                        
                                            }
                                        %><script type="text/javascript">
                                            rootel = document.getElementById('treev');
                                            ulm = document.createElement('ul');
                                            ulm.setAttribute('id', 'ul0');
                                            ulm.setAttribute('class', 'Container');
                                            rootel.appendChild(ulm);
                                            <%=javascript%></script><%
                                            %>                               
                                    </div>
                                </div>
                            </td>
                            <td valign="top">
                                <div>            
                                    <table id="readtable">
                                        <tr>
                                            <td>
                                                <p class="readtitle"><%=title%></p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p class="readtext"><%=text%></p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input id="readedit" class="readbutton" type="submit" value="Edit"/>                                        
                                                <input id="readnew" class="readbutton" type="submit" value="New"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td id="footertd">
                    <%@include file="/jspf/footer.jspf"%>
                </td>
            </tr>
        </table> 
    </body>
</html>
