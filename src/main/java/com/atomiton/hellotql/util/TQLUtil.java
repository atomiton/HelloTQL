/**
 * Copyright © 2015 Atomiton, Inc. All rights reserved.

	This file, or any software and its documentation downloaded from 
	Atomiton website tql.atomiton.com (hereinafter referred as “Software”) 
	is copyright protected work of Atomiton, inc. 
	You may not use, copy, modify or 
	distribute Software without an End User License Agreement (hereinafter referred as “EULA”). 
	Use of Software must be restrained by the clauses in the EULA. 
	You may obtain a copy of the EULA at:
	http://www.atomiton.com
	Unless you agree with the clauses set out in the EULA, you may not install or 
	use any Atomiton Software.
 */
package com.atomiton.hellotql.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;


import org.apache.commons.io.FileUtils;


/**
 * This class provides generic utility functions; not already provided by OdaLib.
 * OdaLib is used whenever appropriate.
 * @author baseerkhan
 *
 */
public class TQLUtil {

	public static int getRandomNumberBetween(int low, int high) {
		Random random = new Random();
		int randomNumber = random.nextInt(high - low) + low;
		return randomNumber;
	}


	public static String readContentsFromFile(String fName) throws Exception {
		File f = new File (fName);
		String strFileContent = FileUtils.readFileToString(f);
		return strFileContent;
	}

	public static void writeContentToFile(String folder, String content, String fName) 
			throws Exception {
		File f = new File (folder);
		if (!f.exists()) {
			f.mkdirs();
		}
		File f2 = new File (folder + "/" + fName);
		FileUtils.writeStringToFile(f2, content, false);
	}

	public static void writeContentToFile(String folder, byte[] content, String fName) 
			throws Exception {
		File f = new File (folder);
		if (!f.exists()) {
			f.mkdirs();
		}
		File f2 = new File (folder + "/" + fName);
		FileUtils.writeByteArrayToFile(f2, content);
	}

	public static Date getTodaysDate() {
		Date todaysDate = null;
		try {
			Calendar currentDate = Calendar.getInstance(); //Get the current date
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-M-dd hh:mm:ss"); //format it as per your requirement
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			String dateNow = formatter.format(currentDate.getTime());
			todaysDate = formatter.parse(dateNow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todaysDate;
	}

	public static Long toUTC(Date cDate) {
		long milliSecondsSinceEpoch = cDate.getTime();
		return new Long(milliSecondsSinceEpoch);
	}

	public static long getUTCTime() {
		return toUTC(getTodaysDate()).longValue();
	}


	public static boolean isNull(String str) {
		return (str == null || str.length() == 0);
	}


	/**
	 * <Query>
			<Find>
				<ParkingSpace>
  					<sid Ne=""/>
				</ParkingSpace>
			</Find>
		</Query>
	 */
	public static String getAllParkingSpaces() throws Exception  {
		StringBuffer sb = new StringBuffer("<Query>");
		sb.append("<Find><ParkingSpace><sid ne=\"\"/>");
		sb.append("</ParkingSpace></Find></Query>");
		return HttpRequestResponseHandler.sendPost(TQLConstants.TQL_INTERFACE_URL, sb.toString());
	}


}
