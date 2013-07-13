package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.joda.time.LocalTime;
import org.junit.Test;

public class TimeServiceTest {

	@Test
	public void testSetOfficeHours() {
		TimeService timeService = new TimeService();
		LocalTime timeStart, timeStop;
		
		timeStart = new LocalTime(16, 0);
		timeStop = new LocalTime(22, 30);
		timeService.setOfficeHours(timeStart, timeStop);
		assertTrue(timeService.checkWorking());
		
		timeStart = new LocalTime(5, 0);;
		timeStop = new LocalTime(16, 55);
		timeService.setOfficeHours(timeStart, timeStop);
		assertFalse(timeService.checkWorking());
	}

}
