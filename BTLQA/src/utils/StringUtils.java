package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static Integer convertToInteger(String str) {
		try {
			Integer integer = new Integer(str);
			return integer;
		} catch (Exception e) {
			return null;
		}

	}

	public static Long convertToLong(String str) {
		try {
			Long long1 = new Long(str);
			return long1;
		} catch (Exception e) {
			return null;
		}

	}

	public static Float convertToFloat(String str) {
		try {
			Float float1 = new Float(str);
			return float1;
		} catch (Exception e) {
			return null;
		}

	}

	public static Double convertToDouble(String str) {
		try {
			Double double1 = new Double(str);
			return double1;
		} catch (Exception e) {
			return null;
		}

	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Date convertToDate(String str) {
		try {
			return formatter.parse(str);
		}catch (Exception e) {
			return null;
		}
	}
	public static Date convertToDate(String str, String format) {
		try {
			SimpleDateFormat formatter2 = new SimpleDateFormat(format);
			return formatter2.parse(str);
		}catch (Exception e) {
			return null;
		}
	}
}
