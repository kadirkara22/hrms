package kodlamaio.hrms.core.utilities.concretes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.core.business.GenerateRandomCode;
import kodlamaio.hrms.core.utilities.abstracts.EmailVerificationDao;
import kodlamaio.hrms.core.utilities.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.entities.concretes.EmailVerification;


@Service
public class EmailVerificationManager  implements EmailVerificationService{
	private EmailVerificationDao emailVerificationDao;
	
	
    @Autowired
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
		
	}

    @Override
	public void generateCode(EmailVerification code, Integer id) {
		// TODO Auto-generated method stub
				EmailVerification code_ = code;
				code.setCode(null);
				code.setVerified(false);
				if(code.isVerified() == false ) {
					GenerateRandomCode generator = new GenerateRandomCode();
					String code_create = generator.create();
					code.setCode(code_create);
					code.setUserId(id);
			
					emailVerificationDao.save(code);
					
				}
				return ;
	}
	
	@Override
	public Result verify(String verificationCode,Integer id) {
		// TODO Auto-generated method stub
		EmailVerification ref = emailVerificationDao.getOne(id);
		if(ref.getCode().equals(verificationCode)) {
			ref.setVerified(true);
			return  new SuccessDataResult<EmailVerification>(this.emailVerificationDao.save(ref),"Başarılı");
		}
		return  new ErrorDataResult<EmailVerification>(null,"Doğrulama kodu geçersiz");
		
		
	
	}
	/**
	  @Override
	public void sendMailCandidate(Candidate candidate) {
		System.out.println("Onay maili yollandi : " + candidate.getEmail());
		
	}

	@Override
	public void sendMailEmployer(Employer employer) {
		
		System.out.println("Onay maili yollandi : " + employer.getEmail());
		
	}
**/



	

}




