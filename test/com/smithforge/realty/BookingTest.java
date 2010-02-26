package com.smithforge.realty;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BookingTest {

	@Test(expected = IllegalBookingException.class)
	public void testExceptionThrowWhenStartAndEndAreSameDay() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate endCalendar = new BookingDate(2010, 1);

		new Booking(startCalendar, endCalendar);

	}

	@Test
	public void testGetBookingDatesReturnsStartDateWhenConsecutiveDays() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate endCalendar = new BookingDate(2010, 2);

		Booking booking = new Booking(startCalendar, endCalendar);

		List<BookingDate> dates = booking.getDates();

		assertEquals(1, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0).getTime());

	}

	@Test
	public void testGetBookingDatesReturnsTwoDaysWhenStartAndEndAreThreeDaysApart() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate middleCalendar = new BookingDate(2010, 2);
		BookingDate endCalendar = new BookingDate(2010, 3);

		Booking booking = new Booking(startCalendar, endCalendar);

		List<BookingDate> dates = booking.getDates();

		assertEquals(2, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0).getTime());
		assertEquals(middleCalendar.getTime(), dates.get(1).getTime());

	}

	@Test
	public void testGetBookingDatesReturns364DaysWhenBookedAllYear() throws Exception {

		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate endCalendar = new BookingDate(2010, 365);

		Booking booking = new Booking(startCalendar, endCalendar);
		List<BookingDate> dates = booking.getDates();

		assertEquals(364, dates.size());
		assertEquals(startCalendar.getTime(), dates.get(0).getTime());

		BookingDate lastNightInParadiseCalendar = new BookingDate(2010, 364);

		Date lastDate = dates.get(363).getTime();

		assertEquals(lastNightInParadiseCalendar.getTime(), lastDate);

	}

	@Test(expected = IllegalBookingException.class)
	public void testExceptionThrownWhenEndDateBeforeStartDate() throws Exception {
		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate endCalendar = new BookingDate(2010, 2);

		new Booking(endCalendar, startCalendar);
	}

	@Test(expected = IllegalBookingException.class)
	public void testWhenStartAndEndDateSameDayExceptionThrown() throws Exception {
		BookingDate startCalendar = new BookingDate(2010, 1);
		BookingDate endCalendar = new BookingDate(2010, 1);

		new Booking(startCalendar, endCalendar);
	}

}
