package controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		if(servletRequest.getSession().getAttribute("username")!=null)
		{
			if(servletRequest.getRequestURI().equals("/ACASESTUDY/Users.jsp"))
			{
				servletResponse.sendRedirect("/ACASESTUDY/Users/");
			}
			else if(servletRequest.getRequestURI().equals("/ACASESTUDY/Products.jsp"))
			{
				servletResponse.sendRedirect("/ACASESTUDY/Products/");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else 
		{
			 if(!servletRequest.getRequestURI().equals("/ACASESTUDY/Login.jsp")) {
				 servletResponse.sendRedirect("/ACASESTUDY/Login.jsp");
			 }else {
				 chain.doFilter(request, response);
			 }
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
