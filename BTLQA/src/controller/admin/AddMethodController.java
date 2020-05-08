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

@WebServlet(urlPatterns = "/admin/method/add")
public class AddMethodController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MethodService methodService = new MethodServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/method/method-add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		Integer month = StringUtils.convertToInteger(req.getParameter("month"));
		Method method = new Method();
		method.setMonth(month);
		method.setName(name);
		methodService.insert(method);
		resp.sendRedirect(req.getContextPath() + "/admin/method/list");
	}

}
