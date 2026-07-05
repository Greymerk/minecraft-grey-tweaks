package com.greymerk.tweaks.util;

import java.util.Calendar;

public enum Season {
	WINTER, SPRING, SUMMER, FALL;
	
	public static Season getSeason(int month) {
		switch(month) {
		case Calendar.JANUARY: return WINTER;
		case Calendar.FEBRUARY: return WINTER;
		case Calendar.MARCH: return SPRING;
		case Calendar.APRIL: return SPRING;
		case Calendar.MAY: return SPRING;
		case Calendar.JUNE: return SUMMER;
		case Calendar.JULY: return SUMMER;
		case Calendar.AUGUST: return SUMMER;
		case Calendar.SEPTEMBER: return FALL;
		case Calendar.OCTOBER: return FALL;
		case Calendar.NOVEMBER: return FALL;
		case Calendar.DECEMBER: return WINTER;
		default: return WINTER;
		}
	}
}
