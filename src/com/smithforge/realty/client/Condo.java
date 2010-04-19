package com.smithforge.realty.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smithforge.realty.BookingDate;
import com.smithforge.realty.IllegalBookingException;
import com.smithforge.realty.server.BookingManager;
import com.smithforge.realty.server.IBookingManager;

public class Condo implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		DatePicker datePicker = new DatePicker();

		IBookingManager manager = null;
		try {
			manager = new BookingManager();
		} catch (IllegalBookingException e) {
			e.printStackTrace();
		}
		List<BookingDate> allBookingDates = manager.getAllBookingDates();

		for (BookingDate bookingDate : allBookingDates) {
			datePicker.addStyleToDates("dateBooked", bookingDate.getTime());
		}

		rootPanel.add(datePicker);
	}

}
