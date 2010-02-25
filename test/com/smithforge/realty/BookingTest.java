package com.smithforge.realty;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BookingTest {

	@Test(expected = IllegalBookingException.class)
	public void testExceptionThrowWhenStartAndEndAreSameDay() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1, 1);
		BookingDate endCalendar = new BookingDate(2010, 1, 1);

		new Booking(startCalendar, endCalendar);

	}

	@Test
	public void testGetBookingDatesReturnsStartDateWhenConsecutiveDays() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1, 1);
		BookingDate endCalendar = new BookingDate(2010, 2, 1);

		Booking booking = new Booking(startCalendar, endCalendar);

		List<Date> dates = booking.getDates();

		assertEquals(1, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0));

	}

	@Test
	public void testGetBookingDatesReturnsTwoDaysWhenStartAndEndAreThreeDaysApart() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1, 1);
		BookingDate middleCalendar = new BookingDate(2010, 2, 1);
		BookingDate endCalendar = new BookingDate(2010, 3, 1);

		Booking booking = new Booking(startCalendar, endCalendar);

		List<Date> dates = booking.getDates();

		assertEquals(2, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0));
		assertEquals(middleCalendar.getTime(), dates.get(1));

	}

	@Test
	public void testGetBookingDatesReturns364DaysWhenBookedAllYear() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1, 1);
		BookingDate endCalendar = new BookingDate(2010, 365, 23);

		Booking booking = new Booking(startCalendar, endCalendar);
		List<Date> dates = booking.getDates();

		assertEquals(364, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0));

		BookingDate lastNightInParadiseCalendar = new BookingDate(2010, 364, 1);

		Date lastDate = dates.get(363);

		assertEquals(lastNightInParadiseCalendar.getTime(), lastDate);

	}

	@Test(expected = IllegalBookingException.class)
	public void testExceptionThrownWhenEndDateBeforeStartDate() throws Exception {
		BookingDate startCalendar = new BookingDate(2010, 1, 1);
		BookingDate endCalendar = new BookingDate(2010, 2, 1);

		new Booking(endCalendar, startCalendar);
	}

}
