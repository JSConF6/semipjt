package net.member.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.Member;
import net.member.db.MemberDAO;

import net.main.action.Action;
import net.main.action.ActionForward;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String realFolder = "";

		// WebContent 아래에 꼭 폴더 생성하세요
		String saveFolder = "memberupload";

		int fileSize = 5 * 1024 * 1024;// 업로드 할 파일의 최대 사이즈 입니다. 5MB

		// 실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());

			String user_id = multi.getParameter("user_id");
			String user_password = multi.getParameter("user_password");
			String user_name = multi.getParameter("user_name");
			int user_birthdate = Integer.parseInt(multi.getParameter("user_birthdate"));
			String user_gender = multi.getParameter("user_gender");
			String user_email = multi.getParameter("user_email");
			String user_phone = multi.getParameter("user_phone");
			String user_address1 = multi.getParameter("user_address1");
			String user_address2 = multi.getParameter("user_address2");

			Member m = new Member();
			m.setUser_id(user_id);
			m.setUser_password(user_password);
			m.setUser_name(user_name);
			m.setUser_birthdate(user_birthdate);
			m.setUser_gender(user_gender);
			m.setUser_email(user_email);
			m.setUser_phone(user_phone);
			m.setUser_address1(user_address1);
			m.setUser_address2(user_address2);

			String user_memberfile = multi.getFilesystemName("user_memberfile");
			if(user_memberfile != null) {
				System.out.println(user_memberfile);
				m.setUser_memberfile(user_memberfile);
			}else {
				System.out.println(user_memberfile);
				m.setUser_memberfile("profile.png");
			}
			
			MemberDAO mdao = new MemberDAO();
			int result = mdao.memberInsert(m);

			if (result == -1) {
				forward.setRedirect(false);
				request.setAttribute("maintitle", "회원가입");
				request.setAttribute("title", "회원가입");
				request.setAttribute("body", "아이디가 중복되었습니다. 다시 입력하세요.");
				request.setAttribute("path", "main.ma");
				forward.setPath("Modal/SuccessModal.jsp");
			}else if(result == 0) {
				forward.setRedirect(false);
				request.setAttribute("maintitle", "회원가입");
				request.setAttribute("title", "회원가입");
				request.setAttribute("body", "회원가입 실패");
				request.setAttribute("path", "main.ma");
				forward.setPath("Modal/SuccessModal.jsp");
			}
			
			forward.setRedirect(false);
			request.setAttribute("maintitle", "회원가입");
			request.setAttribute("title", "회원가입");
			request.setAttribute("body", "회원가입을 축하합니다.");
			request.setAttribute("path", "main.ma");
			forward.setPath("Modal/SuccessModal.jsp");
			return forward;
		} catch (IOException ex) {
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}
}
