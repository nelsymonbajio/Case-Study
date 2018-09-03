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

		if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("admin"))
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
		else if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("user"))
		{
			//Filter Access to admin modules
			if(servletRequest.getRequestURI().equals("/ACASESTUDY/Users.jsp")|| servletRequest.getRequestURI().equals("/ACASESTUDY/AddUserForm.jsp")
					||servletRequest.getRequestURI().equals("/ACASESTUDY/UpdateUserForm.jsp")) 
			{ 
				servletResponse.sendRedirect("/ACASESTUDY/Products/");
			}
			//Filter 
			else if(servletRequest.getRequestURI().equals("/ACASESTUDY/AddProductForm.jsp")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{	
				chain.doFilter(request,response);
			}
			else if(servletRequest.getRequestURI().equals("/ACASESTUDY/AddProductForm.jsp")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				servletResponse.sendRedirect("/ACASESTUDY/Products/");
			}
			else if(servletRequest.getRequestURI().equals("/ACASESTUDY/UpdateProductForm.jsp")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				chain.doFilter(request,response);
			}
			else if(servletRequest.getRequestURI().equals("/ACASESTUDY/UpdateProductForm.jsp")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				servletResponse.sendRedirect("/ACASESTUDY/Products/");
			}
			else {
				chain.doFilter(request,response);
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