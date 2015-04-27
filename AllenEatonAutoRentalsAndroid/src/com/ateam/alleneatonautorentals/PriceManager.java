package com.ateam.alleneatonautorentals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class PriceManager {
	private static Map <String, Map <String, Double> > vehiclePrices;
	private static Map <String, Double> additionalPrices;
	private static Map <String, Double> locationTax;
	private static Map <String, Double> stateLicenseFees;
	
	private static Map <String, Object> contractDetails;
	private static final String CAR_TYPE_TAG = "Car Type";
	private static final String START_DATE_TAG 	= "Start Date";
	private static final String END_DATE_TAG = "End Date";
	private static final String CITY_TAG = "City";
	private static final String STATE_TAG = "State";
	private static final String CHECKIN_DATE_TAG = "Checkin Date";
	
	private static final String WEEK_TAG = "week";
	private static final String DAY_TAG = "day";
	private static final String ECONOMY_TAG = "Economy";
	private static final String COMPACT_TAG = "Compact";
	private static final String STANDARD_TAG = "Standard";
	private static final String PREMIUM_TAG = "Premium";
	private static final String SMALL_SUV_TAG = "Sm SUV";
	private static final String STANDARD_SUV_TAG = "Std SUV";
	private static final String MINIVAN_TAG = "Minivan";
	
	private static final String GPS_TAG = "GPS Receiver";
	private static final String CHILD_SEATS_TAG = "Child Seats";
	private static final String KTAG_TAG = "K-Tag Rental";
	private static final String ASSISTANCE_TAG = "Roadside Assistance";
	private static final String DINSURANCE_TAG = "Loss Damage Waiver Insurance";
	private static final String AINSURANCE_TAG = "Personal Accident Insurance";
		
	private static final String ATCHISON_KS_TAG = "Atchison, Kansas";
	private static final String BELTON_MO_TAG = "Belton, Missouri";
	private static final String EMPORIA_KS_TAG = "Emporia, Kansas";
	private static final String HIAWATHA_KS_TAG = "Hiawatha, Kansas";
	private static final String KC_MO_TAG = "Kansas City, Missouri";
	private static final String LAWRENCE_KS_TAG = "Lawrence, Kansas";
	private static final String LEAVENWORTH_KS_TAG = "Leavenworth, Kansas";
	private static final String MANHATTAN_KS_TAG = "Manhattan, Kansas";
	private static final String PLATTECITY_MO_TAG = "Platte City, Missouri";
	private static final String STJOSEPH_MO_TAG = "St. Joseph, Missouri";
	private static final String TOPEKA_KS_TAG = "Topeka, Kansas";
	private static final String WARRENSBURG_MO_TAG = "Warrensburg, Missouri";
	
	private static final String KANSAS_TAG = "Kansas";
	private static final String MISSOURI_TAG = "Missouri";
	
	static {
		vehiclePrices = new HashMap <String, Map <String, Double> >();
		additionalPrices  = new HashMap <String, Double>();
		locationTax  = new HashMap <String, Double>();
		stateLicenseFees = new HashMap <String, Double>();
		
		// Initializing Vehicle Prices
		Map <String, Double> vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 45.0);
		vehicle.put(WEEK_TAG, 300.0);
		vehiclePrices.put(ECONOMY_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 50.0);
		vehicle.put(WEEK_TAG, 325.0);
		vehiclePrices.put(COMPACT_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 60.0);
		vehicle.put(WEEK_TAG, 400.0);
		vehiclePrices.put(STANDARD_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 65.0);
		vehicle.put(WEEK_TAG, 435.0);
		vehiclePrices.put(PREMIUM_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 70.0);
		vehicle.put(WEEK_TAG, 475.0);
		vehiclePrices.put(SMALL_SUV_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 75.0);
		vehicle.put(WEEK_TAG, 500.0);
		vehiclePrices.put(STANDARD_SUV_TAG, vehicle);
		
		vehicle = new HashMap <String, Double>();
		vehicle.put(DAY_TAG, 85.0);
		vehicle.put(WEEK_TAG, 575.0);
		vehiclePrices.put(MINIVAN_TAG, vehicle);
		
		// Initializing Additional Equipment/Services
		additionalPrices.put(GPS_TAG, 15.0);
		additionalPrices.put(CHILD_SEATS_TAG, 10.0);
		additionalPrices.put(KTAG_TAG, 2.0);
		additionalPrices.put(ASSISTANCE_TAG, 7.0);
		additionalPrices.put(DINSURANCE_TAG, 25.0);
		additionalPrices.put(AINSURANCE_TAG, 5.0);
		
		// Initializing Location Sales Tax
		locationTax.put(ATCHISON_KS_TAG, 0.075);
		locationTax.put(BELTON_MO_TAG, 0.07);
		locationTax.put(EMPORIA_KS_TAG, 0.075);
		locationTax.put(HIAWATHA_KS_TAG, 0.07);
		locationTax.put(KC_MO_TAG, 0.08);
		locationTax.put(LAWRENCE_KS_TAG, 0.07);
		locationTax.put(LEAVENWORTH_KS_TAG, 0.075);
		locationTax.put(MANHATTAN_KS_TAG, 0.07);
		locationTax.put(PLATTECITY_MO_TAG, 0.065);
		locationTax.put(STJOSEPH_MO_TAG, 0.085);
		locationTax.put(TOPEKA_KS_TAG, 0.085);
		locationTax.put(WARRENSBURG_MO_TAG, 0.07);
		
		// Initializing State License Fees
		stateLicenseFees.put(KANSAS_TAG, 2.0);
		stateLicenseFees.put(MISSOURI_TAG, 1.5);
	}
	
	public PriceManager (Map <String, Object> params) {
		contractDetails = new HashMap <String, Object>(params);
		Log.d("Details from Contract", contractDetails.toString());
	}
	
	public double getContractPrice() {
		double total = 0.0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String car_type = (String) contractDetails.get(CAR_TYPE_TAG);
		Log.d("Car type: ", car_type);
		
		try {
			// Getting start and end date of reservation
			Date start_date =  dateFormat.parse((String) contractDetails.get(START_DATE_TAG));
			Date end_date = dateFormat.parse((String) contractDetails.get(END_DATE_TAG));
			
			Log.d("Start Date to End Date", (String) contractDetails.get(START_DATE_TAG) + "to"
										  + (String) contractDetails.get(END_DATE_TAG));
			
			// Getting number of days between start and end date
			long time_diff = end_date.getTime() - start_date.getTime();
			long days_diff = TimeUnit.DAYS.convert(time_diff, TimeUnit.MILLISECONDS);
			
			Log.d("Number of days between", String.valueOf(days_diff));
			
			// Getting number of weeks and number of remaining days
			double num_weeks = Math.floor(days_diff/7);
			double num_days = days_diff%7;
			
			// Getting prices for number of weeks and number of days
			total += (num_weeks * (vehiclePrices.get(car_type)).get(WEEK_TAG));
			total += (num_days * (vehiclePrices.get(car_type)).get(DAY_TAG));

			long all_time_diff = end_date.getTime() - start_date.getTime();
			
			// Getting late/early checked in prices
			String checkin_date_string = (String) contractDetails.get(CHECKIN_DATE_TAG);
			if (!checkin_date_string.equals("null") && !checkin_date_string.equals(null) && !checkin_date_string.isEmpty()) {
				Date checkin_date = dateFormat.parse(checkin_date_string);
				
				time_diff = checkin_date.getTime() - end_date.getTime();
				days_diff = TimeUnit.DAYS.convert(time_diff, TimeUnit.MILLISECONDS);
				
				total += days_diff * vehiclePrices.get(car_type).get(DAY_TAG);
				
				all_time_diff = checkin_date.getTime() - start_date.getTime();
			}
			
			Log.d("Total after checkin", String.valueOf(total));
			
			long all_days_diff = TimeUnit.DAYS.convert(all_time_diff, TimeUnit.MILLISECONDS);
			
			// Getting GPS cost
			if (Integer.valueOf((String) contractDetails.get(GPS_TAG)) > 0) {
				total += (all_days_diff * additionalPrices.get(GPS_TAG)); 
			}
			
			// Getting Child Seats cost
			int child_seats = Integer.valueOf((String) contractDetails.get(CHILD_SEATS_TAG)); 
			if (child_seats > 0) {
				total += (all_days_diff * child_seats * additionalPrices.get(CHILD_SEATS_TAG));
			}
			
			// Getting K-TAG Rental cost
			if (Integer.valueOf((String) contractDetails.get(KTAG_TAG)) > 0) {
				total += (all_days_diff * additionalPrices.get(KTAG_TAG));
			}
			
			// Getting Roadside Assistance cost
			if (Integer.valueOf((String) contractDetails.get(ASSISTANCE_TAG)) > 0) {
				total += (all_days_diff * additionalPrices.get(ASSISTANCE_TAG));
			}
			
			// Getting Loss Damage Waiver Insurance cost
			if (Integer.valueOf((String) contractDetails.get(DINSURANCE_TAG)) > 0) {
				total += (all_days_diff * additionalPrices.get(DINSURANCE_TAG));
			}
			
			// Getting Personal Accident Insurance cost
			if (Integer.valueOf((String) contractDetails.get(AINSURANCE_TAG)) > 0) {
				total += (all_days_diff * additionalPrices.get(AINSURANCE_TAG));
			}
			
			// Getting State License Fees
			Double state_fees = locationTax.get(contractDetails.get(STATE_TAG));
			if (state_fees == null) {
				state_fees = 0.0;
			}
			total += (state_fees * all_days_diff);
			
			// Getting Sales tax
			Double sales_tax = stateLicenseFees.get(contractDetails.get(CITY_TAG) + ", "
												  + contractDetails.get(STATE_TAG));
			if (sales_tax != null) {
				total += (total * sales_tax);
			}
		}
		catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		return total;
	}
}
