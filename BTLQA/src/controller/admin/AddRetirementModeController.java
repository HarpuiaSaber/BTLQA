package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RetirementMode;
import service.RetirementModeService;
import service.impl.RetirementModeServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/mode/retirement/add")
public class AddRetirementModeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RetirementModeService rmService = new RetirementModeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/mode/retirement/retirement-add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer year = StringUtils.convertToInteger(req.getParameter("year"));
		Integer old = StringUtils.convertToInteger(req.getParameter("old"));
		String gender = req.getParameter("gender");
		Double minPercent = StringUtils.convertToDouble(req.getParameter("minPercent"));
		Double maxPercent = StringUtils.convertToDouble(req.getParameter("maxPercent"));
		Double increase = StringUtils.convertToDouble(req.getParameter("increase"));
		String time = req.getParameter("time");
		RetirementMode mode = new RetirementMode();
		mode.setYear(year);
		mode.setGender(gender);
		mode.setOld(old);
		mode.setIncrease(increase);
		mode.setMinPercent(minPercent);
		mode.setMaxPercent(maxPercent);
		mode.setTime(time);
		rmService.insert(mode);
		resp.sendRedirect(req.getContextPath() + "/admin/mode/list?mode=1");
	}

}
