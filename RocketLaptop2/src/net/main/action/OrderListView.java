package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.Order;
import net.main.db.OrderDAO;

public class OrderListView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDAO odao = new OrderDAO();
		List<Order> orderlist = new ArrayList<Order>();
		
		String user_id = request.getParameter("user_id");
		if(user_id.equals("")) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("Modal/OrderErModal.jsp");
			return forward;
		}
		
		int page = 1;
		int limit = 5;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		System.out.println("넘어온 limit = " + limit);
		
		int index = -1;
		int listcount = odao.getOrderListCount(user_id);
		orderlist = odao.getOrderList(user_id, page, limit);
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수  = " + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
			
		request.setAttribute("startpage", startpage);
			
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("listcount", listcount);
		request.setAttribute("limit", limit);
		request.setAttribute("search_field", index);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main/orderListView.jsp");
		return forward;
	}

}
