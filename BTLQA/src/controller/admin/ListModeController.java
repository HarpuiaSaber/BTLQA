package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AfterDeathModeService;
import service.DeathModeService;
import service.RetirementModeService;
import service.impl.AfterDeathModeServiceImpl;
import service.impl.DeathModeServiceImpl;
import service.impl.RetirementModeServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/mode/list")
public class ListModeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AfterDeathModeService admService = new AfterDeathModeServiceImpl();
	private DeathModeService dmService = new DeathModeServiceImpl();
	private RetirementModeService rmService = new RetirementModeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer mode = StringUtils.convertToInteger(req.getParameter("mode"));
		String jspPage = "/admin/mode/retirement/retirement-list.jsp";
		if (mode != null) {
			if (mode == 1) {
				req.setAttribute("modes", rmService.getAll());
				jspPage = "/admin/mode/retirement/retirement-list.jsp";
			} else if (mode == 2) {
				req.setAttribute("modes", dmService.getAll());
				jspPage = "/admin/mode/death/death-list.jsp";
			} else if (mode == 3) {
				jspPage = "/admin/mode/afterdeath/afterdeath-list.jsp";
				req.setAttribute("modes", admService.getAll());
			}
		} else {
			jspPage = "/error/null.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(jspPage);
		dispatcher.forward(req, resp);
	}
}
