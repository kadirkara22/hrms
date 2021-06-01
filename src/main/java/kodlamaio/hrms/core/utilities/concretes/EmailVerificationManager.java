package kodlamaio.hrms.core.utilities.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.abstracts.EmailVerificationService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmailVerificationManager implements EmailVerificationService{

	@Override
	public void sendMailCandidate(Candidate candidate) {
		System.out.println("Onay maili yollandi : " + candidate.getEmail());
		
	}

	@Override
	public void sendMailEmployer(Employer employer) {
		
		System.out.println("Onay maili yollandi : " + employer.getEmail());
		
	}

}
