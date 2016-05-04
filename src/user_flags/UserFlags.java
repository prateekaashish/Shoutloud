package user_flags;
import simple.*;
public class UserFlags {

	private static final Object countLock = new Object();
	public static JSONObject users=new JSONObject();
	
    public void incrementCount() {
        synchronized (countLock) {
        }
    }

	public static void addUser(String phonenumber){
		synchronized (countLock) {
        users.put(phonenumber, new JSONObject());
		}
	 }
	public static void deleteUser(String phonenumber){
		synchronized (countLock) {
        users.remove(phonenumber);
		}
	}

	public static void modifyFlag(String phonenumber,String flag,boolean value){
		synchronized(countLock){
			if((JSONObject)users.get(phonenumber)==null){
				users.put(phonenumber, new JSONObject());
			}else
			{
				JSONObject user=(JSONObject)users.get(phonenumber);
				if(user.get(flag)!=null)
				user.replace(flag, value);
				else
					user.put(flag, value);
			}
		}
	}
	public static boolean checkFlag(String phonenumber,String flag){
		synchronized(countLock){
			JSONObject user=(JSONObject)users.get(phonenumber);
			return (boolean)user.get(flag);
		}
	}
}
