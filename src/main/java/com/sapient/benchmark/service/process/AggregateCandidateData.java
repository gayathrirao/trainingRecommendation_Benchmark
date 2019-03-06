package com.sapient.benchmark.service.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.SkillSet;
import com.sapient.benchmark.service.feign.CandidateFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AggregateCandidateData {
	
	@Autowired
	CandidateFeignClient feignclient;
	
	public Candidate getCandidate(@PathVariable(value="emailid") String email)
	{
		log.info("Calling getCandidate for email "+email);
		return feignclient.getCandidate(email);
	}
	
	
	public Map<String,List<String>> aggregateSkills (Candidate candidate,AggregationType Type)
	{
		List<SkillSet> skillset = new ArrayList<SkillSet>();
		log.info("Aggregtion type " + Type);
		
		if(Type==AggregationType.ALL || Type == (AggregationType.EXPERIENCE) )
			skillset.addAll(candidate.getExperience());
		if(Type==AggregationType.ALL || Type == (AggregationType.INTERESTS) )
			skillset.addAll(candidate.getInterest());
		if(Type==AggregationType.ALL || Type == (AggregationType.CROSSSKILLS) )
			skillset.addAll(candidate.getCrossskills());
		return buildSkillMatrix(skillset);
	}
	
	

	public Map<String,List<String>> buildSkillMatrix(List<SkillSet> skillset) {
		Map<String,List<String>> skillMap = new HashMap<String,List<String>>();
		if(skillset != null)
		skillset.forEach(exp ->
		{
			skillMap.computeIfAbsent(exp.getSkillType(), k -> 
			 (new ArrayList<String>()));
			skillMap.computeIfPresent(exp.getSkillType(),
					(k,v) -> {
						if(v.contains(exp.getSkillName() ) == false)
						{
							log.debug("Adding" +exp.getSkillName());
							v.add(exp.getSkillName());
						}
						return v;
					});
			
			
			}
		);
		log.info("candidate Skill Map" +skillMap);
		return skillMap;
	}
}
