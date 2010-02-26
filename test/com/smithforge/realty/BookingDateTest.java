package com.smithforge.realty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import com.smithforge.realty.BookingDate.BookingDateUnit;

public class BookingDateTest {

	@Test
	public void testGetTimeReturnsTimePassedInCtr() {
		BookingDate bookingDate = new BookingDate(2010, 1);

		Calendar c = createDefaultCalendar(2010, 1);

		assertEquals(c.getTime(), bookingDate.getTime());
	}

	@Test
	public void testGetTimeInMillisReturnsTimePassedInCtr() {
		BookingDate bookingDate = new BookingDate(2010, 1);

		Calendar c = createDefaultCalendar(2010, 1);

		assertEquals(c.getTimeInMillis(), bookingDate.getTimeInMillis());
	}

	private Calendar createDefaultCalendar(int year, int dayOfYear) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_YEAR, dayOfYear);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		return c;
	}

	@Test
	public void testEqualsReturnsTrueWhenEqual() {
		BookingDate first = new BookingDate(2010, 1);
		BookingDate second = new BookingDate(2010, 1);

		assertEquals(first, second);
	}

	@Test
	public void testEqualsReturnsFalseWhenNotEqual() {
		BookingDate first = new BookingDate(2010, 1);
		BookingDate second = new BookingDate(2010, 2);

		assertFalse(first.equals(second));
	}

	@Test
	public void testAfterReturnsTrueWhenAfter() {
		BookingDate first = new BookingDate(2010, 1);
		BookingDate second = new BookingDate(2010, 2);

		assertTrue(second.after(first));
	}

	@Test
	public void testAfterReturnsFalseWhenNotAfter() {
		BookingDate first = new BookingDate(2010, 1);
		BookingDate second = new BookingDate(2010, 2);

		assertFalse(first.after(second));
	}

	@Test
	public void testAfterReturnsFalseWhenEquals() {
		BookingDate first = new BookingDate(2010, 1);

		assertFalse(first.after(first));
	}

	@Test
	public void testAddAddsOneYearWhenGivenOneYear() {
		BookingDate date = new BookingDate(2010, 1);

		date.add(BookingDate.BookingDateUnit.YEAR, 1);

		Calendar c = createDefaultCalendar(2011, 1);

		assertEquals(c.getTime(), date.getTime());

	}

	@Test
	public void testAddAddsOneDayWhenGivenOneDay() {
		BookingDate date = new BookingDate(2010, 1);

		date.add(BookingDate.BookingDateUnit.DAY_OF_YEAR, 1);

		Calendar c = createDefaultCalendar(2010, 2);

		assertEquals(c.getTime(), date.getTime());

	}

	@Test
	public void testAddRollsOverToNextYearWhen365DaysAdded() {
		BookingDate date = new BookingDate(2010, 1);

		date.add(BookingDate.BookingDateUnit.DAY_OF_YEAR, 365);

		Calendar c = createDefaultCalendar(2011, 1);

		assertEquals(c.getTime(), date.getTime());

	}

	@Test
	public void testCopyReturnsIdenticalBookingDate() {
		BookingDate date = new BookingDate(2010, 1);
		BookingDate copy = date.copy();
		assertEquals(date, copy);
	}

	@Test
	public void testCopyReturnsCopyThatIsNotSame() {
		BookingDate date = new BookingDate(2010, 1);
		BookingDate copy = date.copy();
		assertNotSame(date, copy);
	}

	@Test
	public void testGetReturnsCorrectAmount() {
		BookingDate b = new BookingDate(2010, 1);
		assertEquals(2010, b.get(BookingDateUnit.YEAR));
		assertEquals(1, b.get(BookingDateUnit.DAY_OF_YEAR));
	}

}
