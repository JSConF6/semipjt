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

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class MainNewProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		List<Product> newlist = new ArrayList<Product>();
		
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String Today = sdf.format(cal.getTime());
		
		cal.add(Calendar.DATE, 1);
		String Tomorrow = sdf.format(cal.getTime());
		
		int listcount = 0;
		int index = -1;

		int limit = pdao.newProductListCount();
		
		listcount = pdao.newProductListCount();
		newlist = pdao.newProductList(Today, Tomorrow);
			
		request.setAttribute("listcount", listcount);
			
		// 해당 페이지의 상품 목록을 갖고 있는 리스트
		request.setAttribute("newlist", newlist);
		request.setAttribute("limit", limit);
		request.setAttribute("search_field", index);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main/mainNewProductView.jsp");
		return forward;
	}

}
