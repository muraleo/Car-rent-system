package car_rent_system.view;

import java.util.Scanner;

public class View {
	private static final String CONTEXT="" +
			"Welcome to Leo's car rent\n" +
			"Function List\n" +
			"*[QUERY/Q]:List all available car\n" +
			"*[GET/G]:List information about one car\n" +
			"*[ADD/A]:Add a new Car\n" +
			"*[UPDATE/U]:Edit a Car\n" +
			"*[DELETE/D]:Remove a Car\n" +
			"*[SEARCH/S]:Search a car\n" +
			"*[EXIT/E]:Exit\n" +
			"*[BREAK/B]:Back to MainMenu";

	private static final String OPERATION_MAIN="MAIN";
	private static final String OPERATION_QUERY="QUERY";
	private static final String OPERATION_GET="GET";
	private static final String OPERATION_ADD="ADD";
	private static final String OPERATION_UPDATE="UPDATE";
	private static final String OPERATION_DELETE="DELETE";
	private static final String OPERATION_SEARCH="SEARCH";
	private static final String OPERATION_EXIT="EXIT";
	private static final String OPERATION_BREAK="BREAK";
	
	public static void main(String[] args){
		System.out.println(CONTEXT);
		Scanner scan = new Scanner(System.in);
		while(true){
			String command = scan.next().toUpperCase().toString();
			if(OPERATION_EXIT.equals(command)){
				System.out.println("Bye");
				break;
			}
			System.out.println(scan.next());
		}
	}
}
