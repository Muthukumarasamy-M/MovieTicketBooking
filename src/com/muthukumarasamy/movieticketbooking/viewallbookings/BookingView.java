package com.muthukumarasamy.movieticketbooking.viewallbookings;

import java.util.ArrayList;
import java.util.List;

import com.muthukumarasamy.movieticketbooking.dto.Booking;

public class BookingView {

	private BookingViewModel bookingviewmodel;
	private static List<Booking> bookings = new ArrayList<>();

	public BookingView() {
		bookingviewmodel = new BookingViewModel();
	}

	public void viewBooking() {
		bookings = bookingviewmodel.getBookings();
		System.out.println("-------------------------------------");
		if (bookings.isEmpty()) {
			System.out.println("No bookings available.");
		} else {
			System.out.println("Booking Details:");
			System.out.println("+------+--------------+-----------------+-------------+");
			System.out.printf("| %-4s | %-12s | %-15s | %-11s |%n", "ID", "User Name", "Movie Title", "Seats");
			System.out.println("+------+--------------+-----------------+-------------+");

			for (Booking booking : bookings) {
				System.out.printf("| %-4s | %-12s | %-15s | %-11s |%n", bookings.indexOf(booking) + 1,
						booking.getUserName(), booking.getMovieTitle(), booking.getBookedSeat());
			}

			System.out.println("+------+--------------+-----------------+-------------+");
		}

	}

}
