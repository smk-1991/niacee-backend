package com.br.niacee.helper;

import com.br.niacee.entities.Candidate;

public class CandidateBuilder {

    public static Candidate candidateBuilder(Candidate candidate){
        return Candidate.builder()
                .id(candidate.getId())
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
