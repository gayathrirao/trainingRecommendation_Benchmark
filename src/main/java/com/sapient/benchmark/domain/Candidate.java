package com.sapient.benchmark.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class Candidate {

	private String FirstName;
	private String LastName;
	private @NonNull String  emailID;
	private List<SkillSet> experience;
	private List<SkillSet> interest;
	private List<SkillSet> crossskills;
	private List<Role> roles;
	
}

