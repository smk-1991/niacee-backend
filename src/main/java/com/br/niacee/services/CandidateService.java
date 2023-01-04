package com.br.niacee.services;

import com.br.niacee.dto.CandidateDTO;
import com.br.niacee.entities.Candidate;

import java.util.List;

public interface CandidateService {

    void addCandidate(CandidateDTO candidateDTO);

    Candidate findCandidate(Long id);

    List<CandidateDTO> findAllCandidates();

}
