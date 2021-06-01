package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.MernisVerification;


public interface CandidateService {
	
	Result delete(int id);
	
	DataResult<List<Candidate>>getAll();
	
	Result register(Candidate candidate, EmailVerification emailVerification, MernisVerification mernisVerification);
}
