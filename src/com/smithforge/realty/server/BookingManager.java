package com.smithforge.realty.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smithforge.realty.Booking;
import com.smithforge.realty.BookingDate;
import com.smithforge.realty.IllegalBookingException;

public class BookingManager extends RemoteServiceServlet implements IBookingManager {

	private static final long serialVersionUID = 2945112719392816998L;
	private List<Booking> bookings = new ArrayList<Booking>();

	public BookingManager() throws IllegalBookingException {
		BookingDate feb1 = new BookingDate(2010, 31);
		BookingDate feb2 = new BookingDate(2010, 32);

		BookingDate feb22 = new BookingDate(2010, 30 + 22);
		BookingDate feb25 = new BookingDate(2010, 30 + 25);
		Booking firstBooking = new Booking(feb1, feb2);
		Booking secondBooking = new Booking(feb22, feb25);
		addBooking(firstBooking);
		addBooking(secondBooking);
	}

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
		String errorMessage = "Duplicate Booking on: ";

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
