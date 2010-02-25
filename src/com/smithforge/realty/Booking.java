package com.smithforge.realty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {

	private BookingDate startDate;
	private BookingDate endDate;

	private long MILLS_IN_DAY = 1000 * 60 * 60 * 24;

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

	public List<Date> getDates() {

		List<Date> dates = new ArrayList<Date>();

		dates.add(startDate.getTime());
		long millis = endDate.getTimeInMillis() - startDate.getTimeInMillis();

		long days = millis / MILLS_IN_DAY;

		BookingDate start = startDate.copy();

		for (long d = 1; d < days; d++) {
			start.add(BookingDate.BookingDateUnit.DAY_OF_YEAR, 1);
			dates.add(start.getTime());
		}

		return dates;
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
