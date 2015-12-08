package tools;

public class StringAmend {

	public static String removeMin(String withMin)
	{
		int when = withMin.indexOf('m');
		String test;
		test = withMin.substring(0, (when - 1));
		return test;
	}
	
	public static void main(String[] args)
	{
	}
}
