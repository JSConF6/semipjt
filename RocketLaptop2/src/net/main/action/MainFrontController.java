package net.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.MemberDeleteAction;
import net.member.action.MemberIdCheckAction;
import net.member.action.MemberInfoAction;
import net.member.action.MemberJoinProcessAction;
import net.member.action.MemberLoginAction;
import net.member.action.MemberLoginProcessAction;
import net.member.action.MemberLogoutAction;
import net.member.action.MemberSearchAction;
import net.member.action.MemberUpdateAction;
import net.member.action.MemberUpdateProcessAction;

@WebServlet("*.ma")
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 요청된 전체 URI중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
		 예) http://localhost:8088/JspProject/login.net인 경우
		 	"/JspProject/login.net" 반환됩니다.
		 */
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		// 마지막 위치 문자까지 추출합니다.
		// command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		// 초기화
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
			case "/main.ma":
				action = new MainAction();
				break;
			case "/MainProductList.ma":
				action = new MainProductListAction();
				break;
			case "/MainNewProductList.ma":
				action = new MainNewProductListAction();
				break;
			case "/MainBestProductList.ma":
				action = new MainBestProductListAction();
				break;
			case "/MainProductDetail.ma":
				action = new MainProductDetailView();
				break;
			case "/OrderAction.ma":
				action = new OrderAction();
				break;
			case "/OrderListView.ma":
				action = new OrderListView();
				break;
			case "/OrderDetailView.ma":
				action = new OrderDetailView();
				break;
			case "/MainSearch.ma":
				action = new MainSearchView();
				break;
			case "/MainCategoryProductList.ma":
				action = new MainCategoryListAction();
				break;
			case "/MainNoticeList.ma":
				action = new MainNoticeListAction();
				break;
			case "/MainNoticeDetail.ma":
				action = new MainNoticeDetailAction();
				break;
			case "/QnaList.ma":
				action = new QnaListAction();
				break;
			case "/QnaDetail.ma":
				action = new QnaDetailAction();
				break;
			case "/QnaWrite.ma":
				action = new QnaWriteAction();
				break;
			case "/QnaAddAction.ma":
				action = new QnaAddAction();
				break;
			case "/QnaDetailAction.ma":
				action = new QnaDetailAction();
				break;	
			case "/QnaReplyView.ma":
				action = new QnaReplyView();
				break;	
			case "/QnaReplyAction.ma":
				action = new QnaReplyAction();
				break;	
			case "/QnaModifyView.ma":
				action = new QnaModifyView();
				break;
			case "/QnaModifyAction.ma":
				action = new QnaModifyAction();
				break;
			case "/QnaDeleteAction.ma":
				action = new QnaDeleteAction();
				break;
			case "/QnaFileDown.ma":
				action = new QnaFileDownAction();
				break;	
			case "/CommentAdd.ma":
				action = new CommentAdd();
				break;
			case "/CommentList.ma":
				action = new CommentList();
				break;
			case "/CommentDelete.ma":
				action = new CommentDelete();
				break;
			case "/CommentUpdate.ma":
				action = new CommentUpdate();
				break;
			case "/CommentReply.ma":
				action = new CommentReply();
				break;	
			case "/MainCartView.ma":
				action = new MainCartView();
				break;
			case "/MainCartAction.ma":
				action = new MainCartAction();
				break;
			case "/MainCartDeleteAction.ma":
				action = new MainCartDeleteAction();
				break;
			case "/MainCartSelectionDelete.ma":
				action = new MainCartSelectionDeleteAction();
				break;
			case "/joinProcess.ma":
				action = new MemberJoinProcessAction();
				break;	
			case "/idcheck.ma":				
				action = new MemberIdCheckAction();
				break;	
			case "/logout.ma":
				action = new MemberLogoutAction();
				break;	
			case "/login.ma":
				action = new MemberLoginAction();
				break;	
			case "/loginProcess.ma":
				action = new MemberLoginProcessAction();
				break;
			case "/FindPasswordAction.ma":
				action = new MemberFindPasswordAction();
				break;
			case "/memberUpdate.ma":
				action = new MemberUpdateAction();
				break;
			case "/updateProcess.ma":
				action = new MemberUpdateProcessAction();
				break;
			case "/memberDelete.ma":						
				action = new MemberDeleteAction();
				break;
			case "/memberList.ma":
				action = new MemberSearchAction();
				break;
			case "/memberInfo.ma":
				action = new MemberInfoAction();
				break;
		} // switch end
		
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { // 리다이렉트 합니다.
				response.sendRedirect(forward.getPath());
			}else { // 포워딩됩니다.
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			} // else
		} // forward
	} // doProcess
    
    // doProcess(request, response)메서드를 구현하여 요청이 GET방식이든
    // POST방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
