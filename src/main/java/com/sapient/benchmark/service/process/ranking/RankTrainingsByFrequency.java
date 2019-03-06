package com.sapient.benchmark.service.process.ranking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.process.Data;

import lombok.extern.slf4j.Slf4j;

@Component(value="ByFrequency")
@Slf4j

public class RankTrainingsByFrequency implements Ranker{

	public Set<TrainingWithRank> rank(Data d) {
		
		log.info("*****************Data provided for ranking **************\n " +d);
		Set<TrainingWithRank> sortedTrainings = null;
		Map<Training, Integer> rankedTrainings = new HashMap<Training, Integer>();
		for (Map.Entry<String, List<Training>> groupoftrainings : d.getRecommendedTrainings().entrySet()) {
			log.info("Group of trainings for " + groupoftrainings.getKey() + " and trainings are " + groupoftrainings.getValue());
			if(groupoftrainings != null && groupoftrainings.getValue() != null)
				groupoftrainings.getValue().forEach(trng -> {
					log.info("Computing the rank for trainings " + trng);
					rankedTrainings.computeIfPresent(trng, (k, v) -> {
						Integer rank = rankedTrainings.get(trng);
						rank = rank + 1;
						log.info("Final rank for training " + trng +" " + rank);
						return rank;
					});
					rankedTrainings.putIfAbsent(trng, 1);
	
				});

		}
		log.info("*****************trainings ranked with rank **************\n " +rankedTrainings);
		 sortedTrainings =   new HashSet<TrainingWithRank>();
		for (Map.Entry<Training, Integer> training : rankedTrainings.entrySet()) {
			System.out.println("adding" + training.getKey().getCode());
			sortedTrainings.add(new TrainingWithRank(training.getKey(), training.getValue()));
		}
		log.info("**************List of sorted trainings By rank***************"+sortedTrainings.size());
		sortedTrainings.forEach(trng ->
				{
					log.info(trng.toString());
				});
		return sortedTrainings;
	}
}
