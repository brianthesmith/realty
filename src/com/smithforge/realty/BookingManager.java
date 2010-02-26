package com.smithforge.realty;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {

	private List<Booking> bookings = new ArrayList<Booking>();

	public void addBooking(Booking booking) throws IllegalBookingException {
		if (bookings.contains(booking)) {
			throw new IllegalBookingException("Duplicate Bookings");
		} else {
			makeBooking(booking);
		}

	}

	public List<BookingDate> getAllBookingDates() {

		List<BookingDate> bookingDates = new ArrayList<BookingDate>();

		for (Booking booking : bookings) {
			bookingDates.addAll(booking.getDates());
		}
		return bookingDates;
	}

	private void makeBooking(Booking booking) throws IllegalBookingException {
		List<BookingDate> allBookingDates = new ArrayList<BookingDate>();
		boolean doubleBook = false;
		String errorMessage = "Duplicate Booing on: ";

		for (Booking existingBooking : bookings) {
			allBookingDates.addAll(existingBooking.getDates());
		}

		for (BookingDate newBookingDate : booking.getDates()) {
			if (allBookingDates.contains(newBookingDate)) {
				doubleBook = true;
				errorMessage += newBookingDate.toString();
			}
		}

		if (doubleBook) {
			throw new IllegalBookingException(errorMessage);
		} else {
			this.bookings.add(booking);
		}
	}

}
