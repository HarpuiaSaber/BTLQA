package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Method;
import service.MethodService;
import service.impl.MethodServiceImpl;

@WebServlet(urlPatterns = "/admin/method/list")
public class ListMethodController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MethodService methodService = new MethodServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Method> methods = methodService.getAll();
		req.setAttribute("methods", methods);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/method/method-list.jsp");
		dispatcher.forward(req, resp);
	}

}
