import java.util.*;
import java.lang.String;
import java.io.*;


public class P1 {

	/* Define data structures for holding the data here */

	LinkedList<LinkedList<String>> table_coach = new LinkedList<LinkedList<String>>();
	LinkedList<LinkedList<String>> table_team = new LinkedList<LinkedList<String>>();


	public P1() {
		/* initialize the data structures */

	}

	public void add_coach(String [] Parameters){
		boolean condition = true;
		LinkedList<String> row = new LinkedList<String>();


		if (Parameters[0].length() > 9){
			System.out.println("Coach_ID too long");
			condition = false;

		}    			

		int season = Integer.parseInt(Parameters[1]);
		if(season < 0 || season > 9999){
			System.out.println("Season Year is out of range");
			condition = false;
		}

		int season_win = Integer.parseInt(Parameters[4]);
		if(season_win < 0){
			System.out.println("Must be a positive number");
			condition = false;
		}

		int season_loss = Integer.parseInt(Parameters[5]);
		if(season_loss < 0){
			System.out.println("Must be a positive number");
			condition = false;
		}

		int playoff_win = Integer.parseInt(Parameters[6]);
		if(playoff_win < 0){
			System.out.println("Must be a positive number");
			condition = false;
		}

		int playoff_loss = Integer.parseInt(Parameters[7]);
		if(playoff_loss < 0){
			System.out.println("Must be a positive number");
			condition = false;
		}

		if (condition == true)
		{
			row.add(Parameters[0]);
			row.add(Parameters[1]);
			row.add(Parameters[2]);
			row.add(Parameters[3]);
			row.add(Parameters[4]);
			row.add(Parameters[5]);
			row.add(Parameters[6]);
			row.add(Parameters[7]);
			row.add(Parameters[8].toUpperCase());
		}

		table_coach.add(row);
	}

	public void add_team(String[] Parameters){
		boolean condition = true;
		LinkedList<String> row = new LinkedList<String>();

		if(Parameters.length == 5){
			if (Parameters[4].length() > 1)
			{
				System.out.println("League is one capital letter input");
				condition = false;
			}
			if(condition == true){
				row.add(Parameters[0]);
				row.add(Parameters[1] + Parameters[2]);			
				row.add(Parameters[3]);
				row.add(Parameters[4].toUpperCase());
			}
		}
		else{
			if (Parameters[3].length() > 1){
				System.out.println("League is one capital letter input");
				condition = false;
			}
			if(condition == true){
				row.add(Parameters[1]);
				row.add(Parameters[2]);
				row.add(Parameters[3].toUpperCase());
			}
		}

		table_team.add(row);

	}

	@SuppressWarnings("unchecked")
	public void load_coaches(String[] Parameters){

		try{
			LinkedList<String> row = new LinkedList<String>();
			BufferedReader reader = new BufferedReader( new InputStreamReader (P1.class.getResourceAsStream(Parameters[0])) );

			String temp;
			while( (temp = reader.readLine()) != null){
				String[] str = temp.split(",");

				for(int i = 0; i < str.length; i++){
					row.add(str[i]);				
				}
				table_coach.add((LinkedList<String>)row.clone());
				row.clear();
			}
			reader.close();
		}
		catch(IOException e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void load_teams(String[] Parameters){

		try{
			LinkedList<String> row = new LinkedList<String>();
			BufferedReader in = new BufferedReader( new InputStreamReader (P1.class.getResourceAsStream(Parameters[0])) );

			String temp;
			while( (temp = in.readLine()) != null){
				String[] str = temp.split(",");

				for(int i = 0; i < str.length; i++){
					row.add(str[i]);				
				}
				table_team.add((LinkedList<String>)row.clone());
				row.clear();
			}
			in.close();
		}
		catch(IOException e){
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void print_coaches(String[] Parameters){
		Iterator<LinkedList<String>> coaches = table_coach.iterator();
		while(coaches.hasNext())
		{
			LinkedList<String> coach = coaches.next();
			Iterator<String> data = coach.iterator();
			while(data.hasNext())
			{
				System.out.print(data.next() + " ");
			}
			System.out.println();
		}
	}

	//this function will search the table_coach
	public void search(String Parameters, LinkedList<LinkedList<String>> list, int column){
		Iterator<LinkedList<String>> iterator = list.iterator();
		while(iterator.hasNext()){
			search_row(Parameters, iterator.next(), column);
		}
	}

	//this function will search the rows
	public void search_row(String Parameters, LinkedList <String> list, int column){
		Iterator <String> iterator = list.iterator();
		while(list.get(column) == Parameters && iterator.hasNext()){
			System.out.print(iterator.next() + '\t');
		}
	}

	//Delete table; row by row; after finding the match
	public void delete(String Parameters, LinkedList<LinkedList<String>> list, int column){
		Iterator<LinkedList<String>> iterator = list.iterator();
		while(iterator.hasNext()){
			delete_row(Parameters, iterator.next(), column);
		}
	}

	//Delete row; used by delete(); under the hood function
	public void delete_row(String Parameters, LinkedList <String> list, int column){
		Iterator <String> iterator = list.iterator();
		while(iterator.hasNext() && list.get(column)== Parameters){
			if(iterator.next() == Parameters){
				list.clear();
			}			
		}
	}

	public void print_teams(String[] Parameters){
		Iterator<LinkedList<String>> teams = table_team.iterator();
		while(teams.hasNext())
		{
			LinkedList<String> team = teams.next();
			Iterator<String> data = team.iterator();
			while(data.hasNext())
			{
				System.out.print(data.next() + " ");
			}
			System.out.println();

		}
	}

	public void coaches_by_name(String[] Parameters){
		//need code to join String[] 
		//then pass it as Parameters[0]or whatever

		search(Parameters[0], table_coach, 3);
	}


	public void teams_by_city(String[] Parameters){
		search(Parameters[0], table_team, 3);
	}

	public void best_coach(String[] Parameters){
	}

	public void search_coaches(String[] Parameters){
		int i = 0;
		for(int j = 0; j < Parameters.length; j++){
			int ind = Parameters[j].indexOf('=')+1;

			if(Parameters[j].substring(0, ind).equalsIgnoreCase("ID"))
				i = 0;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season"))
				i = 1;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("first_name"))
				i = 2;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("last_name"))
				i = 3;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season_win"))
				i = 4;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season_loss"))
				i = 5;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("playoff_win"))
				i = 6;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("Playoff_loss"))
				i = 7;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("team"))
				i = 8;

			search(Parameters[i].substring(ind), table_coach, i);
		}
	}

	public void delete_coaches(String[] Parameters){
		int i = 0;
		for(int j = 0; j < Parameters.length; j++){
			int ind = Parameters[j].indexOf('=');

			if(Parameters[j].substring(0, ind).equalsIgnoreCase("ID"))
				i = 0;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season"))
				i = 1;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("first_name"))
				i = 2;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("last_name"))
				i = 3;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season_win"))
				i = 4;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("season_loss"))
				i = 5;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("playoff_win"))
				i = 6;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("Playoff_loss"))
				i = 7;
			if(Parameters[j].substring(0, ind).equalsIgnoreCase("team"))
				i = 8;

			delete(Parameters[0].substring(ind), table_coach, i);
		}
	}


	public void run() {
		CommandParser parser = new CommandParser();

		System.out.println("The mini-DB of NBA coaches and teams");
		System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
		System.out.println();
		System.out.print("> "); 

		Command cmd = null;
		while ((cmd = parser.fetchCommand()) != null) {
			//System.out.println(cmd);

			boolean result=false;

			if (cmd.getCommand().equals("help")) {
				result = doHelp();

				/* You need to implement all the following commands */
			} else if (cmd.getCommand().equals("add_coach")) {
				add_coach(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("add_team")) {
				add_team(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("print_coaches")) {
				print_coaches(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("print_teams")) {
				print_teams(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("coaches_by_name")) {
				coaches_by_name(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("teams_by_city")) {
				teams_by_city(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("load_coaches")) {
				load_coaches(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("load_teams")) {
				load_teams(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("best_coach")) {

			} else if (cmd.getCommand().equals("search_coaches")) {
				search_coaches(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("delete_coaches")) {
				delete_coaches(cmd.getParameters());//done
			} else if (cmd.getCommand().equals("exit")) {
				System.out.println("Leaving the database, goodbye!");
				break;
			} else if (cmd.getCommand().equals("")) {
			} else {
				System.out.println("Invalid Command, try again!");
			} 

			if (result) {
				// ...
			}

			System.out.print("> "); 
		}        
	}

	private boolean doHelp() {
		System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN "); 
		System.out.println("          EASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
		System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
		System.out.println("print_coaches - print a listing of all coaches");
		System.out.println("print_teams - print a listing of all teams");
		System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
		System.out.println("teams_by_city CITY - list the teams in the specified city");
		System.out.println("load_coach FILENAME - bulk load of coach info from a file");
		System.out.println("load_team FILENAME - bulk load of team info from a file");
		System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
		System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
		System.out.println("delete_coaches field=VALUE - delete the coach satisfying the specified conditions");
		System.out.println("exit - quit the program");        
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new P1().run();
	}



}
