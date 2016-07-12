package com.fynn;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		System.out.println("init method");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy method");
    }
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	        System.out.println("Servlet served");  
	       
	        // 1.Get parameter from HTTP header  
	        String goWhere = request.getParameter("goto");  
	          
	        if (goWhere == null || "1".equals(goWhere)) {  
	              
	            // 2.Set response header  
	            response.setContentType("text/html");  
	              
	            // 3.Get useful info from TCP & HTTP header  
	            System.out.println(  
	                    "Request from: " + request.getRemoteAddr() + ":" +   
	                    request.getRemotePort() + " by method " +   
	                    request.getMethod());  
	              
	            // 4.Print html(out is built-in object in JSP)  
	            DataOutputStream out = new DataOutputStream(response.getOutputStream());  
	            out.writeUTF("Hello Servlet");  
	            out.writeUTF("<br>");  
	            out.close();  
	        }  
	        else if ("2".equals(goWhere)) {  
	            RequestDispatcher dispather = request.getRequestDispatcher("/main.jsp?param1=java");  
	            request.setAttribute("param2", "servlet");  
	            dispather.forward(request, response);  
	        }  
	        else if ("3".equals(goWhere)) {  
	            response.sendRedirect("http://www.google.com?newwindow=1&q=java&oq=java");  
	        } 
	}
}