package kodlamaio.hrms.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.HrmsVerification;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	@Autowired
	private UserDao userDao;
	
	public UserManager() {
		
	}
	
	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll(),"Kullanıcılar başarılı bir şekilde listelendi");
	}

	@Override
	public User add(User user) {
		return userDao.save(user);
	}

	@Override
	public Result register(User user, HrmsVerification hrmsVerification, EmailVerification emailVerification) {
		
		return null;
	}

	@Override
	public Result newAdd(User user) {
		this.userDao.save(user);
		return new SuccessResult("eklendi");
	}

	
}
