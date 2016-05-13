package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import simple.JSONArray;
import simple.JSONObject;


public class FetchData {

	public static JSONArray getLocationOfAllUsers(JSONObject data){
		//String sql="call getNearByUsers("+data.get("latitude")+","+data.get("longitude")+","+data.get("phone_number")+","+data.get("range")+")";
		Double lat=(Double)data.get("latitude");
		Double lon=(Double)data.get("longitude");
		String p=(String)data.get("phone_number");
		Long range=(Long)data.get("Range");
		String sql="call getNearbyUsers("+lat+","+lon+","+p+","+range+")";
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		PreparedStatement ps=null;		
		try {
			ps=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		
		ResultSet rs;
		//JSONObject userLocations=new JSONObject();
		int found=0;
		JSONArray users=new JSONArray();
		try {
			rs = ps.executeQuery(sql);
			while(rs.next())
			{
				JSONObject user=new JSONObject();
				String phone_number=rs.getString("phone_number");
				String latitude=rs.getString("latitude");
				String longitude=rs.getString("longitude");
				String distance=rs.getString("distance");
				String altitude=rs.getString("altitude");
				user.put("phone_number",phone_number);
				user.put("latitude", latitude);
				user.put("longitude",longitude);
				user.put("altitude",altitude);
				user.put("distance",distance);
				users.add(user);
			
			}
			rs.close();
			db.closeConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	/*	JSONObject user=new JSONObject();
		String phone_number="7503227437";
		String latitude="123.345678";
		String longitude="134.34567";
		String distance="100";
		String altitude="200";
		user.put("phone_number",phone_number);
		user.put("latitude", latitude);
		user.put("longitude",longitude);
		user.put("altitude",altitude);
		user.put("distance",distance);
		users.add(user);
	*/
		return users;
	}

	public static JSONArray getLocationOfContacts(JSONObject data) throws Exception{
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		Statement stmt=null;
		ResultSet rs;
		JSONArray users=new JSONArray();
		JSONArray contacts=(JSONArray)data.get("CONTACTS");
	/*	String in="(";
		int i;
		for(i=0;i<contacts.size()-1;i++)
		{
			in+=(String)contacts.get(i)+",";
		}
		in+=contacts.get(i)+")";
*///			String sql="call getNearByFriends("+data.get("latitude")+","+data.get("longitude")+","+data.get("phone_number")+","+(String)contacts.get(i)+","+data.get("range")+")";

			//String sql="call getNearbyUsers(28.7090441,77.2105401,917503678630,1)";
			Double lat=(Double)data.get("latitude");
			Double lon=(Double)data.get("longitude");
			String p=(String)data.get("phone_number");
			Long range=(Long)data.get("Range");
			String sql="call getNearbyUsers("+lat+","+lon+","+p+","+range+")";
					
			//try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					JSONObject user=new JSONObject();
					String phone_number=rs.getString("phone_number");
					String latitude=rs.getString("latitude");
					String longitude=rs.getString("longitude");
					String altitude=rs.getString("altitude");
					String distance=rs.getString("distance");
					user.put("phone_number",phone_number);
					user.put("latitude", latitude);
					user.put("longitude",longitude);
					user.put("altitude",altitude);
					user.put("distance", distance);
					users.add(user);
				}
				rs.close();
		db.closeConnection();
		System.out.print(users.toJSONString());
		return users;
	}
	public static JSONArray getAllUsersWitinRoom(JSONObject data){
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		Statement stmt=null;
		ResultSet rs;
		JSONArray users=new JSONArray();
		Double longitude1=(Double)(data.get("LATITUDE1"));
		Double latitude1=(Double)(data.get("LONGITUDE1"));
		Double longitude2=(Double)(data.get("LATITUDE2"));
		Double latitude2=(Double)(data.get("LONGITUDE2"));
		Double longitude3=(Double)(data.get("LATITUDE3"));
		Double latitude3=(Double)(data.get("LONGITUDE3"));
		Double longitude4=(Double)(data.get("LATITUDE4"));
		Double latitude4=(Double)(data.get("LONGITUDE4"));
		
		try {
			 stmt= conn.createStatement();
			 rs=stmt.executeQuery("call getUsersInRoom("+latitude1+","+latitude2+","+latitude3+","+latitude4+","+longitude1+","+longitude2+","+longitude3+","+longitude4+");");
				 

//		boolean hadResults = cStmt.execute();

	    while (rs.next()) {
	         //rs = cStmt.getResultSet();


			JSONObject user=new JSONObject();
			String phone_number=rs.getString("phone_number");
			String latitude=rs.getString("latitude");
			String longitude=rs.getString("longitude");
			String altitude=rs.getString("altitude");
			user.put("phone_number",phone_number);
			user.put("latitude", latitude);
			user.put("longitude",longitude);
			user.put("altitude",altitude);
			users.add(user);       // hadResults = cStmt.getMoreResults();
	    }
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		JSONObject tagger=new JSONObject();
		String name="tagger";
		String latitude="124.567";
		String longitude="234.567";
		String message="bla bla bla";
		String time="asdgadf";
		tagger.put("name",name);
		tagger.put("latitude", latitude);
		tagger.put("longitude",longitude);
		tagger.put("message",message);
		tagger.put("time", time);
		users.add(tagger);
    */
		db.closeConnection();
		return users;
		
		
	}
	public static JSONArray getTaggedMessages(JSONObject data){
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		Statement stmt=null;
		ResultSet rs;
		JSONArray users=new JSONArray();
		Double longitude1=(Double)(data.get("longitude"));
		Double latitude1=(Double)(data.get("latitude"));
		String phone_number=data.get("phone_number").toString();
        
		try {
			 stmt= conn.createStatement();
			 rs=stmt.executeQuery("call getTaggedMessages("+latitude1+","+longitude1+","+phone_number+");");
				 

//		boolean hadResults = cStmt.execute();

	    while (rs.next()) {
	         //rs = cStmt.getResultSet();

				JSONObject tagger=new JSONObject();
				String name=rs.getString("tagger");
				String latitude=rs.getString("latitude");
				String longitude=rs.getString("longitude");
				String message=rs.getString("message");
				//String time=rs.getString("time");
				tagger.put("name",name);
				tagger.put("latitude", latitude);
				tagger.put("longitude",longitude);
				tagger.put("message",message);
				//tagger.put("time", time);
				users.add(tagger);
		       // hadResults = cStmt.getMoreResults();
	    }
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		JSONObject tagger=new JSONObject();
		String name="tagger";
		String latitude="124.567";
		String longitude="234.567";
		String message="bla bla bla";
		String time="asdgadf";
		tagger.put("name",name);
		tagger.put("latitude", latitude);
		tagger.put("longitude",longitude);
		tagger.put("message",message);
		tagger.put("time", time);
		users.add(tagger);
    */
		db.closeConnection();
		return users;
		
	}
	public static void test(){
	
	}
	public static boolean cheackForUser(String sql){
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		Statement stmt=null;
		ResultSet rs;
		int found=0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
			rs.close();
			db.closeConnection();
			found=1;
			}else{
				rs.close();
				db.closeConnection();
				found=0;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    if(found==1){
	    	return true;
	    	}
	    else{
	    	return false;
	    }//STEP 5: Extract data from result set
	     
	}
}
