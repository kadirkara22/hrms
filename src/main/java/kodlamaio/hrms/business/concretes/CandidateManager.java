package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.abstracts.EmailCheckService;
import kodlamaio.hrms.core.utilities.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.abstracts.IdentityCheckService;
import kodlamaio.hrms.core.utilities.abstracts.MernisVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.MernisVerification;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao; 
	private EmailCheckService emailCheckService;
	private IdentityCheckService identityCheckService;
	private EmailVerificationService emailVerificationService;
	private MernisVerificationService mernisVerificationService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, EmailCheckService emailCheckService,
			IdentityCheckService identityCheckService, EmailVerificationService emailVerificationService,
			MernisVerificationService mernisVerificationService) {
		super();
		this.candidateDao = candidateDao;
		this.emailCheckService = emailCheckService;
		this.identityCheckService = identityCheckService;
		this.emailVerificationService = emailVerificationService;
		this.mernisVerificationService = mernisVerificationService;
	}



	@Override
	public Result delete(int id) {
		
		this.candidateDao.deleteById(id);
		return new SuccessResult("silindi");
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Data getirildi.");
	}

	@Override
	public Result register(Candidate candidate, EmailVerification emailVerification,
			MernisVerification mernisVerification) {
		Result result = new SuccessResult("Kayit basarili.");

		if (emailCheckService.emailIsItUsed(candidate.getEmail())) {
			result = new ErrorResult("Email sisteme kayitli."); 
			return result;
		}if (identityCheckService.identityIsItUsed(candidate.getIdentityNumber())) {
			result = new ErrorResult("Kimlik numarasi sisteme kayitli.");
			return result;
		}if(emailVerification.isEmailBool() == false) {
			result = new ErrorResult("Email onayi gerekiyor.");
			return result;
		}if(mernisVerification.isMernisBool() == false){
			result = new ErrorResult("Mernis onayi gerekiyor.");
			
		}else {
			this.mernisVerificationService.mernisVerification();
			this.emailVerificationService.sendMailCandidate(candidate);
			this.candidateDao.save(candidate);
			
		}
		return result;
	}


}
