package net.main.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class MainProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		List<Product> bestlist = new ArrayList<Product>();
		List<Product> newlist = new ArrayList<Product>();
		
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String Today = sdf.format(cal.getTime());
		
		cal.add(Calendar.DATE, 1);
		String Tomorrow = sdf.format(cal.getTime());
		
		newlist = pdao.newProductList(Today, Tomorrow);
		bestlist = pdao.bestProductList();
		
		JsonObject object = new JsonObject();
		
		JsonElement je = new Gson().toJsonTree(bestlist);
		System.out.println("bestlist = " + je.toString());
		object.add("bestlist", je);
		
		JsonElement je2 = new Gson().toJsonTree(newlist);
		System.out.println("newlist = " + je2.toString());
		object.add("newlist", je2);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(object.toString());
		System.out.println(object.toString());
		return null;
	}

}
