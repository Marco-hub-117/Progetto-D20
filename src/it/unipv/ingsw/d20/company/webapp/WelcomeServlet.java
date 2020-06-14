package it.unipv.ingsw.d20.company.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

public class WelcomeServlet extends HttpServlet {
	
	private Operator loggedOperator;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/add_operator")) {
			resp.getWriter().write(Rythm.render("operatorForm.rtm"));
		}
		else if (req.getPathInfo().equals("/operators")) {
			resp.getWriter().write(Rythm.render("operatorsTable.rtm", Operators.all()));
		}
		else if (req.getPathInfo().equals("/report_confirmed")) {
			resp.getWriter().write(Rythm.render("reportConfirmed.rtm"));
		}
		else if (req.getPathInfo().equals("/report")) {
			resp.getWriter().write(Rythm.render("vendingReport.rtm", Operators.all()));
		}
		else if (req.getPathInfo().equals("/settings")) {
			Vending vending = Vendings.get(Integer.parseInt((req.getParameter("id"))));
			resp.getWriter().write(Rythm.render("vendingSettings.rtm", vending, Vendings.all()));
		}
		else if (req.getPathInfo().equals("/add_vending")) {
			resp.getWriter().write(Rythm.render("vendingForm.rtm"));
		}
		else if (req.getPathInfo().equals("/vendings")) {
			resp.getWriter().write(Rythm.render("vendingsTable.rtm", Vendings.all()));
		}
		else if (req.getPathInfo().equals("/select")) {
			resp.getWriter().write(Rythm.render("selectOp.rtm", loggedOperator));
		}
		else if (req.getPathInfo().equals("/goodbye")) {
			resp.getWriter().write(Rythm.render("goodbye.rtm"));
		}
		else {
			resp.getWriter().write(Rythm.render("login.rtm"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/save_vending")) {
			Vendings.add(Integer.parseInt(req.getParameter("id")), req.getParameter("location"), req.getParameter("status"), req.getParameter("type"));
			resp.sendRedirect("/vendings");
		} 
		else if (req.getPathInfo().equals("/save_operator")) {
			Operators.add(req.getParameter("first_name")+" "+req.getParameter("last_name"), req.getParameter("username"), req.getParameter("password"),  req.getParameter("type"));
			resp.sendRedirect("/operators");
		}
		else if (req.getPathInfo().equals("/send_report")) {
			System.out.println("AIUTOOO");
			resp.sendRedirect("/report_confirmed");
		} 
		else if (req.getPathInfo().equals("/try_login")) {
		    try {   	
			Operator operator=Operators.getMy(req.getParameter("username"));
			loggedOperator=operator.checkLogIn(req.getParameter("username"), req.getParameter("inputPassword"));
			
		    }
		    catch (NullPointerException e) {
		    	System.out.println("Invalid Operator Username");
		    }
		    catch (InvalidPasswordException ep) {
		    	System.out.println("Invalid Password");
		    }
		    resp.sendRedirect("/select");
		}
		else if (req.getPathInfo().equals("/logout")) {
			loggedOperator=null;
			resp.sendRedirect("/goodbye");
		} 
		resp.sendRedirect("/");
	}
}
