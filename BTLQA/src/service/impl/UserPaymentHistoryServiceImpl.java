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

import dao.UserPaymentHistoryDao;
import dao.impl.UserPaymentHistoryDaoImpl;
import model.Paging;
import model.Search;
import model.UserPaymentHistory;
import service.UserPaymentHistoryService;

public class UserPaymentHistoryServiceImpl implements UserPaymentHistoryService {

	private UserPaymentHistoryDao uphDao = new UserPaymentHistoryDaoImpl();

	@Override
	public List<UserPaymentHistory> search(Search search) {
		return uphDao.search(search);
	}

	@Override
	public List<UserPaymentHistory> searchWithPaging(Search search, Paging paging) {
		return uphDao.searchWithPaging(search, paging);
	}

	@Override
	public int getTotalRecord(Search search) {
		return uphDao.getTotalRecord(search);
	}

	@Override
	public String exportToExecl(Search search) {
		List<UserPaymentHistory> list = uphDao.search(search);
		String fileName = "Lich su dong BHXH.xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Đóng BHXH");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-mm-yyyy"));

		int rowNum = 0;
		Row firstRow = sheet.createRow(rowNum++);
		Cell label = firstRow.createCell(0);
		label.setCellValue("Lịch sử đóng BHXH");

		Row secondRow = sheet.createRow(rowNum++);
		Cell detail = secondRow.createCell(0);
		detail.setCellValue("Ghi chú: ");

		Row thirdRow = sheet.createRow(rowNum++);
		Cell c0 = thirdRow.createCell(0);
		c0.setCellValue(("STT"));
		Cell c1 = thirdRow.createCell(1);
		c1.setCellValue("Ngày");
		Cell c2 = thirdRow.createCell(2);
		c2.setCellValue(("CMND"));
		Cell c3 = thirdRow.createCell(3);
		c3.setCellValue(("Họ tên"));
		Cell c4 = thirdRow.createCell(4);
		c4.setCellValue("Mã BHXH");
		Cell c5 = thirdRow.createCell(5);
		c5.setCellValue("Mã giao dịch");
		Cell c6 = thirdRow.createCell(6);
		c6.setCellValue("Ngân hàng");
		Cell c7 = thirdRow.createCell(7);
		c7.setCellValue("Chi phí");
		long stt = 1;
		for (UserPaymentHistory userPaymentHistory : list) {
			Row row = sheet.createRow(rowNum++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(stt++);
			Cell cell1 = row.createCell(1);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue(userPaymentHistory.getTime());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(userPaymentHistory.getInsurance().getUser().getIdentityCard());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(userPaymentHistory.getInsurance().getUser().getName());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(userPaymentHistory.getInsurance().getId());
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(userPaymentHistory.getTransactionId());
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(userPaymentHistory.getBankName());
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(userPaymentHistory.getCost());
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
