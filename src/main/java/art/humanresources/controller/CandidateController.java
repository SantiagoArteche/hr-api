package art.humanresources.controller;


import art.humanresources.exceptions.NotFoundException;
import art.humanresources.model.Candidate;
import art.humanresources.service.CandidateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/candidate")
@CrossOrigin(value = "http://localhost:5433")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping()
    public List<Candidate> getAllCandidates(){
        return this.candidateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable String id){
        Candidate findCandidate = this.candidateService.getById(id);

        if(findCandidate != null){
            return ResponseEntity.ok(findCandidate);
        }else{
            throw new NotFoundException("Candidate by id " + id + " not found");
        }
    }

    @PostMapping()
    public Candidate createCandidate(@RequestBody Candidate candidate){
        this.candidateService.create(candidate);
        return candidate;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate, @PathVariable String id){
        Candidate findCandidate = this.candidateService.getById(id);

        if(findCandidate != null){
            findCandidate.setName(candidate.getName());
            findCandidate.setCountry(candidate.getCountry());
            findCandidate.setRequestedSalary(candidate.getRequestedSalary());
            this.candidateService.create(findCandidate);
            return ResponseEntity.ok(findCandidate);
        }else{
            throw new NotFoundException("Candidate by id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteCandidate(@PathVariable String id){
        Candidate findCandidate = this.candidateService.getById(id);

        if(findCandidate != null){
            this.candidateService.delete(id);
            Map<String, String> mapResponse = new HashMap<>();
            mapResponse.put("message", "Candidate with id " + id + " was deleted");
            return ResponseEntity.ok(mapResponse);
        }else{
            throw new NotFoundException("Candidate by id " + id + " not found");
        }
    }


}
