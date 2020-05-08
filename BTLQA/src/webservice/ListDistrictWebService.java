package webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.District;
import service.DistrictService;
import service.impl.DistrictServiceImpl;

@WebServlet(urlPatterns = "/district/getByProvince")
public class ListDistrictWebService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DistrictService districtService = new DistrictServiceImpl();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String provinceId = req.getParameter("id");
		List<District> districts = districtService.getByProvinceId(provinceId);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");		
		resp.getWriter().print(gson.toJson(districts));
	}
}
