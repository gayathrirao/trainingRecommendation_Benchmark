package com.sapient.benchmark.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.feign.CandidateFeignClient;
import com.sapient.benchmark.service.process.AggregationType;
import com.sapient.benchmark.service.process.Data;
import com.sapient.benchmark.service.process.FetchMatchingTrainings;
import com.sapient.benchmark.service.process.ranking.Ranker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainingForCandidateServiceImpl implements TrainingForCandidateService {

	@Autowired
	private CandidateFeignClient candidateFeignClient;

	@Autowired
	private FetchMatchingTrainings trainingMatcher;

	@Autowired
	@Qualifier("ByFrequency")
	private Ranker rankTrainingsbyFreq;

	@Override
	public Set<TrainingWithRank> getTrainingsForCandidate(Candidate candidate,AggregationType aggregationType) {
		Map<String, List<Training>> recommendedTrainings = new HashMap<String, List<Training>>();
		
		log.info("Aggregation type : " +aggregationType);
		if(aggregationType == null) aggregationType = AggregationType.ALL;
		List<Training> trainingByDomain = trainingMatcher.fetchDomainTrainingsForCandidate(candidate,aggregationType);
		List<Training> trainingByTechnology = trainingMatcher.fetchTechTrainingsForCandidate(candidate,aggregationType);
		if(aggregationType == AggregationType.ALL)
		{
			List<Training> trainingByRole = trainingMatcher.fetchRoleSpecificTrainingForCandidate(candidate);
			log.info("trainingByRole " + trainingByRole);
			recommendedTrainings.put("Role", trainingByRole);
		}
		log.info("trainingByDomain " + trainingByDomain);
		log.info("trainingByTechnology " + trainingByTechnology);
		recommendedTrainings.put("Domain", trainingByDomain);
		recommendedTrainings.put("Technology", trainingByTechnology);
		Set<TrainingWithRank> finalTrainings = rankTrainingsbyFreq.rank(new Data(recommendedTrainings));
		return finalTrainings;
	}

	public Candidate getCandidateByEmail(String emailId) {
		return candidateFeignClient.getCandidate(emailId);
	}

}
