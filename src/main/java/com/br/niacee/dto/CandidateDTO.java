package com.br.niacee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDTO {

    private String firstName;
    private String lastName;
    private String stack;
    private Integer yearsOfExperience;
    private Integer salary;
    private Integer salaryExpectation;
    private String technicalDescription;

}
