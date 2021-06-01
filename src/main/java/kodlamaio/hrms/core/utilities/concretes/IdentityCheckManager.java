package kodlamaio.hrms.core.utilities.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.abstracts.IdentityCheckService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class IdentityCheckManager implements IdentityCheckService{

	private CandidateDao candidateDao;
	
	@Autowired
	public IdentityCheckManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
		
	}

	private List<String> identities = new ArrayList<String>();
	
	
	@Override
	public List<String> identityCheckDataBase() {
		List<Candidate> candidates = this.candidateDao.findAll();
		int numberOfidentities = candidates.size();

		for (int i = 0; i < numberOfidentities; i++) {

			identities.add(candidates.get(i).getIdentityNumber());
		}
		return identities;
	}

	@Override
	public boolean identityIsItUsed(String identity) {
		boolean IsItUsed = false;

		if (this.identityCheckDataBase().contains(identity)) {
			IsItUsed = true;
		}
		return IsItUsed;
	}

}
