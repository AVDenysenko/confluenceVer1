/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homecredit.studyproject.tags;

import com.homecredit.studyproject.ContensElement;
import com.homecredit.studyproject.SqlQuery;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author andrew
 */
public class TreeViewClassicTag extends TagSupport{ 
    
    private String javascript;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    /* Process Start Tag. This method forms treeview*/
    public int doStartTag() throws JspTagException {
        javascript = "";
        SqlQuery sqText = new SqlQuery();
        List<ContensElement> cel = new ArrayList<ContensElement>();
        try {
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
                if (ce.getParentId() == 0) {
                    ce.setIsRoot(true);
                } else {
                    ce.setIsRoot(false);
                }
                cel.add(ce);
            }
            sqText.closeConnection();
            List<ContensElement> celCopy = cel;
            for (ContensElement celor : cel) {
                int ulRefference = 0;
                int k = 0;
                for (ContensElement celorCopy : celCopy) {
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
        } catch (SQLException e) {
        }

        for (ContensElement celor : cel) {
            javascript = javascript + celor.getJavaScriptCodeOfElement();                                        
        }
        return EVAL_BODY_INCLUDE;
    }
    
    /* Process End Tag. Outputs result */
    public int doEndTag() throws JspTagException {
         try {
            JspWriter out = pageContext.getOut();
            if (this.title != null) {
                out.print("<p style='margin-bottom:0px;font-size:18px;font-weight:bold;font-style:oblique;'>" + this.title + "</p>");
            }
            out.print(" <div onclick='tree_toggle(arguments[0])' id='treev'> ");
            out.print(" <script type='text/javascript'> ");
            out.print(" rootel = document.getElementById('treev'); ");
            out.print(" ulm = document.createElement('ul'); ");
            out.print(" ulm.setAttribute('id', 'ul0'); ");
            out.print(" ulm.setAttribute('class', 'Container'); ");
            out.print(" rootel.appendChild(ulm); ");          
            out.print(javascript);
            out.print(" </script> ");
            out.print(" </div> ");          
        } catch (IOException e) {
            throw new JspTagException(e.toString());
        }
        return EVAL_PAGE;
    }
}
