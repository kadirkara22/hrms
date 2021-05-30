package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;
	
	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public List<JobTitle> getAll() {
		
		return this.jobTitleDao.findAll();
	}

	@Override
	public void add(JobTitle jobTitle) {
		
		List<JobTitle> jobTitles=this.jobTitleDao.findAll();
		
		for (JobTitle jobTitle2 : jobTitles) {
		if (jobTitle2==jobTitle) {
			System.out.println("Bu pozisyon zaten mevcut");
		} else {
               this.jobTitleDao.save(jobTitle);
               System.out.println("Kullanıcı eklendi");
		}
			
		}
		
		
	}

}
