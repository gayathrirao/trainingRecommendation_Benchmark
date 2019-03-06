package com.sapient.benchmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.SkillSet;
import com.sapient.benchmark.service.process.AggregateCandidateData;
import com.sapient.benchmark.service.process.AggregationType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAggregateCandidate {
	
	@Autowired
	AggregateCandidateData agg;
	
	
	
	
	@Test
	public void testAggregateSkill() throws Exception
	{
		Candidate candidate = new Candidate();
		List<SkillSet> skill = new ArrayList<SkillSet>();
		skill.add(new SkillSet("Technology","Java",5));
		skill.add(new SkillSet("Technology","C++",5));
		skill.add(new SkillSet("Technology","Play",5));
		skill.add(new SkillSet("Technology","AKKA",5));
		skill.add(new SkillSet("Domain","Ecommerce",5));
		skill.add(new SkillSet("Domain","Insurance",5));
		candidate.setInterest(skill);
		skill = new ArrayList<SkillSet>();
		skill.add(new SkillSet("Technology","RubyOnRails",5));
		skill.add(new SkillSet("Technology","ApacheSpark",5));
		skill.add(new SkillSet("Technology","Play",5));
		skill.add(new SkillSet("Technology","AKKA",5));
		candidate.setExperience(skill);
		Map<String, List<String>> skillmap = agg.aggregateSkills(candidate,AggregationType.ALL);
		assertNotNull(skillmap);
		assertEquals((skillmap.get("Technology")).size(), 6);
		assertEquals((skillmap.get("Domain")).size(),2);
	}
}
