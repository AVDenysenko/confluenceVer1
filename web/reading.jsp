<%-- 
    Document   : index
    Created on : Jan 4, 2013, 5:06:27 PM
    Author     : andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.sql.*, com.homecredit.studyproject.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/tlds/tags.tld" prefix="ex" %>
<%@ taglib uri="/tlds/elfunctions.tld" prefix="elu" %>
<!DOCTYPE html>
<c:set var="title" value="first page"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/confluence/css/readingstyle.css" type="text/css">
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
                                    <ex:treeViewClassicTag title="Documents..."/>
                                </div>
                            </td>
                            <td valign="top">
                                <div>            
                                    <table id="readtable">
                                        <tr>
                                            <td>
                                                <p class="readtitle">${title}</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p class="readtext">${elu:getPageTextByTitle(title)}</p>
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
