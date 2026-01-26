/*
 * package com.backend.dtos;
 * 
 * import com.backend.entity.Gender;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor public class DoctorDTO {
 * 
 * private Long id; private String doctorName; private String specialization;
 * private String doctorDepartment; private String doctorPhoneNo; private String
 * doctorEmail; private Gender gender; }
 */

package com.backend.dtos;

import java.time.LocalDate;

import com.backend.entity.Doctor;
import com.backend.entity.Gender;
import com.backend.entity.Status;
import com.backend.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {

	/* -------- Doctor Fields -------- */
	private Long doctorId;
	private String specialization;
	private String department;

	/* -------- User Fields -------- */
	private Long userId;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private Gender gender;
	private LocalDate dob;
	private Status status;

	/* -------- Constructor Mapper -------- */
	public DoctorDTO(Doctor doctor) {

		this.doctorId = doctor.getId();
		this.specialization = doctor.getSpecialization();
		this.department = doctor.getDept().getDepartmentName();

		User user = doctor.getUser();
		this.userId = user.getId();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.gender = user.getGender();
		this.dob = user.getDob();
		this.status = user.getStatus();
	}
}
