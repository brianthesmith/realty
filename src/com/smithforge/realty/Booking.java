package com.smithforge.realty;

import java.util.ArrayList;
import java.util.List;

import com.smithforge.realty.BookingDate.BookingDateUnit;

public class Booking {

	private BookingDate startDate;
	private BookingDate endDate;

	public Booking(BookingDate startDate, BookingDate endDate) throws IllegalBookingException {
		if (startDate.equals(endDate)) {
			throw new IllegalBookingException("Bookings must be create for a minimum of one day in length");
		}
		if (startDate.after(endDate)) {
			throw new IllegalBookingException("Beginning date of booking must be before the ending date");
		}
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<BookingDate> getDates() {

		int startDay = startDate.get(BookingDateUnit.DAY_OF_YEAR);
		int endDay = endDate.get(BookingDateUnit.DAY_OF_YEAR);

		List<BookingDate> allDates = new ArrayList<BookingDate>();

		for (int i = startDay; i < endDay; i++) {
			BookingDate newDate = new BookingDate(startDate.get(BookingDateUnit.YEAR), i);
			allDates.add(newDate);
		}
		return allDates;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Booking other = (Booking) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
