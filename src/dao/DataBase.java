package dao;
import java.sql.*;

//import com.mysql.jdbc.PreparedStatement;

public class DataBase {
	
	  static String URL = "jdbc:mysql://localhost:3306/new?noAccessToProcedureBodies=true";
      static String USER = "java";
      static String PASSWORD = "password";
      static String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
      public Connection connection;
    static
    {
    	try {
            //Step 2: Load MySQL Java driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public DataBase()
    {
    	connection=null;
    	openConnection();
    	System.out.print(connection);
    }
	public  Connection openConnection()
	{
		   
	        try {
	            //Step 3: Establish Java MySQL connection
	        	if(connection==null||connection.isClosed()==true)
	        	{
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        	}
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	        return connection; 
	}
	public void closeConnection()
	{
		try{
			
			if(connection.isClosed()==false)
				connection.close();
		}catch(Exception e)
		{
			
			System.out.println(e);
		}
		
	}
	
	/*
	public int insertMessage(String sender,String receiver,String message,String status)
	{
		
		openConnection();
		String sql="insert into messages (sender,receiver,message,status) values(?,?,?,?);";
		try{
		PreparedStatement ps=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
		//System.out.println("1");
		ps.setString(1,sender);
		ps.setString(2, receiver);
		ps.setString(3, message);
		ps.setInt(4,Integer.parseInt(status));
		int s=ps.executeUpdate();
		ps.close();
		closeConnection();
		return s;
		}catch(Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}
	public JSONArray getOnlineUsers(String user) throws Exception
 	{
		openConnection();
		ResultSet rs;
		//String sql="select user_id,datetime,status from activeusers where status=?";
		String sql="select user_id,datetime,status from activeusers where status=? and (user_id in (select userone from userfriends where usertwo=? and status='friends') or user_id in (select usertwo from userfriends where userone=? and status='friends'))";
		
		
		PreparedStatement ps=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ps.setInt(1, 1);
		ps.setString(2, user);
		ps.setString(3,user);
		rs=ps.executeQuery();
		JSONArray jarray=new JSONArray();
        while(rs.next())
		{
        	String message=rs.getString("user_id");
        	jarray.add(message);
            
		}
        ps.close();
		connection.close();
		System.out.println(jarray.toString());
		return jarray;
		
	}
	public int updateOnlineStatus(String user) throws Exception
	{
		openConnection();
		String sql="UPDATE activeusers SET status='1',datetime=now() WHERE user_id=?" ;
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1,user);
		int s=ps.executeUpdate();
		System.out.print(s);
		return s;
	}
	public JSONArray getUnreadMessage(String receiver) throws Exception
	{
		openConnection();
		ResultSet rs;
		String sql="select id,sender,receiver,message,datetime,status from messages where receiver=? and status='0'";
		PreparedStatement ps=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ps.setString(1,receiver);
		rs=ps.executeQuery();
		JSONArray jarray=new JSONArray();
        while(rs.next())
		{
			rs.updateInt("status", 1);
			JSONObject message=new JSONObject();
			message.put("sender",rs.getString("sender"));
			message.put("receiver",rs.getString("receiver"));
			message.put("message", rs.getString("message"));
			message.put("timestamp",rs.getTimestamp("datetime").toString());
            jarray.add(message);
            rs.updateRow();
            System.out.println("inside re.next");
		}
        ps.close();
		connection.close();
		return jarray;
	}
	public JSONArray getAllMessage(String receiver,String sender,String timestamp) throws Exception
	{
		openConnection();
		ResultSet rs;
		String sql="select id,sender,receiver,message,datetime,status from messages where ((receiver=? and sender=?) or (receiver=? and sender=?)) and (datetime <= ?) ORDER BY id DESC limit 10";
		PreparedStatement ps=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ps.setString(1,receiver);
		ps.setString(2, sender);
		ps.setString(3,sender);
		ps.setString(4, receiver);
		Timestamp ts = Timestamp.valueOf(timestamp);
		System.out.println(ts);
		ps.setTimestamp(5, ts);
		rs=ps.executeQuery();
		JSONArray jarray=new JSONArray();
        while(rs.next())
		{
			rs.updateInt("status", 1);
			JSONObject message=new JSONObject();
			message.put("sender",rs.getString("sender"));
			message.put("receiver", rs.getString("receiver"));
			message.put("message", rs.getString("message"));
			message.put("timestamp",rs.getTimestamp("datetime").toString());
            jarray.add(message);
            rs.updateRow();
            System.out.println("inside re.next");
		}
        ps.close();
		connection.close();
		return jarray;
	}
	public JSONArray getAllFriends(String user)
	{
		openConnection();
		ResultSet rs;
		//String sql="select user_id from activeusers";
		String sql="select user_id from activeusers where user_id in (select userone from userfriends where usertwo=?  and status='friends') or user_id in (select usertwo from userfriends where userone=?  and status='friends')";
		
		JSONArray jarray=new JSONArray();
        
		try{
		PreparedStatement ps=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ps.setString(1, user);
		ps.setString(2,user);
		rs=ps.executeQuery();
		while(rs.next())
		{
			jarray.add(rs.getString("user_id"));
		}
        ps.close();
		connection.close();
		
		}catch(Exception e)
		{
			System.out.println("exception in getallfriends"+e);
		}
		return jarray;
	}*/
	
}
