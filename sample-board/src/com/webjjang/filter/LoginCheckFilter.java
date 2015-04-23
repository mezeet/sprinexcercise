package com.webjjang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class LoginCheckFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("LoginCheckFilter.doFilter()");
		HttpSession session 
		= ((HttpServletRequest)request).getSession(); 
		if(session.getAttribute("id")==null){
			((HttpServletResponse)response)
			.sendRedirect("../error/loginError.jsp");
		} else {
			chain.doFilter(request,	response);
			System.out.println("LoginCheckFilter.doFilter():After");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
