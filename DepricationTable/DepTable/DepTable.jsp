<%-- 
    Document   : DepTable
    Created on : Mar 12, 2019, 12:02:09 PM
    Author     : xanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%page import="java.io.*" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <HTML>
        <HEAD>
            <TITLE> Deprecation Table </TITLE>
        </HEAD>
        <BODY>
            <% double cost = Double.parseDouble(request.getParameter("Cost")); 
             double scrap = Double.parseDouble(request.getParameter("Scrap")); 
             double life = Double.parseDouble(request.getParameter("Life")); 
             double[] arr = new double[life];
             if(!cost.isDigit() || !scrap.isDigit() || !life.isDigit()) {
                   <HEAD><TITLE><H1> Invalid Data </H1></TITLE></HEAD> 
                           }
            else if(cost.isDigit() && scrap.isDigit() && life.isDigit() && request.getParameter("RADIO").equals(Straight)){
                getSLD();
            }%>

            <%! 
            public void getSLD(double cost, double scarp, double life, double[] arr){
            double accDeprecitation = (cost - scrap)/life;
            for(int i = 0; i < (int) life; i++){
                arr[i] = accDeprecitation;
                if(accDeprecitation > cost)
                accDeprecitation += (accDeprecitation - cost);
                else
                accDeprecitation += accDeprecitation;
                
}
            

}

            
            %>
        </BODY>
    </HTML>
</f:view>
