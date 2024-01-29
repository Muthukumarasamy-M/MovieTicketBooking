package com.muthukumarasamy.movieticketbooking.viewallbookings;

import java.util.List;

import com.muthukumarasamy.movieticketbooking.dto.Booking;
import com.muthukumarasamy.movieticketbooking.repository.Repository;

class BookingViewModel {
	
	public List<Booking> getBookings() {
		
		return Repository.getInstance().getBookings();
	}
	

}
