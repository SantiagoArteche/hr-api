package art.humanresources.service;

import art.humanresources.model.Candidate;
import art.humanresources.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService implements ICandidateService {


    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAll() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate getById(String id) {
        return this.candidateRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Candidate candidate) {
        this.candidateRepository.save(candidate);
    }

    @Override
    public void delete(String id) {
        this.candidateRepository.deleteById(id);
    }
}
