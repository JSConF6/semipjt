package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.main.db.CartDAO;
import net.main.db.CartList;

public class MainCartSearch implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CartList> cartlist = new ArrayList<CartList>();
		CartDAO cdao = new CartDAO();
		
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		String[] cartValues = request.getParameterValues("valueArr");
		
		cartlist = cdao.getCartList(user_id, cartValues);
		
		JsonObject object = new JsonObject();
		
		JsonElement je = new Gson().toJsonTree(cartlist);
		object.add("cartlist", je);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(object.toString());
		System.out.println(object.toString());
		return null;
	}

}
