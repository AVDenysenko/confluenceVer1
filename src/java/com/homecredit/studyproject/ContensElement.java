/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homecredit.studyproject;

/**
 *
 * @author andrew
 */
public class ContensElement {
    private int id;
    private String name;    
    private int parentId;
    private int orderNumber;
    //tels us if element has children
    private boolean hasChildren;
    //flag tells us if element has parent of all other elements
    private boolean isRoot;
    //flag tells us if element last in column
    private boolean isLast;
    //flag tells us if element has its own ul
    private boolean hasUl;
    //refference to parent ul of li element
    private int ulRef;
    
    public String getJavaScriptCodeOfElement() {
        StringBuilder javascript = new StringBuilder();
        String parentElement;
        String jspatt;
        String parentUl;
        StringBuilder liclass = new StringBuilder();
        if (this.isIsRoot()) { 
            parentElement = "ul0";
        } else {
            parentElement = "li" + this.getParentId();
        }
        if (this.isHasUl() && (!this.isIsRoot())) {
            jspatt = "rootel = document.getElementById('"+parentElement+"');"+
                              "ul1 = document.createElement('ul');"+
                              "ul1.setAttribute('id', 'ul"+this.getId()+"');"+
                              "ul1.setAttribute('class', 'Container');"+
                              "rootel.appendChild(ul1);";
            javascript.append(jspatt);            
        } 
        parentUl = "ul" + this.getUlRef();
        liclass.append("Node ");
        if (this.isHasChildren()) {
            liclass.append("ExpandClosed ");
        } else {
            liclass.append("ExpandLeaf ");
        }
        if (this.isIsRoot()){
            liclass.append("IsRoot ");
        }
        if (this.isIsLast()) {
            liclass.append("IsLast");
        }
        jspatt = "parul = document.getElementById('"+parentUl+"');"+
                              "li1 = document.createElement('li');"+
                              "li1.setAttribute('id', 'li"+this.getId()+"');"+
                              "li1.setAttribute('class', '"+liclass+"');"+
                              "parul.appendChild(li1);"+
                              "expdiv = document.createElement('div');"+
                              "expdiv.setAttribute('class', 'Expand');"+
                              "li1.appendChild(expdiv);"+
                              "condiv = document.createElement('div');"+
                              "condiv.setAttribute('class', 'Content');"+
                              "li1.appendChild(condiv);"+
                              "textel = document.createTextNode('"+this.getName()+"');"+                              
                              "condiv.appendChild(textel);";
                              
        javascript.append(jspatt);        
        return javascript.toString();
    }

    public int getUlRef() {
        return ulRef;
    }

    public void setUlRef(int ulRef) {
        this.ulRef = ulRef;
    }

    public boolean isHasUl() {
        return hasUl;
    }

    public void setHasUl(boolean hasUl) {
        this.hasUl = hasUl;
    }

    public int getId() {
        return id;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    public boolean isIsLast() {
        return isLast;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
    
    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }  
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
