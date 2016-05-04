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
		System.out.print("LocationUpdate post hit");

		ServletInputStream is=request.getInputStream();
		DataInputStream dis=new DataInputStream(is);
		String input;
		try{
		if((input=dis.readUTF())!=null){
			
			System.out.println(input);
			response.setStatus(HttpServletResponse.SC_OK);
			DataOutputStream writer = new DataOutputStream(response.getOutputStream());
            writer.flush();
            writer.close();            
            JSONParser p=new JSONParser();
            JSONObject update=(JSONObject)p.parse(input);
            Double latitude=(Double)update.get("latitude");
            Double altitude=(Double)update.get("altitude");
            Double longitude=(Double)update.get("longitude");
            String UserNumber=(String)update.get("UserNumber");
            String sql="select *from user_location where phone_number="+UserNumber;
            if(dao.FetchData.cheackForUser(sql)){
              sql="UPDATE user_location SET latitude="+latitude+", altitude="+altitude+", longitude="+longitude+"where phone_number="+UserNumber;
              dao.PutData.UpdateLocation(sql);
            }else{
            	sql="insert into user_location (phone_number,latitude,longitude,altitude) values ("+UserNumber+","+latitude+","+longitude+","+altitude+")";
            System.out.println(sql);
            dao.PutData.InsertLocation(sql);
            	
            }
            /*
            JSONObject contacts=(JSONObject)update.get("contacts");
            for(Object e:contacts.keySet()){
            	String key=e.toString();
            	user_flags.UserFlags.modifyFlag(key,"FRIENDS_LOCATION_CHANGED", true);
            }*/
	}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}

}
