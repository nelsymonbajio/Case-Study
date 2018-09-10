package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilesFilter implements Filter {

	public JspFilesFilter() {
	}

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("admin"))
		{
			if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Users.jsp"))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Users/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Products.jsp"))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Profile.jsp"))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Profile/");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else if(servletRequest.getSession().getAttribute("username")!=null&&servletRequest.getSession().getAttribute("accessLevel").equals("user"))
		{
			if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Users.jsp")|| servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/AddUserForm.jsp")
					||servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/UpdateUserForm.jsp")) 
			{ 
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Profile.jsp"))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Profile/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/AddProductForm.jsp")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{	
				chain.doFilter(request,response);
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/AddProductForm.jsp")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("createPriv")))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/UpdateProductForm.jsp")&&Boolean.TRUE.equals(servletRequest.getSession().getAttribute("updatePriv")))
			{
				chain.doFilter(request,response);
			}
			else if(servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/UpdateProductForm.jsp")&&Boolean.FALSE.equals(servletRequest.getSession().getAttribute("updatePriv")))
			{
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Products/");
			}
			else {
				chain.doFilter(request,response);
			}
		}
		else 
		{
			if(!servletRequest.getRequestURI().equals(servletRequest.getContextPath()+"/Login.jsp")) {
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/Login.jsp");
			}else {
				chain.doFilter(request, response);
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}