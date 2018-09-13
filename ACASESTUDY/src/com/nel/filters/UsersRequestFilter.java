package com.nel.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UsersRequestFilter implements Filter {

    public UsersRequestFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		/*Filters requests in Users module */
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("admin"))
		{
			chain.doFilter(request, response);
		}
		else if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("user"))
		{
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/UserMain.jsp");
		}
		else {
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/Login.jsp");
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
