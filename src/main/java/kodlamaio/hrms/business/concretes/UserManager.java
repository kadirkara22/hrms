package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao; 
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> getAll() {
		
		return this.userDao.findAll();
	}

	@Override
	public void add(User user) {
	
		List<User> users=this.userDao.findAll();
		for (User user2 : users) {
			if (user2==user) {
				System.out.println("Sistemde bu kullanıcı mevcut");
			} else {
                     this.userDao.save(user);
                     System.out.println("Kullanıcı eklendi");
			}
		}
	}

}
