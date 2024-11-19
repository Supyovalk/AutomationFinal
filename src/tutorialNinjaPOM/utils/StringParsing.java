package tutorialNinjaPOM.utils;

public class StringParsing {
	public static String stripDollar(String unstripped) {
		//return unstripped.substring(0,unstripped.length() - 1);
		String finaldollar=unstripped.substring(1).replace(",", "");
		System.out.println("Stripped from Dollar:"+finaldollar);
		return finaldollar;
	}
	public static String stripCrossCount(String unstripped) {
		//return unstripped.substring(0,unstripped.length() - 1);
		String finalcount=unstripped.substring(1).replace(",", "");
		System.out.println("Stripped from CrossCount:"+finalcount);
		return finalcount;
	}
}
