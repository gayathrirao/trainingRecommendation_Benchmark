package com.sapient.benchmark;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.service.feign.TrainingFeignClient;
import com.sapient.benchmark.service.process.AggregateCandidateData;
import com.sapient.benchmark.service.process.AggregationType;
import com.sapient.benchmark.service.process.FetchMatchingTrainings;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFetchTrainings {
	
@Autowired
	FetchMatchingTrainings matcher;
	
	@Mock
	AggregateCandidateData agg;
	
	@Autowired
	@Mock
	Candidate candidate;
	
	@Mock
	Map<String,List<String>> skillmap;
	
	@Mock
	TrainingFeignClient tfc;
	
	@Mock
	List<Training> trainings;
	
	@Test
	public void testFetchTrainingByFunctionalDomain() throws Exception
	{
		List<String> techlist = new ArrayList<String>();
		techlist.add("JAVA");
		techlist.add("C++");
		techlist.add("PLAY");
		when(skillmap.get("Technology")).thenReturn(techlist);
		when(agg.aggregateSkills(candidate,AggregationType.ALL)).thenReturn(skillmap);
		when(tfc.getTrainingForDomain(techlist)).thenReturn(trainings);
		when(trainings.size()).thenReturn(5);
		matcher.setAggregator(agg);
		matcher.setTrainingClient(tfc);
		//assertNotNull((matcher.fetchDomainTrainingsForCandidate(candidate)));
		//assertEquals(matcher.fetchDomainTrainingsForCandidate(candidate).size(), 5);
		
	}
}
