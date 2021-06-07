package kodlamaio.hrms.business.concretes;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateCvService;

import kodlamaio.hrms.core.business.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateJobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateLanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateTalentDao;
import kodlamaio.hrms.entities.concretes.CandidateCv;

@Service
public class CandidateCvManager implements CandidateCvService {

	private CandidateCvDao candidateCvDao;
	private CandidateSchoolDao candidateSchoolDao;
	private CandidateTalentDao candidateTalentDao;
	private CandidateLanguageDao candidateLanguageDao;
	private CandidateJobExperienceDao candidateJobExperienceDao;
	
	private ImageService imageService;
	
	@Autowired
	public CandidateCvManager(CandidateCvDao candidateCvDao, CandidateSchoolDao candidateSchoolDao,
			CandidateTalentDao candidateTalentDao, CandidateLanguageDao candidateLanguageDao,
			CandidateJobExperienceDao candidateJobExperienceDao, ImageService imageService) {
		super();
		this.candidateCvDao = candidateCvDao;
		this.candidateSchoolDao = candidateSchoolDao;
		this.candidateTalentDao = candidateTalentDao;
		this.candidateLanguageDao = candidateLanguageDao;
		this.candidateJobExperienceDao = candidateJobExperienceDao;
		this.imageService = imageService;
	}

	@Override
	public DataResult<List<CandidateCv>> getAll() {
		return new SuccessDataResult<List<CandidateCv>>
		(this.candidateCvDao.findAll(),"İş Arayan Cv si listelendi");
	}

	@Override
	public Result add(CandidateCv candidateCv) {
	      this.candidateCvDao.save(candidateCv);
	        return new SuccessResult("Cv eklendi");

	}

	@Override
	public DataResult<List<CandidateCv>> findByCandidateId(int id) {
		return new SuccessDataResult<List<CandidateCv>>(this.candidateCvDao.findByCandidateId(id));
	}

	@Override
	public Result uploadCvPhoto(int candidateCvId, MultipartFile multipartFile) throws IOException {

	    var result = this.imageService.upload(multipartFile);
	      var url = result.getData().get("url");
	      
	      CandidateCv ref = this.candidateCvDao.getOne(candidateCvId); 
	      ref.setAvatarUrl(url.toString());
	      this.candidateCvDao.save(ref);
	      
	        return new SuccessResult("başarılı");
	}

}
