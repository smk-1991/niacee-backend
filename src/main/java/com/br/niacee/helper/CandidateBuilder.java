package com.br.niacee.helper;

import com.br.niacee.dto.CandidateDTO;
import com.br.niacee.entities.Candidate;

public class CandidateBuilder {

    public static Candidate candidateBuilder(CandidateDTO candidateDTO){
        return Candidate.builder()
                .firstName(candidateDTO.getFirstName())
                .lastName(candidateDTO.getLastName())
                .stack(candidateDTO.getStack())
                .yearsOfExperience(candidateDTO.getYearsOfExperience())
                .salary(candidateDTO.getSalary())
                .salaryExpectation(candidateDTO.getSalaryExpectation())
                .technicalDescription(candidateDTO.getTechnicalDescription())
                .build();
    }

    public static CandidateDTO candidateDTOBuilder(Candidate candidate){
        return CandidateDTO.builder()
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .stack(candidate.getStack())
                .yearsOfExperience(candidate.getYearsOfExperience())
                .salary(candidate.getSalary())
                .salaryExpectation(candidate.getSalaryExpectation())
                .technicalDescription(candidate.getTechnicalDescription())
                .build();
    }


}
