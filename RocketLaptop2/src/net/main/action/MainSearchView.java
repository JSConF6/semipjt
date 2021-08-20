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
		
		int page = 1;
		int limit = 6;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		System.out.println("넘어온 limit = " + limit);
		
		int listcount = 0;
		int index = -1;
		
		index = Integer.parseInt(request.getParameter("search_field"));
		String[] search_field = new String[] {"PRODUCT_NAME", "CATEGORY_NAME"};
		String search_word = request.getParameter("search_word");
		
		listcount = pdao.getProductListCount(search_field[index], search_word);
		searchlist = pdao.getProductList(search_field[index], search_word, page, limit);
		
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
