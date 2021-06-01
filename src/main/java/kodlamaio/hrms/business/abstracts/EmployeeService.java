package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.Employee;

import kodlamaio.hrms.entities.concretes.HrmsVerification;

public interface EmployeeService {
	Result add(Employee employee);
	Result delete(int id);
	
	DataResult<List<Employee>>getAll();
	
	Result register(Employee employee, HrmsVerification hrmsVerification, EmailVerification emailVerification);
}
