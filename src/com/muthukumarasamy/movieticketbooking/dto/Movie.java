package com.muthukumarasamy.movieticketbooking.dto;

import java.util.List;

public class Movie {
	private String title;
	private List<List<String>> seats;

	public Movie(String title, List<List<String>> seats2) {
		this.title = title;
		this.seats = seats2;
	}
	
	public String getTitle() {
		return title;
	}

	public List<List<String>> getSeats() {
		return seats;
	}

	public void setSeats(List<List<String>> data) {
		this.seats = data;

	}

}
