package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Nana  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		//int cnt = Integer.parseInt(req.getParameter("cnt"));
		
		String temp = req.getParameter("cnt");
		int cnt = 15; 
		if(temp != null && !temp.equals(""))
			cnt = Integer.parseInt(temp);
		for (int i = 0; i < cnt; i++) {
			out.println(i+1+" : ¾È³çHello~~aa<br>");
		}
		
	}
}
