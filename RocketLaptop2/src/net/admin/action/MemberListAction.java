package net.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		List<Member> memberlist = new ArrayList<Member>();
		
		
		int page = 1; // 보여줄 page
		int limit = 5; // 한 페이지에 보여줄 상품 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		System.out.println("넘어온 limit = " + limit);
		
		int listcount = 0;
		int index = -1;
		
		String search_word = "";
		if(request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")) {
			// 총 리스트 수를 받아옵니다.
			listcount = mdao.getMemberListCount();
			memberlist = mdao.getMemberList(page, limit);
		}else{ // 검색을 클릭한 경우
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"USER_ID", "USER_NAME", "USER_GENDER", "USER_EMAIL", "USER_PHONE"};
			search_word = request.getParameter("search_word");
			listcount = mdao.getMemberListCount(search_field[index], search_word);
			memberlist = mdao.getMemberList(search_field[index], search_word, page, limit);
		}
		
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
			
		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
			
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
			
		request.setAttribute("listcount", listcount);
			
		// 해당 페이지의 상품 목록을 갖고 있는 리스트
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("limit", limit);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		forward.setRedirect(false);
			
		// 상품 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("admin/memberList.jsp");
		return forward;
	}

}
