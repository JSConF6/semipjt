package net.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.main.db.Order;
import net.main.db.OrderDAO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> orderlist = new ArrayList<Order>();
		OrderDAO odao = new OrderDAO();
		
		int page = 1; // 보여줄 page
		int limit = 5; // 한 페이지에 보여줄 상품 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		System.out.println("넘어온 limit = " + limit);
		
		int listcount = 0;
		int index = -1;
		int delivery = 0;
		
		String search_word = "";
		if(request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")) {
			if(request.getParameter("order_delivery") == null
					|| request.getParameter("order_delivery").equals("")) {
				listcount = odao.getOrderListCount();
				orderlist = odao.getOrderList(page, limit);
			}else {
				delivery = Integer.parseInt(request.getParameter("order_delivery"));
				String[] order_delivery = new String[] {"배송준비", "배송중", "배송완료"};
				listcount = odao.getOrderDeliveryListCount(order_delivery[delivery]);
				orderlist = odao.getOrderDeliveryList(order_delivery[delivery], page, limit);
			}
		}else{// 검색을 클릭한 경우
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"ORDER_NUM", "ORDER_NAME", "ORDER_PAYMENT"};
			search_word = request.getParameter("search_word");
			listcount = odao.getOrderListCount(search_field[index], search_word);
			orderlist = odao.getOrderList(search_field[index], search_word, page, limit);
		}
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수  = " + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		String state = request.getParameter("state");
		
		if(state == null) {
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage);
				
			// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);
				
			// 현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);
				
			request.setAttribute("listcount", listcount);
				
			// 해당 페이지의 상품 목록을 갖고 있는 리스트
			request.setAttribute("orderlist", orderlist);
			request.setAttribute("limit", limit);
			request.setAttribute("search_field", index);
			request.setAttribute("search_word", search_word);
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("admin/orderList.jsp");
			return forward;
		}else {
			System.out.println("ajax = " + state);
			
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("delivery", delivery);
			object.addProperty("search_field", index);
			object.addProperty("search_word", search_word);
			
			JsonElement je = new Gson().toJsonTree(orderlist);
			System.out.println("orderlist=" + je.toString());
			object.add("orderlist", je);
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append(object.toString());
			System.out.println(object.toString());
			return null;
		}
	}

}
