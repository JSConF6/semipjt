package net.member.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.main.action.Action;
import net.main.action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");

		System.out.println("user_id= " + user_id);
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.getMemberDetail(user_id);

		String saveFolder = "memberupload";
		ServletContext sc = request.getServletContext();
		String realFolder = sc.getRealPath(saveFolder);

		System.out.println("realFolder= " + realFolder);
		System.out.println("getMemberfile= " + m == null);
		System.out.println("getMemberfile= " + m.getUser_memberfile());
		File file = new File(realFolder + "/" + m.getUser_memberfile());
		if (file.isFile()) {
			System.out.println("realFolder= ");
		} else {

			System.out.println("r= ");
			m.setUser_memberfile("");
		}
		 response.setContentType("text/html; charset=utf-8");
		 Gson gson = new Gson();
		 String json = gson.toJson(m);
		 PrintWriter out = response.getWriter();
		 out.print(json);
	     out.flush();
	     out.close();
		//forward.setPath("main/joinForm.jsp");
		//forward.setRedirect(false);
		//request.setAttribute("memberinfo", m);
		return null;
	}

}
