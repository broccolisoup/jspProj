package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		
		String[] num_ =req.getParameterValues("num");
		
		int result = 0; 
		
		for (int i = 0; i < num_.length; i++) {
			//for���ȿ����� ������ �ݺ������� ������ �ݺ����� �����Ƿ� �ȿ��� �����ϴ°� �� ���� 
			int num = Integer.parseInt(num_[i]);
			result += num;
		}
	//	Integer x = Integer.parseInt(req.getParameter("x"));
	//	Integer y = Integer.parseInt(req.getParameter("y"));
		
		resp.getWriter().printf("result id %d\n", result);

		//out.println(x+y);
		
	}
}
