package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Product;
import net.admin.db.ProductDAO;

public class MainCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO pdao = new ProductDAO();
		List<Product> categoryproductlist = new ArrayList<Product>();
		
		int listcount = 0;
		int index = -1;
		
		String search_field = "category_name";
		String category_name = request.getParameter("category_name");
		listcount = pdao.getProductListCount(search_field, category_name);
		categoryproductlist = pdao.getProductList(search_field, category_name);

		request.setAttribute("listcount", listcount);
			
		// 해당 페이지의 상품 목록을 갖고 있는 리스트
		request.setAttribute("categoryproductlist", categoryproductlist);
		request.setAttribute("search_field", index);
		request.setAttribute("category_name", category_name);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
			
		// 상품 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("main/mainCategoryProductListView.jsp");
		return forward;
	}

}
