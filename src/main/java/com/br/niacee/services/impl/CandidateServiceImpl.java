package com.br.niacee.services.impl;

import com.br.niacee.dto.CandidateDTO;
import com.br.niacee.entities.Candidate;
import com.br.niacee.helper.CandidateBuilder;
import com.br.niacee.repository.CandidateRepository;
import com.br.niacee.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.br.niacee.helper.CandidateBuilder.candidateBuilder;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public void addCandidate(CandidateDTO candidateDTO) {
        candidateRepository.save(candidateBuilder(candidateDTO));
    }

    @Override
    public Candidate findCandidate(Long id) {
        return candidateRepository.findById(id).orElseThrow(()-> new RuntimeException("ID not found!"));
    }

    @Override
    public List<CandidateDTO> findAllCandidates() {
        List<Candidate> candidate = candidateRepository.findAll();

        return candidate.stream()
                .map(CandidateBuilder::candidateDTOBuilder)
                .collect(Collectors.toList());
    }
}
