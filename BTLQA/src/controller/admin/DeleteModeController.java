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

@WebServlet(urlPatterns = "/admin/mode/delete")
public class DeleteModeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AfterDeathModeService admService = new AfterDeathModeServiceImpl();
	private DeathModeService dmService = new DeathModeServiceImpl();
	private RetirementModeService rmService = new RetirementModeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer mode = StringUtils.convertToInteger(req.getParameter("mode"));
		Integer id = StringUtils.convertToInteger(req.getParameter("id"));
		if (mode != null && id != null) {
			if (mode == 1) {
				rmService.delete(id);				
			} else if (mode == 2) {
				dmService.delete(id);
			} else if (mode == 3) {
				admService.delete(id);
			}
			resp.sendRedirect(req.getContextPath() + "/admin/mode/list?mode=" + mode);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error/null.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
