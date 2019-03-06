package com.sapient.benchmark.service.process.ranking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.process.Data;

public class RankByCandidateSkillSet 
 {

	public Set<TrainingWithRank> rank(Data d) {
		Set<TrainingWithRank> sortedTrainings = new TreeSet<TrainingWithRank>();
		Map<Training, Integer> rankedTrainings = new HashMap<Training, Integer>();

		for (Map.Entry<String, List<Training>> trainingouter : d.getRecommendedTrainings().entrySet()) {

			trainingouter.getValue().forEach(trng -> {
				d.getCandidate().getExperience().forEach(expSkill -> {
					if (trng.getPrerequisites().getTechnology().contains(expSkill.getSkillName()))
						rankedTrainings.computeIfPresent(trng, (k, v) -> {
							Integer rank = rankedTrainings.get(trng);
							rank = rank + 1;
							return rank;
						});
					else
						rankedTrainings.putIfAbsent(trng, 1);
				});
			});
		}
		for (Map.Entry<Training, Integer> training : rankedTrainings.entrySet()) {
			sortedTrainings.add(new TrainingWithRank(training.getKey(), training.getValue()));
		}
		return sortedTrainings;

	}

}
