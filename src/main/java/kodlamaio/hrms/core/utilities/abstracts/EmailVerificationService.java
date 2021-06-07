package kodlamaio.hrms.core.utilities.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

import kodlamaio.hrms.entities.concretes.EmailVerification;


public interface EmailVerificationService {
	//public void sendMailCandidate(Candidate candidate);
	//public void sendMailEmployer(Employer employer);
	
	void generateCode(EmailVerification code, Integer id);
	Result verify(String verificationCode, Integer id);
}
