package net.member.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.main.action.Action;
import net.main.action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String realFolder ="";
		
		//WebContent 아래에 꼭 폴더 생성하세요
		String saveFolder = "memberupload";
		
		int fileSize=5*1024*1024;// 업로드 할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		try {
			MultipartRequest multi
			=new MultipartRequest(request,realFolder,fileSize,"utf-8",
					new DefaultFileRenamePolicy());

			String user_id 	= multi.getParameter("user_id");
			String user_name = multi.getParameter("user_name");
			String user_gender = multi.getParameter("user_gender");
			String user_email = multi.getParameter("user_email");
			String user_phone = multi.getParameter("user_phone");
			String user_address1 = multi.getParameter("user_address1");
			String user_address2 = multi.getParameter("user_address2");
			
			Member m = new Member();
			m.setUser_id(user_id);
			m.setUser_name(user_name);
			m.setUser_gender(user_gender);
			m.setUser_email(user_email);
			m.setUser_phone(user_phone);
			m.setUser_address1(user_address1);
			m.setUser_address2(user_address2);
			
			String check = multi.getParameter("check");
			System.out.println("check" + check);
			if(check != null) {
				m.setUser_memberfile(check);
			}else {
				String memberfile = multi.getFilesystemName("memberfile");
				m.setUser_memberfile(memberfile);
			}
			MemberDAO mdao = new MemberDAO();
			int result = mdao.memberUpdate(m);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			
			if (result == 1) { // 삽입이 된 경우
				out.println("alert('수정되었습니다.');");
				out.println("location.href='main.ma';");
			} else {
				out.println("alert('회원 정보 수정에 실패했습니다.');");
				out.println("history.back()");// 비밀번호를 제외한 다른 데이터는 유지되어 있습니다.
			}
			out.println("</script>");
			out.close();
			return null;
		} catch (IOException ex) {
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		} //catch end
	}//execute end
}