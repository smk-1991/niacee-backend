package com.br.niacee.services;

import com.br.niacee.entities.Candidate;

import java.util.List;

public interface CandidateService {

    void addCandidate(Candidate candidate);

    Candidate findCandidate(Long id);

    List<Candidate> findAllCandidates();

}
