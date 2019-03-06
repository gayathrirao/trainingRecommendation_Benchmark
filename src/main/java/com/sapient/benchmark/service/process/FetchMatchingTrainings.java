package com.sapient.benchmark.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.service.feign.TrainingFeignClient;

import lombok.Setter;

@Service
@Setter
public class FetchMatchingTrainings {

	@Autowired
	AggregateCandidateData aggregator;
	
	@Autowired
	TrainingFeignClient trainingClient;
	
	public List<Training> fetchDomainTrainingsForCandidate(Candidate candidate, AggregationType aggregationType)
	{
		Map<String,List<String>> skillMap = aggregator.aggregateSkills(candidate,aggregationType);
		if (skillMap.containsKey("Domain"))
		return (trainingClient.getTrainingForDomain(skillMap.get("Domain")));
		return null;
		
	}
	
	public List<Training> fetchTechTrainingsForCandidate(Candidate candidate, AggregationType aggregationType)
	{
		Map<String,List<String>> skillMap = aggregator.aggregateSkills(candidate,aggregationType);
		if (skillMap.containsKey("Technology"))
		return (trainingClient.getTrainingByTechnology(skillMap.get("Technology")));
		return null;
		
	}
	
	public List<Training> fetchRoleSpecificTrainingForCandidate(Candidate candidate)
	{
		List<String> rolesPlayed = new ArrayList<String> ();
		candidate.getRoles().forEach(role -> rolesPlayed.add(role.getRolename()));
		return (trainingClient.getTrainingByroles(rolesPlayed));
	}

}
