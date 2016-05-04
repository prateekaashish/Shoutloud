package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("get hit");
	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dao.DataBase db=new dao.DataBase();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the Test Servlet");

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.print("<br/>Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			out.print("</em>, Header Value: <em>" + headerValue);
			out.println("</em>");
		}

		out.println("<hr/>");
		String authHeader = request.getHeader("authorization");
		String encodedValue = authHeader.split(" ")[1];
		out.println("Base64-encoded Authorization Value: <em>" + encodedValue);
		byte[] decodedValue = Base64.decode(encodedValue);
		out.println("</em><br/>Base64-decoded Authorization Value: <em>" + decodedValue.toString());
		out.println("</em>");
// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("post hit");

		System.out.print("server hit post");
		ServletInputStream is=request.getInputStream();
		DataInputStream dis=new DataInputStream(is);
		String input;
		try{
		if((input=dis.readUTF())!=null){
			
			System.out.println(input);
			response.setStatus(HttpServletResponse.SC_OK);
			DataOutputStream writer = new DataOutputStream(response.getOutputStream());
            writer.writeUTF("LOGIN SUC"
            		+ "CESSFUL".toString());
            writer.flush();
            writer.close();
            
		
	}
		}catch(Exception e)
		{
			e.printStackTrace();}
		}
}
