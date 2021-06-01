package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.HrmsVerification;
@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	@Override
	public Result add(Employee employee) {
		
		List<Employee> employees=this.employeeDao.findAll();
		for (Employee employee2 : employees) {
			if (employee==employee2) {
				return new ErrorResult("bu kayıt mevcut");
			} else {
				this.employeeDao.save(employee);
			}
		}
		return new SuccessResult( "Employee Eklendi");
	}

	@Override
	public Result delete(int id) {
		return new SuccessResult("employee silindi");
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Emplyoee listelendi");
	}
 
	@Override
	public Result register(Employee employee, HrmsVerification hrmsVerification, EmailVerification emailVerification) {
		// TODO Auto-generated method stub
		return null;
	}

}
