package br.com.ping;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.net.www.content.text.plain;

/**
 * Servlet implementation class PingServlet
 */
@WebServlet("/PingServlet")
public class PingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String valorIP = request.getParameter("endereco");
		int numeroPing = Integer.parseInt(request.getParameter("numero"));

		response.getWriter().println("TESTE!!!!");

		Thread t = null;

	
			for (int i = 0; i < numeroPing; i++) {

				Ping p = new Ping();

				p.endereco = valorIP;
				t = new Thread(p);
				t.start();

			}

			synchronized (t) {
				try{
					response.getWriter().println("Aguardando completar...");
	                  t.wait();
	              }catch(InterruptedException e){
	                  e.printStackTrace();
	              }
			}
		
			
		for (String string : Ping.ListaPing) {

			response.getWriter().println(string);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
