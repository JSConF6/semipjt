package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Notice;
import net.admin.db.NoticeDAO;

public class MainNoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
		List<Notice> noticelist = new ArrayList<Notice>();
		
		
		int page = 1; // 보여줄 page
		int limit = 10; // 한 페이지에 보여줄 공지사항 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		System.out.println("넘어온 limit = " + limit);
		
		int listcount = 0;
	
		listcount = ndao.getNoticeListCount();
		noticelist = ndao.getNoticeList(page, limit);
		
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
			
		// 해당 페이지의 공지사항 목록을 갖고 있는 리스트
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("limit", limit);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
			
		// 공지사항 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("main/mainNoticeList.jsp");
		return forward;
	}

}
