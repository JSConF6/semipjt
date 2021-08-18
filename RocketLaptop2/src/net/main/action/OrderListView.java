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
		ActionForward forward = new ActionForward();
		OrderDAO odao = new OrderDAO();
		List<Order> orderlist = new ArrayList<Order>();
		
		String user_id = request.getParameter("user_id");
		
		int listcount = odao.getOrderListCount(user_id);
		orderlist = odao.getOrderList(user_id);
		
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("listcount", listcount);
		
		forward.setRedirect(false);
		forward.setPath("main/orderListView.jsp");
		return forward;
	}

}
