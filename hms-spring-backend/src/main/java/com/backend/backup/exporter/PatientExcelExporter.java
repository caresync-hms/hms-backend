package com.backend.backup.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.backend.entity.Patient;
import com.backend.entity.User;

public class PatientExcelExporter {

	private static final String SHEET_NAME = "Patients";

	private static final String[] HEADERS = { "Patient ID", "User ID", "First Name", "Last Name", "Email", "Phone",
			"Gender", "Blood Group", "Medical History", "Admit Date", "Discharge Date", "Status" };

	public static ByteArrayInputStream export(List<Patient> patients) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			Sheet sheet = workbook.createSheet(SHEET_NAME);

			// ---------------- Header ----------------
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < HEADERS.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(HEADERS[i]);
			}

			// ---------------- Data ----------------
			int rowIndex = 1;
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

			for (Patient patient : patients) {

				User user = patient.getUser();
				Row row = sheet.createRow(rowIndex++);

				row.createCell(0).setCellValue(patient.getId());
				row.createCell(1).setCellValue(user.getId());
				row.createCell(2).setCellValue(user.getFirstname());
				row.createCell(3).setCellValue(user.getLastname());
				row.createCell(4).setCellValue(user.getEmail());
				row.createCell(5).setCellValue(user.getPhone());

				row.createCell(6).setCellValue(user.getGender() != null ? user.getGender().name() : "");

				row.createCell(7).setCellValue(patient.getBloodGroup() != null ? patient.getBloodGroup().name() : "");

				row.createCell(8).setCellValue(patient.getMedicalHistory() != null ? patient.getMedicalHistory() : "");

				row.createCell(9).setCellValue(
						patient.getAdmitDate() != null ? patient.getAdmitDate().format(dateTimeFormatter) : "");

				row.createCell(10).setCellValue(
						patient.getDischargeDate() != null ? patient.getDischargeDate().format(dateTimeFormatter) : "");

				row.createCell(11).setCellValue(user.getStatus() != null ? user.getStatus().name() : "");
			}

			// Auto-size columns
			for (int i = 0; i < HEADERS.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());

		} catch (Exception e) {
			throw new RuntimeException("Failed to export Patient Excel", e);
		}
	}
}
