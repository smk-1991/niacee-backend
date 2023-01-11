package com.br.niacee.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long  id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "stack")
    private String stack;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "salary_expectation")
    private Integer salaryExpectation;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "technical_description")
    private String technicalDescription;

}
