package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="candidates_school")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class CandidateSchool {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;

	    @Column(name = "school_name")
	    private String schoolName;

	    @Column(name = "entry_date")
	    private LocalDate entryDate;

	    @Column(name = "graduation_date")
	    private LocalDate graduationDate;

	    @Column(name = "department")
	    private String department;

	    @ManyToOne(targetEntity = CandidateCv.class)
	    @JoinColumn(name = "candidates_cv_id")
	    private CandidateCv candidateCv;

	    @Column(name = "is_continue")
	    private boolean isContinue;
}
