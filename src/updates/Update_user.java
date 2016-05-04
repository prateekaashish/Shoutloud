package updates;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple.JSONArray;
import simple.JSONObject;
import simple.parser.JSONParser;

/**
 * Servlet implementation class Update_user
 */
@WebServlet("/Update_user")
public class Update_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user() {
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
		System.out.print("update user post hit");

		ServletInputStream is=request.getInputStream();
		DataInputStream dis=new DataInputStream(is);
		String input;
		try{
		if((input=dis.readUTF())!=null){
			
			System.out.println(input);

			JSONParser p=new JSONParser();
			JSONObject data=(JSONObject)p.parse(input);
			String type=(String)data.get("TYPE");

			System.out.println(type);
			if(type.equals("GETALLFRIENDS")){
				
			}else if(type.equals("GETALLUSERS")){
				JSONArray userLocations=dao.FetchData.getLocationOfAllUsers(data);
				DataOutputStream writer = new DataOutputStream(response.getOutputStream());
	            writer.writeUTF(userLocations.toJSONString());
				writer.flush();
				writer.close();
				System.out.println("anonymous users"+userLocations);
			}else if(type.equals("GETCONTACTS")){
				System.out.println("inside getcontacts");
				JSONArray contactLocations=dao.FetchData.getLocationOfContacts(data);
				DataOutputStream writer = new DataOutputStream(response.getOutputStream());
	            writer.writeUTF(contactLocations.toJSONString());
				writer.flush();
				writer.close();
				System.out.println(contactLocations);
			}else if(type.equals("GETTAGGEDMESSAGES")){
				System.out.println("inside gettagged messages");
				JSONArray taggedMessages=dao.FetchData.getTaggedMessages(data);
				DataOutputStream writer = new DataOutputStream(response.getOutputStream());
	            writer.writeUTF(taggedMessages.toJSONString());
				writer.flush();
				writer.close();
				System.out.println(taggedMessages);
			}
			
			//wait(1000);
			response.setStatus(HttpServletResponse.SC_OK);
			
		
	}
		}catch(Exception e)
		{
			e.printStackTrace();}
		
	}

}
