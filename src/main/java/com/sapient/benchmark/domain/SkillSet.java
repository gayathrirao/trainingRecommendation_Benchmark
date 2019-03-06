package com.sapient.benchmark.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SkillSet {
	private String SkillType;
	private String SkillName;
	private float years;
	
	public SkillSet(String st,String sn, float yrs)
	{
		SkillType = st;
		SkillName = sn;
		years = yrs;
	}
	
}
