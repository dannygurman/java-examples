package designPatterns.creational.builder.ex1;

import java.util.Date;

public class Client_Example {
	
	public static void main(String[] args) {
		AbstractBuilder builder = new VacationBuilder();

		Date today = new Date();
		builder.buildDay(today);
		builder.addHotel(today, new Hotel("Rex"));
		builder.addReservation(today, new Resort("Dam Sen"));
		builder.addTickets(3);
		
		Vacation v = builder.getVacation();
		System.out.println(v);
	}

}
