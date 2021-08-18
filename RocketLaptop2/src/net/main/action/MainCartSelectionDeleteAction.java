package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.CartDAO;

public class MainCartSelectionDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ajaxArr = request.getParameterValues("valueArr");
		String user_id = request.getParameter("user_id");
		CartDAO cdao = new CartDAO();
		
		int result = cdao.CartSelectionDelete(ajaxArr, user_id);
		
		System.out.println("result = " + result);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
		return null;
	}

}
