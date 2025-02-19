package com.lb.lbint;

public class CurrencyCalc {
	
	public static float currencyConversion(SoapServiceCaller soap, DBManager db, String curName, String conName, float curValue) {
		float curRate = 1;
		float conRate = 0;
		try {
			conRate = soap.getCurrencyRate(conName.toUpperCase());
			curRate = soap.getCurrencyRate(curName.toUpperCase());
		} catch (NullPointerException e) {
			System.out.println("Neįmanoma valiutos konvertacija");
			return 0;
		}
		float rate = conRate/curRate;
		db.logConversion(curName, conName, curValue, rate, curValue * rate);
		return curValue * rate;
	}
	
	public static void printCurrencyConversion(SoapServiceCaller soap, DBManager db, String curName, String conName, float curValue) {
		float rez = currencyConversion(soap, db, curName, conName, curValue);
		if(rez != 0) {
			System.out.println(rez + " " + conName);
		}
	}

}
