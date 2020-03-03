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
	 * APPLICATION : ���� SESSION : ���� (WAS�� ���� ) COOKIE : ���������� PATH���� (WEB ��������
	 * �޸� Ȥ�� ���Ͽ� ����) �ð��� �������ְų� Ư���������� ����Ǿ��Ҷ�
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
			} // ����� �״�� �����Ϸ���
		} else if (op != null && op.equals("C")) {

			exp = ""; // ��Ű �����
		} else {
			exp += (value == null) ? "" : value;
			exp += (op == null) ? "" : op;
			exp += (dot == null) ? "" : dot;
		}
		Cookie expCookie = new Cookie("exp", exp);
		if (op != null && op.equals("C"))
			expCookie.setMaxAge(0); // �������� ���� �ʰ� �ٷ� �Ҹ�
		
		expCookie.setPath("/");
		resp.addCookie(expCookie);
		resp.sendRedirect("calcPage");// ������ �ش��η� �̵�

	}
}
