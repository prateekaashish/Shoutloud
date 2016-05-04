package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple.JSONObject;
import simple.parser.JSONParser;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int error=0;
		ServletInputStream is=request.getInputStream();
		DataInputStream dis=new DataInputStream(is);
		String input;
		try{
		if((input=dis.readUTF())!=null){
			
			System.out.println(input);
			JSONParser p=new JSONParser();
			JSONObject register=(JSONObject)p.parse(input.toString());
			dao.PutData.register(register);
			//response.setStatus(HttpServletResponse.SC_OK);
			
		
	}
		}catch(Exception e)
		{
			error=1;
			DataOutputStream writer = new DataOutputStream(response.getOutputStream());
            writer.writeUTF("ERROR IN REGISTRATION".toString());
            writer.flush();
            writer.close();
            
			e.printStackTrace();}
		if(error==0){
		DataOutputStream writer = new DataOutputStream(response.getOutputStream());
        writer.writeUTF("SUCCESSFULLY_REGISTERED".toString());
        writer.flush();
        writer.close();
		}
		}
	
	}


