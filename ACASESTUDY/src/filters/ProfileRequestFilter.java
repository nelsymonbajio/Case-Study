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

public class ProfileRequestFilter implements Filter {

    public ProfileRequestFilter() {
    }
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		if(servletRequest.getSession().getAttribute("username")!=null)
		{
			chain.doFilter(request, response);
		}
		else
		{
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/Login.jsp");
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
