package com.test.djackatron2.service;

import org.joda.time.LocalTime;

public class TimeService {

	LocalTime timeStart, timeStop;
	
	public void setOfficeHours(LocalTime timeStart, LocalTime timeStop) {
		this.timeStart = timeStart;
		this.timeStop = timeStop;
	}

	public boolean checkWorking() {
		LocalTime current = new LocalTime();
		current.toDateTimeToday();
		
		if(current.isBefore(timeStop) && current.isAfter(timeStart)){
			return true;
		}

		return false;
	}
	
}
