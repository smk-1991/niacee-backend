package com.br.niacee.controller;

import com.br.niacee.dto.CandidateDTO;
import com.br.niacee.entities.Candidate;
import com.br.niacee.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/candidate")
@CrossOrigin
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;


    @PostMapping(value = "/createCandidate")
    public ResponseEntity<Void> createCandidate(@RequestBody CandidateDTO candidateDTO){
        candidateService.addCandidate(candidateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllCandidates")
    public ResponseEntity<List<CandidateDTO>> findAllCandidates(){
        List<CandidateDTO> candidateList = candidateService.findAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

}
