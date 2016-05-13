package message;

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
 * Servlet implementation class AcceptMessage
 */
@WebServlet("/AcceptMessage")
public class AcceptMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AcceptMessage() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException{

		System.out.print("accept message post hit");
		ServletInputStream is = null;
		DataInputStream dis = null;
		String input;
		DataOutputStream writer = null;
		JSONObject status = null;
		try {
			is = request.getInputStream();
			dis = new DataInputStream(is);
			if ((input = dis.readUTF()) != null) {

				System.out.println(input);
				status = new JSONObject();
				JSONParser p = new JSONParser();
				JSONObject data = (JSONObject) p.parse(input);
				String type = (String) data.get("TYPE");

				System.out.println(type);
				if (type.equals("TAGMESSAGE")) {
					if (dao.PutData.TagMessage(data)) {
						status.put("status", 1);
						writer = new DataOutputStream(
								response.getOutputStream());
						writer.writeUTF(status.toJSONString());
						writer.flush();
						writer.close();
					} else {
						status.put("status", 0);
						writer = new DataOutputStream(
								response.getOutputStream());
						writer.writeUTF(status.toJSONString());
						writer.flush();
						writer.close();
					}
				}
				response.setStatus(HttpServletResponse.SC_OK);

			}
		} catch (Exception e) {
			status.put("status", 2);
			status.put("exception", e);
			writer = new DataOutputStream(response.getOutputStream());
			writer.writeUTF(status.toJSONString());
			writer.flush();
			writer.close();
			e.printStackTrace();
		}

	}

}
