package updates_from_user;

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

@WebServlet("/LocationUpdate")
public class LocationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocationUpdate() {
        super();
       
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream is=null;
		DataInputStream dis=null;
		DataOutputStream writer=null;
		JSONObject status=null;
		try{
		System.out.print("LocationUpdate post hit");
		is=request.getInputStream();
		dis=new DataInputStream(is);
		String input;
		status =new JSONObject();      
		writer = new DataOutputStream(response.getOutputStream());
		if((input=dis.readUTF())!=null){
			System.out.println(input);
			response.setStatus(HttpServletResponse.SC_OK);
		    JSONParser p=new JSONParser();
            JSONObject update=(JSONObject)p.parse(input);
            Double latitude=(Double)update.get("latitude");
            Double altitude=(Double)update.get("altitude");
            Double longitude=(Double)update.get("longitude");
            String UserNumber=(String)update.get("UserNumber");
            String sql="select *from user_location where phone_number="+UserNumber;
            if(dao.FetchData.cheackForUser(sql)){
              sql="UPDATE user_location SET latitude="+latitude+", altitude="+altitude+", longitude="+longitude+"where phone_number="+UserNumber;
              if(dao.PutData.UpdateLocation(sql)){
            	  	status.put("status",1);
            	  	writer.writeUTF(status.toJSONString());
      				writer.flush();
      				writer.close();      
              }else{
                  	status.put("status",0);
            	  	writer.writeUTF(status.toJSONString());
      				writer.flush();
      				writer.close();      
              }
            }else{
            	sql="insert into user_location (phone_number,latitude,longitude,altitude) values ("+UserNumber+","+latitude+","+longitude+","+altitude+")";
            	 if(dao.PutData.UpdateLocation(sql)){
                }else{
                   	status.put("status",0);
            	  	writer.writeUTF(status.toJSONString());
       				writer.flush();
       				writer.close();      
               }
            }
	}
		}catch(Exception e)
		{
			e.printStackTrace();
				writer = new DataOutputStream(response.getOutputStream());
				status =new JSONObject();   
				status.put("status",2);
				status.put("exception", e);
        	  	writer.writeUTF(status.toJSONString());
				writer.flush();
				writer.close();      
    
		}
	
		
	}

}
