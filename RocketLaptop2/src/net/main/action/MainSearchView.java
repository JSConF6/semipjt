package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class MainSearchView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		List<Product> searchlist = new ArrayList<Product>();
		
		int listcount = 0;
		int index = -1;
		
		index = Integer.parseInt(request.getParameter("search_field"));
		String[] search_field = new String[] {"PRODUCT_NAME", "CATEGORY_NAME"};
		String search_word = request.getParameter("search_word");

		int limit = pdao.getProductListCount(search_field[index], search_word);
		
		listcount = pdao.getProductListCount(search_field[index], search_word);
		searchlist = pdao.getProductList(search_field[index], search_word, 1, limit);
			
		request.setAttribute("listcount", listcount);
			
		// 해당 페이지의 상품 목록을 갖고 있는 리스트
		request.setAttribute("searchlist", searchlist);
		request.setAttribute("limit", limit);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main/mainSearchView.jsp");
		return forward;
	}

}
