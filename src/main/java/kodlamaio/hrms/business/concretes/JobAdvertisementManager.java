package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
	
	private JobAdvertisementDao jobAdvertisementDao;

	private EmployerDao employerDao;
	
	private CityDao cityDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,EmployerDao employerDao, CityDao cityDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAll(),"data listelendi");
		
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		
		Result engine = BusinessEngine.run(
				findEmployer(jobAdvertisement),
				findCity(jobAdvertisement),
				descriptionNullChecker(jobAdvertisement),
				ifMinSalaryNull(jobAdvertisement),
				ifMaxSalaryNull(jobAdvertisement),
				minSalaryChecker(jobAdvertisement),
				maxSalaryChecker(jobAdvertisement),
				 ifMinSalaryEqualsMaxSalary(jobAdvertisement) ,
				 ifQuotaSmallerThanOne(jobAdvertisement),
				 appealExpirationChecker( jobAdvertisement)
				);
		if(!engine.isSuccess()) {
			return new ErrorResult(engine.getMessage());
		}
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("eklendi");
		
	
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActive() {
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActive(true),"Ba??ar??l??");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveSorted() {
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActiveOrderByCreatedDateDesc(true),"Ba??ar??l??");
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id) {
		if(!this.employerDao.existsById(id)) {
			return new ErrorDataResult("Hata: ????veren bulunamad??");
		}
		else {
			return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Ba??ar??l??");
		}
	}

	@Override
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id) {
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult("Hata: ????veren bulunamad??");
		}
		JobAdvertisement ref =  this.jobAdvertisementDao.getOne(id);
		ref.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(ref),"???? ilan?? pasif olarak ayarland??");
		
	}
	private Result descriptionNullChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return new ErrorResult("???? tan??m?? bo?? b??rak??lamaz");
		}
		return new SuccessResult();
	}
	
	private Result findCity(JobAdvertisement jobAdvertisement) {
		if(!this.cityDao.existsById(jobAdvertisement.getCity().getId())) {
			return new ErrorResult("??ehir bulunamad?? ");
		}
		return new SuccessResult();
	}
	

	
	private Result findEmployer(JobAdvertisement jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployer().getId())) {
			return new ErrorResult( "????veren bulunamad??");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == null) {
			return new ErrorResult("Minimum maa?? girilmek zorundad??r");
		}
		return new SuccessResult();
	}
	
	
	private Result ifMaxSalaryNull(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == null) {
			return new ErrorResult("Maksimum maa?? girilmek zorundad??r");
		}
		return new SuccessResult();
	}
	
	private Result minSalaryChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == 0) {
			return new ErrorResult("Minimum Maa?? 0 olamaz");
		}
		return new SuccessResult();
	}
	
	private Result maxSalaryChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == 0) {
			return new ErrorResult("Maksimum Maa?? 0 verilemez");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryEqualsMaxSalary(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() >= jobAdvertisement.getMaxSalary()) {
			return new ErrorResult("Minimum Maa?? Maksimum Maa??a e??it olamaz");
		}
		return new SuccessResult();
	}
	
	private Result ifQuotaSmallerThanOne(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getQuota() < 1) {
			return new ErrorResult("A????k pozisyon adedi 1 den k??????k olamaz");
		}
		return new SuccessResult();
	}
	
	private Result appealExpirationChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getAppealExpirationDate() == null) {
			return new ErrorResult("Son Ba??vuru Tarihi Girilmek Zorundad??r");
		}
		return new SuccessResult();
	}
	
	


}
