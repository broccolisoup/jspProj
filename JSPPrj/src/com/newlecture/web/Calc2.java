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
	 * APPLICATION : ���� 
	 * SESSION : ���� (WAS�� ���� )
	 * COOKIE : ���������� PATH����  (WEB �������� �޸� Ȥ�� ���Ͽ� ����) �ð��� �������ְų� Ư���������� ����Ǿ��Ҷ� 
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

		// ���
		if (op.equals("=")) {
			// ����� �� ������
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
		// ���� ����
		else {
			// ����ҿ� �� ����
			// application.setAttribute("value", v);
			// application.setAttribute("op", op);
			// session : ���� ������ ����� �� �����
			// ����� �������� �ٸ��� �ٸ� ����ڷ� �ν���
			// ���������� ������ ����� �ϳ��� �������
			// �۾�ǥ���� ���μ��� ���� �ϳ��� �������� �ϳ��� ���μ����̰� �����常 ���������� ������

			// application : ������ ������� ��ü �����
			// session.setAttribute("value", v);W

			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");//"/" ��� url�� ��Ű ����  "/add" ���ϸ� add �����϶��� �� ��Ű���
			//Cookie maxage ���� �����ָ� ������ ������ �����
			//��Ű�� ���ÿ� in file �������� ���� �� 
			// maxage(��������) �� ������ ���� ���� �� ���� (������ ���� �� ) 
			valueCookie.setMaxAge(60*60);	// �ʴ����� 24*60*60 =�Ϸ�  60*60 = �ѽð�
			opCookie.setPath("/calc2"); // � ���񽺸� ��û�ϵ� ��Ű�� ������ 
			
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
			
			resp.sendRedirect("calc2.html");// ������ �ش��η� �̵�
			//resp.
		}
		// out.println(x+y);

	}
}

