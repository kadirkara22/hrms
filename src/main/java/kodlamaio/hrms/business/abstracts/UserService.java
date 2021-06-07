package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;

import kodlamaio.hrms.entities.concretes.HrmsVerification;
import kodlamaio.hrms.entities.concretes.User;

public interface UserService {

    DataResult<List<User>>getAll();
    
	User add(User user);
	
	Result newAdd(User user);
	
	public Result register(User user, HrmsVerification hrmsVerification, EmailVerification emailVerification);
}
