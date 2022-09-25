package com.br.niacee.services.impl;

import com.br.niacee.entities.Candidate;
import com.br.niacee.repository.CandidateRepository;
import com.br.niacee.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.niacee.helper.CandidateBuilder.candidateBuilder;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        candidateRepository.save(candidateBuilder(candidate));
    }

    @Override
    public Candidate findCandidate(Long id) {
        return candidateRepository.findById(id).orElseThrow(()-> new RuntimeException("ID not found!"));
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }
}
