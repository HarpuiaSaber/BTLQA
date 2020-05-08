package webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Province;
import service.ProvinceService;
import service.impl.ProvinceServiceImpl;

@WebServlet(urlPatterns = "/province/getAll")
public class ListProvinceWebservice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProvinceService provinceService = new ProvinceServiceImpl();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Province> provinces = provinceService.getAll();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(provinces));
	}
}
