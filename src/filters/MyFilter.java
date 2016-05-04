package filters;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter("/MyFilter")
public class MyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest r=(HttpServletRequest)request;
		String []username_password=(Base64.base64Decode(((r.getHeader("Authorization")).substring(6)))).split(":");
		String username=username_password[0];
		String password=username_password[1];
		if(username.equals("tomcat")&&password.equals("tomcat")){
			chain.doFilter(request, response);	
		}else{
			DataOutputStream writer = new DataOutputStream(response.getOutputStream());
            writer.writeUTF("AUTHENTICATION_ERROR".toString());
            writer.flush();
            writer.close();
		}
	 	System.out.print("filter processing "+username+password);
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
