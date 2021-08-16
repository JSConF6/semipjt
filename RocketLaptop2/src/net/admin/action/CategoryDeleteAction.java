package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.CategoryDAO;

public class CategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		CategoryDAO cdao = new CategoryDAO();
		boolean result = false;
		
		String category_code = request.getParameter("category_code");
		
		result = cdao.categoryDelete(category_code);
		
		if(result == false) {
			System.out.println("카테고리 삭제 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("카테고리 삭제 성공");
		forward.setRedirect(false);
		request.setAttribute("maintitle", "카테고리 삭제");
		request.setAttribute("title", "카테고리 삭제");
		request.setAttribute("body", "카테고리가 삭제되었습니다.");
		request.setAttribute("path", "ProductList.ad");
		forward.setPath("Modal/SuccessModal.jsp");
		return forward;
	}

}
