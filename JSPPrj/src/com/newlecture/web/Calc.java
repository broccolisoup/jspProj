package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		// filter를 만들면 이부분이 필요가 없음 req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		Integer x = Integer.parseInt(req.getParameter("x"));
		Integer y = Integer.parseInt(req.getParameter("y"));
		String op = req.getParameter("operator");
		int result = 0;
		if(op.equals("덧셈"))
			result = x+y;
		else
			result = x-y;
		resp.getWriter().printf("result is %d\n", result);

		//out.println(x+y);
		
	}
}
