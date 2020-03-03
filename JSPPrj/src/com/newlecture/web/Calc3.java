package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {

	/*
	 * APPLICATION : 전역 SESSION : 세션 (WAS에 저장 ) COOKIE : 웹브라우저별 PATH범주 (WEB 브라우저별
	 * 메모리 혹은 파일에 저장) 시간이 정해져있거나 특정서블릿에서 실행되야할때
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ServletContext application = req.getServletContext();
		// HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();

		String value = req.getParameter("value");
		String op = req.getParameter("operator");
		String dot = req.getParameter("dot");

		String exp = "";
		if (cookies != null) {
			for (Cookie c : cookies) {
				// Cookie c = cookies[0];
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		if (op != null && op.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 연산식 그대로 실행하려구
		} else if (op != null && op.equals("C")) {

			exp = ""; // 쿠키 지우기
		} else {
			exp += (value == null) ? "" : value;
			exp += (op == null) ? "" : op;
			exp += (dot == null) ? "" : dot;
		}
		Cookie expCookie = new Cookie("exp", exp);
		if (op != null && op.equals("C"))
			expCookie.setMaxAge(0); // 브라우저에 남지 않고 바로 소멸
		
		expCookie.setPath("/");
		resp.addCookie(expCookie);
		resp.sendRedirect("calcPage");// 실행후 해당경로로 이동

	}
}
