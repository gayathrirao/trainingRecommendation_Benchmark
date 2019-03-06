package com.sapient.benchmark.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.TrainingForCandidateService;
import com.sapient.benchmark.service.process.AggregationType;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Setter
public class TrainingRecommendationController {

	@Autowired
	public TrainingForCandidateService  service;
	@GetMapping("/trainingrecommendation/candidate/email/{email}")
	public Set<TrainingWithRank> getTrainingsBasedOnCandidateSkillset(@PathVariable(value="email") String email)
	{
		log.info("Email obtained is  "+email);
		Candidate candidate = service.getCandidateByEmail(email);
		log.info("Candidate for email "+candidate);
		return service.getTrainingsForCandidate(candidate,AggregationType.ALL);
	}
	
	@GetMapping("/trainingrecommendation/candidate/experience/email/{email}")
	public Set<TrainingWithRank> getTrainingsBasedOnCandidateExperience(@PathVariable(value="email") String email)
	{
		log.info("Email obtained is  "+email);
		Candidate candidate = service.getCandidateByEmail(email);
		log.info("Candidate for email "+candidate);
		return service.getTrainingsForCandidate(candidate,AggregationType.EXPERIENCE);
	}
	
	@GetMapping("/trainingrecommendation/candidate/interests/email/{email}")
	public Set<TrainingWithRank> getTrainingsBasedOnCandidateInterests(@PathVariable(value="email") String email)
	{
		log.info("Email obtained is  "+email);
		Candidate candidate = service.getCandidateByEmail(email);
		log.info("Candidate for email "+candidate);
		return service.getTrainingsForCandidate(candidate,AggregationType.INTERESTS);
	}
	
	@GetMapping("/trainingrecommendation/candidate/crossskills/email/{email}")
	public Set<TrainingWithRank> getTrainingsBasedOnCandidateCrossSkills(@PathVariable(value="email") String email)
	{
		log.info("Email obtained is  "+email);
		Candidate candidate = service.getCandidateByEmail(email);
		log.info("Candidate for email "+candidate);
		return service.getTrainingsForCandidate(candidate,AggregationType.CROSSSKILLS);
	}
}
