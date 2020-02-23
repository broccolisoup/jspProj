package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

	/*
	 * APPLICATION : 전역 
	 * SESSION : 세션 (WAS에 저장 )
	 * COOKIE : 웹브라우저별 PATH범주  (WEB 브라우저별 메모리 혹은 파일에 저장) 시간이 정해져있거나 특정서블릿에서 실행되야할때 
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ServletContext application = req.getServletContext();
		// HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String v_ = req.getParameter("v");
		String op = req.getParameter("operator");

		int v = 0;

		if (!v_.equals(""))
			v = Integer.parseInt(v_);

		// 계산
		if (op.equals("=")) {
			// 저장소 값 가져옴
			// int x = (Integer) application.getAttribute("value");
			// int x = (Integer) session.getAttribute("value");
			int x = 0;
			for (Cookie c : cookies) {
				// Cookie c = cookies[0];
				if (c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
				break;
				}
			}
			int y = v;
			String oper = "";
			for (Cookie c : cookies) {
				// Cookie c = cookies[0];
				if (c.getName().equals("op")) {
					oper = c.getValue();
				break;
				}
			}
			int result = 0;
			// String oper = (String) application.getAttribute("op");
			//String oper = (String) session.getAttribute("op");

			
			if (oper.equals("+"))
				result = x + y;
			else
				result = x - y;

			resp.getWriter().printf("result is %d\n", result);
		
		}
		// 값을 저장
		else {
			// 저장소에 값 설정
			// application.setAttribute("value", v);
			// application.setAttribute("op", op);
			// session : 현재 접속한 사용자 별 저장소
			// 참고로 브라우저가 다르면 다른 사용자로 인식함
			// 같은브라우저 여러개 띄워도 하나의 사용자임
			// 작업표시줄 프로세스 보면 하나의 브라우저당 하나의 프로세스이고 쓰레드만 나뉘어지기 때문임

			// application : 접속자 상관없이 전체 저장소
			// session.setAttribute("value", v);W

			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");//"/" 모든 url에 쿠키 설정  "/add" 로하면 add 서비스일때만 뜸 쿠키사용
			//Cookie maxage 설정 안해주면 브라우저 닫힐때 사라짐
			//쿠키는 로컬에 in file 형식으로 저장 됨 
			// maxage(만료일자) 가 없으면 세션 종료 시 삭제 (브라우저 종료 시 ) 
			valueCookie.setMaxAge(60*60);	// 초단위임 24*60*60 =하루  60*60 = 한시간
			opCookie.setPath("/calc2"); // 어떤 서비스를 요청하든 쿠키를 설정함 
			
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
			
			resp.sendRedirect("calc2.html");// 실행후 해당경로로 이동
			//resp.
		}
		// out.println(x+y);

	}
}

