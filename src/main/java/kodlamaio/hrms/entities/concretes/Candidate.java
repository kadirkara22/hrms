package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name="user_id",referencedColumnName = "id")
public class Candidate extends User {

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name = "password_repeat")
	private String passwordRepeat;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="date_of_birth")
	private LocalDate birthDate;
	
	@Column(name="identity_number")
	private String identificationNumber;
}
