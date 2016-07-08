    <!-- 1.Compile instruction -->  
    <%@page   
        import="java.util.concurrent.atomic.*, com.cdai.web.j2ee.TestBean"   
        contentType="text/html;charset=utf-8"   
    %>  
      
    <!-- 2.Declaration: member variable and method -->  
    <%!   
      
        private AtomicInteger count = new AtomicInteger(1);  
        private ThreadLocal<Integer> curCountStorage = new ThreadLocal<Integer>();  
          
        private int getCount() {  
            int curCount = count.getAndIncrement();  
            curCountStorage.set(curCount);  
            return curCount;  
        }  
    %>  
      
    <!-- 3.JSP code & 4.Built-in object -->  
    <%  
        Object curCount = session.getAttribute("count");  
        if (curCount == null) {  
            curCount = getCount();  
            session.setAttribute("count", curCount);  
        }  
          
        out.println(request.getParameter("param1") + " - " + request.getAttribute("param2"));  
    %>  
      
    <br> This is main.jsp. You're the <%=curCount%> visitor.  
      
    <!-- 5.Runtime action -->  
    <jsp:useBean id="testBean" class="com.cdai.web.j2ee.TestBean" scope="page"/>  
    <br>Message in TestBean is: <jsp:getProperty property="message" name="testBean"/>  