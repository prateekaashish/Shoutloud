package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.DataBase;

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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the Test Servlet");

	try {	
		Connection result = null;
	try{
		DataBase db=new DataBase();
		//	System.out.print(message);
			Connection conn=db.openConnection();
			 PreparedStatement updateemp;
			try {
				updateemp = conn.prepareStatement
					      ("insert into tagged_messages (TAGGER,MESSAGE,LONGITUDE,LATITUDE,DISTANCE,TIME) VALUES(?,?,?,?,?,?)");
			      updateemp.setString(1,"7503227437");
			      updateemp.setString(2,"hello world");
			      updateemp.setString(3,"123.345");
			      updateemp.setString(4,"123.4567889");
			      updateemp.setString(5,"123");
			      updateemp.setString(6,"123445");
			      updateemp.executeUpdate();
			      
			 } catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*    while (rs.next()) {
        out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "<br />");
    }*/
}catch(Exception e){
	
	
	
}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dao.DataBase db=new dao.DataBase();
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.print("<br/>Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			out.print("</em>, Header Value: <em>" + headerValue);
			out.println("</em>");
		}

		out.println("<hr/>");
		/*String authHeader = request.getHeader("authorization");
		String encodedValue = authHeader.split(" ")[1];
		out.println("Base64-encoded Authorization Value: <em>" + encodedValue);
		byte[] decodedValue = Base64.decode(encodedValue);
		out.println("</em><br/>Base64-decoded Authorization Value: <em>" + decodedValue.toString());
		out.println("</em>");*/
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
