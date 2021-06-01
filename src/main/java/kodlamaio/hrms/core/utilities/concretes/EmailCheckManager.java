package kodlamaio.hrms.core.utilities.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.core.utilities.abstracts.EmailCheckService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class EmailCheckManager implements EmailCheckService {

	private CandidateDao candidateDao;
	private List<String> emails = new ArrayList<String>();
	
	@Autowired
	public EmailCheckManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}

	@Override
	public List<String> emailCheckDataBase() {
		
		List<Candidate> candidates=this.candidateDao.findAll();
int numberOfEmail = candidates.size();
		
		for(int i = 0 ; i < numberOfEmail ; i++) {
			
			emails.add(candidates.get(i).getEmail());
		}
		return emails;
	}

	@Override
	public boolean emailIsItUsed(String email) {
boolean IsItUsed = false;
		
		if(this.emailCheckDataBase().contains(email)){
			IsItUsed = true;
		}
		return IsItUsed;
	}

}
