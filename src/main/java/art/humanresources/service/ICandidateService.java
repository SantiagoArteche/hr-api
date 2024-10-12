package art.humanresources.service;

import art.humanresources.model.Candidate;

import java.util.List;

public interface ICandidateService {

    List<Candidate> getAll();
    Candidate getById(String id);
    void create(Candidate candidate);
    void delete(String id);
}
