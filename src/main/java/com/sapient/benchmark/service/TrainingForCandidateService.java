package com.sapient.benchmark.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.process.AggregationType;

@Service

public interface TrainingForCandidateService {

	Candidate getCandidateByEmail(String emailId);
	Set<TrainingWithRank> getTrainingsForCandidate(Candidate candidate, AggregationType aggregationType);
}
