package kodlamaio.hrms.core.utilities.abstracts;

import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmailVerificationService {
	public void sendMailCandidate(Candidate candidate);
	public void sendMailEmployer(Employer employer);
}
