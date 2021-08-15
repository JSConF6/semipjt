package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Category;
import net.admin.db.CategoryDAO;

public class CategoryAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		CategoryDAO cdao = new CategoryDAO();
		Category category = new Category();
		boolean result = false;
		
		String category_name = request.getParameter("category_AddName");
		int categoryCnt = cdao.getCategoryListCount();
		
		category.setCategory_code("ctg_00" + categoryCnt);
		category.setCategory_name(category_name);
		
		result = cdao.categoryInsert(category);
		
		if(result == false) {
			System.out.println("카테고리 등록 실패");
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "카테고리 등록 실패");
			forward.setRedirect(false);
			return forward;
		}
		
		System.out.println("카테고리 등록 완료");
		forward.setRedirect(false);
		request.setAttribute("maintitle", "카테고리 등록");
		request.setAttribute("title", "카테고리 등록");
		request.setAttribute("body", "카테고리가 등록되었습니다.");
		request.setAttribute("path", "ProductList.ad");
		forward.setPath("Modal/SuccessModal.jsp");
		return forward;
	}

}
