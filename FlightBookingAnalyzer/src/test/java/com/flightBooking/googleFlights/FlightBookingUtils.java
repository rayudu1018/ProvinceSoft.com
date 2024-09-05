package com.flightBooking.googleFlights;


public class FlightBookingUtils {

	public static int parsePrice(String priceText) {
		return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
	}

	public static int parseDuration(String durationText) {
		String[] parts = durationText.split(" ");
		int hours = 0;
		int minutes = 0;
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].contains("hr")) {
				hours = Integer.parseInt(parts[i - 1]);
			} else if (parts[i].contains("min")) {
				minutes = Integer.parseInt(parts[i - 1]);
			}
		}
		return hours * 60 + minutes;
	}

	public static String formatDuration(int totalMinutes) {
		int hours = totalMinutes / 60;
		int minutes = totalMinutes % 60;
		return hours + " hr " + minutes + " min";
	}
	
	public static void pauseExecution(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


