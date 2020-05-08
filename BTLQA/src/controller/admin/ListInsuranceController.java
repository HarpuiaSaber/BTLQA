package controller.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Paging;
import model.Search;
import service.InsuranceService;
import service.impl.InsuranceServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/insurance/insurance-list")
public class ListInsuranceController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private InsuranceService insuranceService = new InsuranceServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession httpSession = req.getSession();
		Object object = httpSession.getAttribute("searchI");
		Search search = new Search();
		if (object != null) {
			search = (Search) object;
		} else {
			httpSession.setAttribute("searchI", search);
		}
		Integer page = StringUtils.convertToInteger(req.getParameter("page"));
		Paging paging = new Paging();
		if (page == null)
			page = 1;
		paging.setBeginIndex((page - 1) * paging.getPageSize());
		req.setAttribute("insurances", insuranceService.searchWithPaging(search, paging));
		req.setAttribute("totalRecord", insuranceService.getTotalRecord(search));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/insurance/insurance-list.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession httpSession = req.getSession();
		Search search = new Search();
		Date startDate = StringUtils.convertToDate(req.getParameter("startDate"));
		Date endDate = StringUtils.convertToDate(req.getParameter("endDate"));
		String idProvince = req.getParameter("province");
		String idDistrict = req.getParameter("district");
		String idVillage = req.getParameter("village");
		String name = req.getParameter("name");
		Long identityCard = StringUtils.convertToLong(req.getParameter("identityCard"));
		Date dob = StringUtils.convertToDate(req.getParameter("dob"));
		if (startDate != null) {
			search.setStartDate(startDate);
		}
		if (endDate != null) {
			search.setEndDate(new Date(endDate.getTime() + 86400000));
		}
		if (!StringUtils.isNullOrEmpty(idProvince)) {
			search.setIdProvince(idProvince);
		}
		if (!StringUtils.isNullOrEmpty(idDistrict)) {
			search.setIdDistrict(idDistrict);
		}
		if (!StringUtils.isNullOrEmpty(idVillage)) {
			search.setIdVillage(idVillage);
		}
		if (!StringUtils.isNullOrEmpty(name)) {
			search.setName(name);
		}
		if (identityCard != null) {
			search.setIdentityCard(identityCard);
		}
		if (dob != null) {
			search.setDob(dob);
		}
		httpSession.setAttribute("searchI", search);

		Integer page = StringUtils.convertToInteger(req.getParameter("page"));
		Paging paging = new Paging();
		if (page == null)
			page = 1;
		paging.setBeginIndex((page - 1) * paging.getPageSize());
		req.setAttribute("insurances", insuranceService.searchWithPaging(search, paging));
		req.setAttribute("totalRecord", insuranceService.getTotalRecord(search));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/insurance/insurance-list.jsp");
		dispatcher.forward(req, resp);
	}

}
