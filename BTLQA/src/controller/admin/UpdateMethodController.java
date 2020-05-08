package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Method;
import service.MethodService;
import service.impl.MethodServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/method/update")
public class UpdateMethodController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MethodService methodService = new MethodServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = StringUtils.convertToInteger(req.getParameter("id"));
		if (id != null) {
			Method method = methodService.get(id);
			req.setAttribute("method", method);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/method/method-update.jsp");
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
		String name = req.getParameter("name");
		Integer month = StringUtils.convertToInteger(req.getParameter("month"));
		if (id != null && !StringUtils.isNullOrEmpty(name) && month != null) {
			Method method = new Method();
			method.setId(id);
			method.setMonth(month);
			method.setName(name);
			methodService.update(method);
			resp.sendRedirect(req.getContextPath() +"/admin/method/list");
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error/null.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
