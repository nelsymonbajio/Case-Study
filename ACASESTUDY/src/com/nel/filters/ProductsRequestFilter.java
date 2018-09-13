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
public class ProductsRequestFilter implements Filter {
	
    public ProductsRequestFilter() {
    }
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		/**Filters requests in product module */
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("admin"))
		{
			chain.doFilter(request, response);
		}
		else if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("user"))
		{
			if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/DeleteProduct")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("deletePriv")))
			{
				chain.doFilter(request, response);
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/DeleteProduct")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("deletePriv")))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/UpdateProduct")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("updatePriv")))
			{
				chain.doFilter(request, response);
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/UpdateProduct")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("updatePriv")))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/AddProduct")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				chain.doFilter(request, response);
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products/AddProduct")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else 
		{
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/Login.jsp");
		}
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
