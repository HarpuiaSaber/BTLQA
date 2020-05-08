package controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Search;
import service.UserPaymentHistoryService;
import service.impl.UserPaymentHistoryServiceImpl;
import utils.StringUtils;

@WebServlet(urlPatterns = "/admin/insurance/payment-history-export")
public class ExportPaymentHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserPaymentHistoryService uphService = new UserPaymentHistoryServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Date startDate = StringUtils.convertToDate(req.getParameter("startDate"));
		Date endDate = StringUtils.convertToDate(req.getParameter("endDate"));
		String idProvince = req.getParameter("province");
		String idDistrict = req.getParameter("district");
		String idVillage = req.getParameter("village");
		String name = req.getParameter("name");
		Long identityCard = StringUtils.convertToLong(req.getParameter("identityCard"));
		Date dob = StringUtils.convertToDate(req.getParameter("dob"));
		Search search = new Search();
		boolean isEmpty = false;
		if (startDate != null) {
			search.setStartDate(startDate);
		} else {
			isEmpty = true;
		}
		if (endDate != null) {
			search.setEndDate(new Date(endDate.getTime() + 86400000));
		} else {
			isEmpty = true;
		}
		if (!StringUtils.isNullOrEmpty(idProvince)) {
			search.setIdProvince(idProvince);
		} else {
			isEmpty = true;
		}
		if (!StringUtils.isNullOrEmpty(idDistrict)) {
			search.setIdDistrict(idDistrict);
		} else {
			isEmpty = true;
		}
		if (!StringUtils.isNullOrEmpty(idVillage)) {
			search.setIdVillage(idVillage);
		} else {
			isEmpty = true;
		}
		if (!StringUtils.isNullOrEmpty(name)) {
			search.setName(name);
		} else {
			isEmpty = true;
		}
		if (identityCard != null) {
			search.setIdentityCard(identityCard);
		} else {
			isEmpty = true;
		}
		if (dob != null) {
			search.setDob(dob);
		} else {
			isEmpty = true;
		}

		if (isEmpty) {
			HttpSession httpSession = req.getSession();
			Object object = httpSession.getAttribute("searchPH");
			if (object != null) {
				search = (Search) object;
			}
		}
		String fileName = uphService.exportToExecl(search);
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));

			File file = new File(fileName);
			Files.copy(file.toPath(), resp.getOutputStream());
		} catch (IOException e) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/error/null.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
