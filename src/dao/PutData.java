package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import simple.JSONObject;

public class PutData {

	public static boolean UpdateLocation(String sql) throws Exception{
		boolean sucessfull;
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		Statement stmt;
			stmt = conn.createStatement();
			if(stmt.executeUpdate(sql)>=1){
				sucessfull=true;
			}else{
				sucessfull= false;
			}
	return sucessfull;
	}
	public static void register (JSONObject info)throws Exception{
		String first_name=(String)(info.get("first_name"));
		String last_name=(String)(info.get("last_name"));
		String email=(String)(info.get("email"));
		String address=(String)(info.get("address"));
		String user_name=(String)(info.get("user_name"));
		String password=(String)(info.get("password"));
		String phone_number=(String)info.get("phone_number");
		DataBase db=new DataBase();
		Connection conn=db.openConnection();
		 PreparedStatement updateemp;
				updateemp = conn.prepareStatement
					      ("insert into user_info (first_name,lastname,username,password,email,phone_number,address) VALUES(?,?,?,?,?,?,?)");
			      updateemp.setString(1,first_name);
			      updateemp.setString(2,last_name);
			      updateemp.setString(3,user_name);
			      updateemp.setString(4,password);
			      updateemp.setString(5,email);
			      updateemp.setString(6,phone_number);
			      updateemp.setString(7,address);
			      updateemp.executeUpdate();
		
			
		
	}
	public static boolean TagMessage(JSONObject message) throws Exception{
		boolean sucessfull;
		String phone_number=(String)message.get("phone_number");
		String m=(String)message.get("message");
		String distance=(String)message.get("distance");
		Long timecoming=Long.parseLong((String)message.get("time"));
		String longitude=(String)(message.get("longitude")+"");
		String latitude=(String)(message.get("latitude")+"");
		long unixTimestamp = Instant.now().getEpochSecond();
		long time=timecoming+unixTimestamp;
		DataBase db=new DataBase();
			Connection conn=db.openConnection();
			 PreparedStatement updateemp;
				updateemp = conn.prepareStatement
					      ("insert into tagged_messages (TAGGER,MESSAGE,LONGITUDE,LATITUDE,DISTANCE,TIME) VALUES(?,?,?,?,?,?)");
				updateemp.setString(1,phone_number);
			      updateemp.setString(2,m);
			      updateemp.setString(3,longitude);
			      updateemp.setString(4,latitude);
			      updateemp.setString(5,distance);
			      updateemp.setString(6,time+"");
			     
			      if(updateemp.executeUpdate()>=1){
			    	  sucessfull=true;
			      }else{
			    	  sucessfull=false;
			      }
			      conn.close();
			      return sucessfull;
		}
}
