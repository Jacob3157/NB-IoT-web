package com.panhong.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.panhong.thread.UdpServerThread;

public class UdpServerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(UdpServerServlet.class);

    public void init() throws ServletException {
        
       new Thread(new UdpServerThread()).start();
        
    }
}
