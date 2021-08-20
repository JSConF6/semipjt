package net.main.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.Order;
import net.main.db.OrderDAO;

public class MainOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		Order order = new Order();
		OrderDAO odao = new OrderDAO();
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String order_num = sdf.format(today);
		order_num = order_num.replace("-", "");
		
		String subNum = "";
		for(int i = 0; i < 6; i++) {
			subNum += (int)(Math.random() * 10);
		}
		
		order_num = order_num + "_" + subNum;
		String product_code = request.getParameter("product_code");
		int order_de_count = Integer.parseInt(request.getParameter("order_de_count"));
		String order_name = request.getParameter("order_name");
		String order_phone = request.getParameter("order_phone");
		String user_address1 = request.getParameter("order_address1");
		String user_address2 = request.getParameter("order_address2");
		String user_address3 = request.getParameter("order_address3");
		String order_payment = request.getParameter("order_payment");
		String user_id = request.getParameter("user_id");
		int order_totalprice = Integer.parseInt(request.getParameter("pricesum"));
		
		order.setOrder_num(order_num);
		order.setUser_id(user_id);
		order.setOrder_name(order_name);
		order.setOrder_phone(order_phone);
		order.setOrder_totalprice(order_totalprice);
		order.setUser_address1(user_address1);
		order.setUser_address2(user_address2);
		order.setUser_address3(user_address3);
		order.setOrder_payment(order_payment);
		
		int result = odao.orderInsert(order, product_code, order_de_count);
		if(result == 0) {
			System.out.println("주문 실패");
			forward.setRedirect(false);
			request.setAttribute("maintitle", "상품 주문");
			request.setAttribute("title", "상품 주문");
			request.setAttribute("body", "주문에 실패했습니다.");
			request.setAttribute("path", "MainProductDetail.ma?product_code=" + product_code);
			forward.setPath("Modal/OrderModal.jsp");
			return forward;
		}
		
		System.out.println("주문 성공");
		forward.setRedirect(false);
		request.setAttribute("maintitle", "상품 주문");
		request.setAttribute("title", "상품 주문");
		request.setAttribute("body", "주문이 완료되었습니다.");
		request.setAttribute("path", "OrderListView.ma?user_id=" + user_id);
		forward.setPath("Modal/OrderModal.jsp");
		return forward;
	}

}
