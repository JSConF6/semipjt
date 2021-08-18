package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.Order;
import net.main.db.OrderDAO;
import net.main.db.OrderList;

public class OrderDetailView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		List<OrderList> orderlist = new ArrayList<OrderList>();
		Order order = new Order();
		
		OrderDAO odao = new OrderDAO();
		
		String order_num = request.getParameter("order_num");
		String user_id = request.getParameter("user_id");
		
		order = odao.getOrder(order_num);
		orderlist = odao.getOrderDetailList(order_num, user_id);
		
		request.setAttribute("o", order);
		request.setAttribute("orderlist", orderlist);
		
		forward.setRedirect(false);
		forward.setPath("main/orderDetailView.jsp");
		return forward;
	}

}
