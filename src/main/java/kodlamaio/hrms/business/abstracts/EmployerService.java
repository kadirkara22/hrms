package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.HrmsVerification;

public interface EmployerService {

	Result delete(int id);
	
	DataResult<Employer> add(Employer employer);
	DataResult<List<Employer>>getAll();
	
	public Result register(Employer employer, HrmsVerification hrmsVerification, EmailVerification emailVerification);
}
