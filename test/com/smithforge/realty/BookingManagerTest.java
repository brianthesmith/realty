package com.smithforge.realty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BookingManagerTest {

	@Test
	public void testWhenSingleBookingAddedToManagerDatesFromBookingAreReturned() throws Exception {

		BookingManager manager = new BookingManager();
		BookingDate start = new BookingDate(2010, 1, 1);
		BookingDate end = new BookingDate(2010, 1, 2);

		Booking booking = new Booking(start, end);
		manager.addBooking(booking);

		assertEquals(1, manager.getAllBookingDates().size());
		List<Date> dates = booking.getDates();
		assertEquals(start.getTime(), dates.get(0));

	}

	@Test
	public void testWhenManyNonOverlappingBookingsAddedAllDatesReturned() throws IllegalBookingException {
		BookingManager manager = new BookingManager();
		BookingDate startOne = new BookingDate(2010, 1, 1);
		BookingDate endOne = new BookingDate(2010, 2, 1);

		Booking bookingOne = new Booking(startOne, endOne);

		BookingDate startTwo = new BookingDate(2010, 3, 1);
		BookingDate endTwo = new BookingDate(2010, 4, 1);

		Booking bookingTwo = new Booking(startTwo, endTwo);

		BookingDate startThree = new BookingDate(2010, 5, 1);
		BookingDate endThree = new BookingDate(2010, 6, 1);

		Booking bookingThree = new Booking(startThree, endThree);

		manager.addBooking(bookingOne);
		manager.addBooking(bookingTwo);
		manager.addBooking(bookingThree);

		List<Date> allDates = manager.getAllBookingDates();
		assertEquals(3, allDates.size());

	}

	@Test
	public void testWhenOverlappingBookingsAddedExceptionIsThrown() throws IllegalBookingException {
		BookingManager manager = new BookingManager();
		BookingDate startOne = new BookingDate(2010, 1, 1);
		BookingDate endOne = new BookingDate(2010, 2, 1);

		Booking bookingOne = new Booking(startOne, endOne);

		BookingDate startTwo = new BookingDate(2010, 3, 1);
		BookingDate endTwo = new BookingDate(2010, 4, 1);

		Booking bookingTwo = new Booking(startTwo, endTwo);

		BookingDate startThree = new BookingDate(2010, 3, 1);
		BookingDate endThree = new BookingDate(2010, 4, 1);

		Booking bookingThree = new Booking(startThree, endThree);

		manager.addBooking(bookingOne);
		manager.addBooking(bookingTwo);

		try {
			manager.addBooking(bookingThree);
			fail("Exception should have been thrown");
		} catch (IllegalBookingException e) {
		}

	}

}
