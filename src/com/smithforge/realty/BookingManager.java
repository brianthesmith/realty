package com.smithforge.realty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingManager {

	private Set<Booking> bookings = new HashSet<Booking>();

	public void addBooking(Booking booking) throws IllegalBookingException {
		if (!this.bookings.add(booking)) {
			throw new IllegalBookingException("Booking Exists for the specified date already");
		}
	}

	public List<Date> getAllBookingDates() {
		List<Date> dates = new ArrayList<Date>();

		for (Booking booking : bookings) {
			dates.addAll(booking.getDates());
		}
		return dates;
	}

}
