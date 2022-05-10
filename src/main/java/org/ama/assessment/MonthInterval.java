package org.ama.assessment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MonthInterval {

	public static final String MONTH_FORMAT = "MMMM";

	public static String[] generateMonthsInInterval(LocalDate startDate, LocalDate endDate) {
		
		String[] monthNames = null;
		List<String> monthNameList = generateMonthsList(startDate, endDate);
		sortMonthNames(monthNameList);
		monthNames = monthNameList.toArray(new String[0]);
		return monthNames;
	}
												
	private static List<String> generateMonthsList(LocalDate startDate, LocalDate endDate) {
		List<String> monthNameList = new ArrayList<>();
		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MONTH_FORMAT);
			String name = startDate.format(formatter);
			startDate = startDate.plusMonths(1);
			if (!monthNameList.contains(name))
				monthNameList.add(name);
		}
		return monthNameList;
	}
	
	private static void sortMonthNames(List<String> monthNameList){
		Collections.sort(monthNameList, new Comparator<String>() {
			public int compare(String o1, String o2) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat(MONTH_FORMAT);
					return sdf.parse(o1).compareTo(sdf.parse(o2));
				} catch (ParseException ex) {
					return o1.compareTo(o2);
				}
			}
		});
	}

}
