package com.backend.backup.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.backend.entity.Department;
import com.backend.entity.Doctor;
import com.backend.entity.User;

public class DoctorExcelExporter {

	private static final String SHEET_NAME = "Doctors";

	private static final String[] HEADERS = { "Doctor ID", "User ID", "First Name", "Last Name", "Email", "Phone",
			"Gender", "Specialization", "Department", "Status" };

	public static ByteArrayInputStream export(List<Doctor> doctors) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			Sheet sheet = workbook.createSheet(SHEET_NAME);

			// ---------- Header ----------
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < HEADERS.length; i++) {
				headerRow.createCell(i).setCellValue(HEADERS[i]);
			}

			// ---------- Data ----------
			int rowIdx = 1;

			for (Doctor doctor : doctors) {

				User user = doctor.getUser();
				Department dept = doctor.getDept();

				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(doctor.getId());
				row.createCell(1).setCellValue(user.getId());
				row.createCell(2).setCellValue(user.getFirstname());
				row.createCell(3).setCellValue(user.getLastname());
				row.createCell(4).setCellValue(user.getEmail());
				row.createCell(5).setCellValue(user.getPhone());

				row.createCell(6).setCellValue(user.getGender() != null ? user.getGender().name() : "");

				row.createCell(7).setCellValue(doctor.getSpecialization() != null ? doctor.getSpecialization() : "");

				row.createCell(8).setCellValue(dept != null ? dept.getDepartmentName() : "");

				row.createCell(9).setCellValue(user.getStatus() != null ? user.getStatus().name() : "");
			}

			// Auto-size columns
			for (int i = 0; i < HEADERS.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());

		} catch (Exception e) {
			throw new RuntimeException("Failed to export Doctors Excel", e);
		}
	}
}
