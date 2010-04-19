package com.smithforge.realty.server;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smithforge.realty.Booking;
import com.smithforge.realty.BookingDate;
import com.smithforge.realty.IllegalBookingException;

@RemoteServiceRelativePath("bookings")
public interface IBookingManager extends RemoteService {

	void addBooking(Booking booking) throws IllegalBookingException;

	List<BookingDate> getAllBookingDates();

}
