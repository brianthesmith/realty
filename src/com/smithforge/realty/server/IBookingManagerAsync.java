package com.smithforge.realty.server;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smithforge.realty.Booking;
import com.smithforge.realty.BookingDate;

public interface IBookingManagerAsync {

	void addBooking(Booking booking, AsyncCallback<Void> callback);

	void getAllBookingDates(AsyncCallback<List<BookingDate>> callback);

}
