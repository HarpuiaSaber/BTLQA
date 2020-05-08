package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AfterDeathMode;
import service.AfterDeathModeService;
import service.impl.AfterDeathModeServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/mode/afterdeath/update")
public class UpdateAfterDeathModeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AfterDeathModeService admService = new AfterDeathModeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = StringUtils.convertToInteger(req.getParameter("id"));
		if (id != null) {
			AfterDeathMode mode = admService.get(id);
			req.setAttribute("mode", mode);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/mode/afterdeath/afterdeath-update.jsp");
			dispatcher.forward(req, resp);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error/null.jsp");
			dispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer id = StringUtils.convertToInteger(req.getParameter("id"));
		String status = req.getParameter("status");
		Double month = StringUtils.convertToDouble(req.getParameter("month"));
		String time = req.getParameter("time");
		Double reduction = StringUtils.convertToDouble(req.getParameter("reduction"));
		AfterDeathMode mode = new AfterDeathMode();
		mode.setId(id);
		mode.setStatus(status);
		mode.setMonth(month);
		mode.setTime(time);
		mode.setReduction(reduction);
		admService.update(mode);
		resp.sendRedirect(req.getContextPath() + "/admin/mode/list?mode=3");
	}

}
