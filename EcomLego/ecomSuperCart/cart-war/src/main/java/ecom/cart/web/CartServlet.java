/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package ecom.cart.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import ecom.cart.ejb.Cart;
import ecom.cart.util.BookException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = -8312407323476917087L;
    @EJB
    private Cart cart;
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			cart.initialize("Duke d'Url", "123");
            cart.addBook("Infinite Jest");
            cart.addBook("Bel Canto");
            cart.addBook("Kafka on the Shore");
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Output the results
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<title>Servlet CartServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet CartServlet at " +
                request.getContextPath() + "</h1>");
        try {
            String title = request.getParameter("titleB");
            if (title != null && title.length() > 0) {
                
            	if(cart!=null)
            	{
            		cart.addBook(title);
            		out.println("<p> Book " + title + " added </p>" );	
            	}
            	else
            	{	
            		out.println("CART NULL");
            	}
               
            } else {
                out.println("<p>Enter a book name :</p>");
                out.println("<form method=\"get\">");
                out.println("<p>$ <input title=\"titleB\" type=\"text\" name=\"titleB\" size=\"25\"></p>");
                out.println("<br/>");
                out.println("<input type=\"submit\" value=\"Submit\">" +
                        "<input type=\"reset\" value=\"Reset\">");
                out.println("</form>");
            }

        } finally {
        	out.println(cart);
        	
        	List<String> content =  cart.getContents();
        	if(content != null)
        	{
        		out.println("Content: "+content);
        	}
        	else
        	{
        		out.println("Content NULL");
        	}
        	
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
