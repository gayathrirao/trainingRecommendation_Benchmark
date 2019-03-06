package com.sapient.benchmark;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.domain.TrainingType;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.process.Data;
import com.sapient.benchmark.service.process.ranking.Ranker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testRankingByFrequency {

	@Autowired
	@Spy
	Ranker testRanker;

	@Test
	public void testRanking() throws Exception {
		Data d = prepareTestData();
		Set<TrainingWithRank> sortedSet = (testRanker.rank(d));
		assertEquals(sortedSet.size(),2);
		sortedSet.forEach(k->{
			if(k.getTraining().getName().equals("Java 8"))
				assertEquals(new Integer(3),k.getRank());
			if(k.getTraining().getName().equals("Pava 8"))
				assertEquals(new Integer(2), k.getRank());
		});

	}

	private Data prepareTestData() throws Exception {
		Map<String, List<Training>> recommendedTrainings = new HashMap<String, List<Training>>();
		List<Training> trngList = new ArrayList<Training>();
		String fromdateInString = "10/08/2019";
		String toDateInString = "15/08/2019";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		//String[] funcDomain = {"HealthCare","Telecom"};
		//String[] TechDomain = {"JAVA","C++","PYTHON"};
		trngList.add(new Training("Java 8", "TECH123", "Training on new features in java 8 and upcoming features in 9",
				null, formatter.parse(fromdateInString), formatter.parse(toDateInString), TrainingType.ONLINE));
		trngList.add(new Training("Pava 8", "TECH123", "Training on new features in java 8 and upcoming features in 9",
				null, formatter.parse(fromdateInString), formatter.parse(toDateInString), TrainingType.ONLINE));
		recommendedTrainings.put("Domain",trngList);
		recommendedTrainings.put("Technology", trngList);
		List<Training> trng1 = new ArrayList<Training>();
		trng1.add(trngList.get(0));
		recommendedTrainings.put("Roles", trng1);
		Data d = new Data(recommendedTrainings,null);
		return d;
	}

}
