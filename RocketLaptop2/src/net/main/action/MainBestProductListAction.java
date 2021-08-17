package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class MainBestProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		List<Product> bestlist = new ArrayList<Product>();
		
		int listcount = 0;
		int index = -1;

		int limit = pdao.bestProductListCount();
		
		listcount = pdao.bestProductListCount();
		bestlist = pdao.bestProductList(1, limit);
			
		request.setAttribute("listcount", listcount);
			
		// 해당 페이지의 상품 목록을 갖고 있는 리스트
		request.setAttribute("bestlist", bestlist);
		request.setAttribute("limit", limit);
		request.setAttribute("search_field", index);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main/mainBestProductView.jsp");
		return forward;
	}

}
