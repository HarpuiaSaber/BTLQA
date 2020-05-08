package service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.InsuranceDao;
import dao.impl.InsuranceDaoImpl;
import model.Insurance;
import model.Paging;
import model.Search;
import service.InsuranceService;

public class InsuranceServiceImpl implements InsuranceService {

	private InsuranceDao insuranceDao = new InsuranceDaoImpl();

	@Override
	public List<Insurance> search(Search search) {
		return insuranceDao.search(search);
	}

	@Override
	public List<Insurance> searchWithPaging(Search search, Paging paging) {
		return insuranceDao.searchWithPaging(search, paging);
	}

	@Override
	public int getTotalRecord(Search search) {
		return insuranceDao.getTotalRecord(search);
	}

	@Override
	public String exportToExecl(Search search) {
		List<Insurance> list = insuranceDao.search(search);
		String fileName = "Danh sach BHXH.xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("BHXH");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-mm-yyyy"));

		int rowNum = 0;
		Row firstRow = sheet.createRow(rowNum++);
		Cell label = firstRow.createCell(0);
		label.setCellValue("Danh sách BHXH");

		Row secondRow = sheet.createRow(rowNum++);
		Cell detail = secondRow.createCell(0);
		detail.setCellValue("Ghi chú: ");

		Row thirdRow = sheet.createRow(rowNum++);
		Cell c0 = thirdRow.createCell(0);
		c0.setCellValue(("STT"));
		Cell c1 = thirdRow.createCell(1);
		c1.setCellValue(("CMND"));
		Cell c2 = thirdRow.createCell(2);
		c2.setCellValue(("Họ tên"));
		Cell c3 = thirdRow.createCell(3);
		c3.setCellValue("Giới tính");
		Cell c4 = thirdRow.createCell(4);
		c4.setCellValue(("Hình thức"));
		Cell c5 = thirdRow.createCell(5);
		c5.setCellValue("Ngày ĐK");
		Cell c6 = thirdRow.createCell(6);
		c6.setCellValue("Địa chỉ");
		Cell c7 = thirdRow.createCell(7);
		c7.setCellValue("Trạng thái");
		long stt = 1;
		for (Insurance insurance : list) {
			Row row = sheet.createRow(rowNum++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(stt++);
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(insurance.getUser().getIdentityCard());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(insurance.getUser().getName());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(insurance.getUser().getGender());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(insurance.getMethod().getName());
			Cell cell5 = row.createCell(5);
			cell5.setCellStyle(cellStyle);
			cell5.setCellValue(insurance.getRegDate());
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(insurance.getUser().getVillage().getName() + ", "
					+ insurance.getUser().getVillage().getDistrict().getName() + ", "
					+ insurance.getUser().getVillage().getDistrict().getProvince().getName());
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(insurance.getStatus() == 0 ? "Đang đóng" : " Hoàn thành");
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
