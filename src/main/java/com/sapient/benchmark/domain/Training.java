package com.sapient.benchmark.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Training {
	
	private String name;
	private String code;
	private String description;
	private Prerequisites prerequisites ;
	private Date from;
	private Date to;
	private TrainingType typeOfTraining;
	
	public Training(String Name,String Code,String Description,Prerequisites prereq, Date From,Date To,TrainingType type)
	{
		name = Name;
		code = Code;
		description = Description;
		prerequisites = prereq;
		from = From;
		to = To;
		typeOfTraining = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Training other = (Training) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		return true;
	}
	
	
}
