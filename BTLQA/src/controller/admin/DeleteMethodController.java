package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MethodService;
import service.impl.MethodServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/method/delete")
public class DeleteMethodController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MethodService methodService = new MethodServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = StringUtils.convertToInteger(req.getParameter("id"));
		if (id != null) {
			methodService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/method/list");
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error/null.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
