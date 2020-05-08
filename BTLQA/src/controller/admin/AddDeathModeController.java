package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeathMode;
import service.DeathModeService;
import service.impl.DeathModeServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/mode/death/add")
public class AddDeathModeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DeathModeService dmService = new DeathModeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/mode/death/death-add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Double baseSalary = StringUtils.convertToDouble(req.getParameter("baseSalary"));
		Double coefficient = StringUtils.convertToDouble(req.getParameter("coefficient"));
		Double year = StringUtils.convertToDouble(req.getParameter("year"));
		DeathMode mode = new DeathMode();
		mode.setBaseSalary(baseSalary);
		mode.setCoefficient(coefficient);
		mode.setYear(year);
		dmService.insert(mode);
		resp.sendRedirect(req.getContextPath() + "/admin/mode/list?mode=2");
	}

}
