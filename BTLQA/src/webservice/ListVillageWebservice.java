package webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Village;
import service.VillageService;
import service.impl.VillageServiceImpl;

@WebServlet(urlPatterns = "/village/getByDistrict")
public class ListVillageWebservice extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VillageService villageService = new VillageServiceImpl();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String districtId = req.getParameter("id");
		List<Village> villages = villageService.getByDistrictId(districtId);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(villages));
	}

}
