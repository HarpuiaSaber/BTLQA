package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/mode-configuration")
public class ModeConfigurationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer mode = StringUtils.convertToInteger(req.getParameter("mode"));
		String jspPage = "/error/Error.jsp";
		if (mode != null) {
			if (mode == 1) {
				jspPage = "/admin/configuration/configuration-mode1.jsp";
			} else if (mode == 2) {
				jspPage = "/admin/configuration/configuration-mode2.jsp";
			}
		} 
		RequestDispatcher dispatcher = req.getRequestDispatcher(jspPage);
		dispatcher.forward(req, resp);
	}

}
