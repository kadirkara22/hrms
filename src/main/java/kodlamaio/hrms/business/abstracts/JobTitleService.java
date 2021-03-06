package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobTitle;

public interface JobTitleService {

	DataResult<List<JobTitle>>  getAll();
	
    Result add(JobTitle jobTitle);
	Result delete(int id);
	

	DataResult<List<JobTitle>> findById(int id);
	
	DataResult<List<JobTitle>> findJobTitles(String title);
}
