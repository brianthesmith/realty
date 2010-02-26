package com.smithforge.realty;

import java.util.Calendar;
import java.util.Date;

public class BookingDate {

	private Calendar calendar;
	private final int year;
	private final int day;

	public BookingDate(int year, int day) {
		this.year = year;
		this.day = day;
		this.calendar = createDefaultCalendar(year, day);
	}

	public int get(BookingDateUnit unit) {
		int value = 0;

		if (unit == BookingDateUnit.YEAR) {
			value = year;
		} else if (unit == BookingDateUnit.DAY_OF_YEAR) {
			value = day;
		}
		return value;
	}

	private Calendar createDefaultCalendar(int year, int dayOfYear) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_YEAR, dayOfYear);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	@Override
	public String toString() {
		return this.calendar.getTime().toString();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + year;
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
		BookingDate other = (BookingDate) obj;
		if (day != other.day)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public Date getTime() {
		return calendar.getTime();
	}

	public long getTimeInMillis() {
		return calendar.getTimeInMillis();
	}

	public void add(BookingDateUnit unit, int amount) {
		int calUnit = unit.getCalendarEnum();

		calendar.add(calUnit, amount);
	}

	public boolean after(BookingDate another) {
		return this.getTimeInMillis() > another.getTimeInMillis();
	}

	public enum BookingDateUnit {
		YEAR, DAY_OF_YEAR;

		public int getCalendarEnum() {
			int val = 0;
			if (this == YEAR) {
				val = Calendar.YEAR;
			}
			if (this == DAY_OF_YEAR) {
				val = Calendar.DAY_OF_YEAR;
			}
			return val;
		}
	}

	public BookingDate copy() {
		return new BookingDate(this.year, this.day);
	};
}
