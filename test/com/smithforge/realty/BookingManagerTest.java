package com.smithforge.realty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class BookingManagerTest {

	@Test
	public void testWhenSingleBookingAddedToManagerDatesFromBookingAreReturned() throws Exception {

		BookingManager manager = new BookingManager();
		BookingDate start = new BookingDate(2010, 1);
		BookingDate end = new BookingDate(2010, 2);

		Booking booking = new Booking(start, end);
		manager.addBooking(booking);

		assertEquals(1, manager.getAllBookingDates().size());
		List<BookingDate> dates = booking.getDates();
		assertEquals(start.getTime(), dates.get(0).getTime());

	}

	@Test
	public void testWhenManyNonOverlappingBookingsAddedAllDatesReturned() throws IllegalBookingException {
		BookingManager manager = new BookingManager();
		BookingDate startOne = new BookingDate(2010, 1);
		BookingDate endOne = new BookingDate(2010, 2);

		Booking bookingOne = new Booking(startOne, endOne);

		BookingDate startTwo = new BookingDate(2010, 3);
		BookingDate endTwo = new BookingDate(2010, 4);

		Booking bookingTwo = new Booking(startTwo, endTwo);

		BookingDate startThree = new BookingDate(2010, 5);
		BookingDate endThree = new BookingDate(2010, 6);

		Booking bookingThree = new Booking(startThree, endThree);

		manager.addBooking(bookingOne);
		manager.addBooking(bookingTwo);
		manager.addBooking(bookingThree);

		List<BookingDate> allDates = manager.getAllBookingDates();
		assertEquals(3, allDates.size());

	}

	@Test
	public void testWhenTwoEqualBookingsAddedExceptionIsThrown() throws IllegalBookingException {
		BookingManager manager = new BookingManager();
		BookingDate startOne = new BookingDate(2010, 1);
		BookingDate endOne = new BookingDate(2010, 2);

		Booking bookingOne = new Booking(startOne, endOne);

		BookingDate startTwo = new BookingDate(2010, 3);
		BookingDate endTwo = new BookingDate(2010, 4);

		Booking bookingTwo = new Booking(startTwo, endTwo);

		BookingDate startThree = new BookingDate(2010, 3);
		BookingDate endThree = new BookingDate(2010, 4);

		Booking bookingThree = new Booking(startThree, endThree);

		manager.addBooking(bookingOne);
		manager.addBooking(bookingTwo);

		try {
			manager.addBooking(bookingThree);
			fail("Exception should have been thrown");
		} catch (IllegalBookingException e) {
		}

	}

	@Test
	public void testWhenTwoOverlappingBookingsAddedExceptionIsThrown() throws IllegalBookingException {
		BookingManager manager = new BookingManager();
		BookingDate startOne = new BookingDate(2010, 1);
		BookingDate endOne = new BookingDate(2010, 2);

		BookingDate startTwo = new BookingDate(2010, 3);
		BookingDate endTwo = new BookingDate(2010, 5);

		BookingDate startThree = new BookingDate(2010, 4);
		BookingDate endThree = new BookingDate(2010, 6);

		Booking bookingOne = new Booking(startOne, endOne);
		Booking bookingTwo = new Booking(startTwo, endTwo);
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
