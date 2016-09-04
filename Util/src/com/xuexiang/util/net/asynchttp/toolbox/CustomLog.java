package com.xuexiang.util.net.asynchttp.toolbox;

import java.util.Locale;

import android.util.Log;

public class CustomLog {
	public static boolean debug = true;
	public final static String TAG = "Android-Async-Http";
	
	public static void i(String tag, String msg) {
		if (debug)
			Log.i(tag, msg);
	}

	public static void v(String format, Object... args) {
		if (debug) {
			Log.v(TAG, buildMessage(format, args));
		}
	}

	public static void d(String format, Object... args) {
		if (debug) {
			Log.d(TAG, buildMessage(format, args));
		}
	}

	public static void e(String format, Object... args) {
		if (debug) {
			Log.e(TAG, buildMessage(format, args));
		}
	}

	/**
	 * Formats the caller's provided message and prepends useful info like
	 * calling thread ID and method name.
	 */
	private static String buildMessage(String format, Object... args) {
		String msg = (args == null) ? format : String.format(Locale.US, format, args);
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

		String caller = "<unknown>";
		// Walk up the stack looking for the first caller outside of VolleyLog.
		// It will be at least two frames up, so start there.
		for (int i = 2; i < trace.length; i++) {
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(CustomLog.class)) {
				String callingClass = trace[i].getClassName();
				callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
				callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

				caller = callingClass + "." + trace[i].getMethodName();
				break;
			}
		}
		return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
	}
}
