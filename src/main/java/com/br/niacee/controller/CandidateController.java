package com.br.niacee.controller;

import com.br.niacee.entities.Candidate;
import com.br.niacee.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/candidate")
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(value = "/createCandidate")
    public ResponseEntity<Void> createCandidate(@RequestBody Candidate candidate){
        candidateService.addCandidate(candidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllCandidates")
    public ResponseEntity<List<Candidate>> findAllCandidates(){
        List<Candidate> candidateList = candidateService.findAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

}
