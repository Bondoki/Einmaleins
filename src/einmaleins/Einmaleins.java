package einmaleins;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Einmaleins {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Hallo Fabi,");
		System.out.println("na dann lass uns mal das 1*1 üben.");
		System.out.println();
		System.out.println("Wenn du soweit bist, dann einfach \"Enter\" drücken.");
		
		Scanner sc = new Scanner(System.in);
	    sc.nextLine();
	    
	    // generate list of all tuples 
	    ArrayList< Tuple<Integer,Integer> > list = new ArrayList<>(); 
	    
	    // generate list of wrong answers
	    ArrayList< Tuple<Integer,Integer> > list_answer_wrong = new ArrayList<>(); 
	    ArrayList< Tuple<Integer,Integer> > list_answer_1stwrong = new ArrayList<>(); 
	    ArrayList< Tuple<Integer,Integer> > list_answer_2ndwrong = new ArrayList<>(); 
	    
	    // ommiting Ones and Tenth  
	    for(int i = 2; i <= 9; i++)
	    	for(int j = i; j <= 9; j++)
	    		list.add( new Tuple<Integer,Integer>(i,j) ); 
	    
	    //System.out.println(list.size());
	    
	    Random rand = new Random();
	    
	    int answer_right = 0;
	    int answer_wrong = 0;
	    
	    long startTime = System.currentTimeMillis();
	    
	    while(list.size() != 0)
	    {
	    	int rnd_idx = rand.nextInt(list.size());
	    	
	    	int x = 0;
	    	int y = 0;
	    	// permute x and y 
	    	if(rand.nextInt(2)%2 == 0)
	    	{
	    		x = list.get(rnd_idx).getX();
		    	y = list.get(rnd_idx).getY();
	    	}
	    	else
	    	{
	    		y = list.get(rnd_idx).getX();
		    	x = list.get(rnd_idx).getY();
	    	}
	    	
	    	int result = x*y;
	    	
	    	int try_input = 0;
	    	
	    	do{
	    		try_input ++;
	    		System.out.println();
	    		System.out.println("Was ergibt " + x + " * " + y + " ?");

	    		int result_input = 0;
	    		
	    		try {
	    			result_input = sc.nextInt();
	    		}catch (InputMismatchException exception) {
	    			// Output unexpected InputMismatchExceptions.
	    			// in the worst case we exit the program
	    			if(sc.next().compareToIgnoreCase("exit") == 0)
	    			{
	    				System.exit(1);
	    			}
	    			
	    			try_input--;
	    			System.out.println("Die Eingabe verstehe ich nicht. Nochmal bitte.");
	    			
	    			continue;
	    		}

	    		
	    		if(result_input != result)
	    		{
	    			if(try_input == 1)
	    			{
	    				list_answer_1stwrong.add( new Tuple<Integer,Integer>(list.get(rnd_idx).getX(),list.get(rnd_idx).getY()) ); 
	    				System.out.println("Das ist leider nicht richtig. Versuch's nochmal.");
	    			}
	    			else if(try_input == 2)
	    			{
	    				list_answer_2ndwrong.add( new Tuple<Integer,Integer>(list.get(rnd_idx).getX(),list.get(rnd_idx).getY()) ); 
	    				System.out.println("Das ist leider nicht richtig. Versuch's nochmal.");
	    			}
	    			else if(try_input == 3)
	    			{
	    				System.out.println("Antwort: " + x + " * " + y + " = " + result);
	    				answer_wrong++;
	    				list_answer_wrong.add( new Tuple<Integer,Integer>(list.get(rnd_idx).getX(),list.get(rnd_idx).getY()) ); 
	    			}
	    			//else
	    			//	System.out.println("Das ist leider nicht richtig. Versuch's nochmal.");
	    		}
	    		else 
	    		{
	    			System.out.println("Richtig! " + x + " * " + y + " = " + result);
	    			answer_right ++;
	    			break;
	    		}
	    			
	    		
	    	}while(try_input < 3); // three tries 

	    	// remove object from list
	    	list.remove(rnd_idx);
	    }
	    
	    long estimatedTime = System.currentTimeMillis() - startTime;
	    
	    System.out.println();
	    System.out.println("Besten Dank. Das waren soweit alle. Gut gemacht.");
	    
	    // print and save the time
	    System.out.println("Das Programm lief " + (estimatedTime/1000.0) + " s. ");
	    
	    // get the date
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy/MM/dd/HH:mm:ss");
    	Date today = Calendar.getInstance().getTime();        
    	String reportDate = df.format(today);
    	// write the date, run time, answer right, answer wrong
	    try{
	    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Training.txt", true)));
	        
	    	out.println(reportDate + "\t" + (estimatedTime/1000.0) + "\t" + answer_right + "\t" + answer_wrong);
	    	out.close();
	    	
	    	} catch (IOException e) {
	    	    //exception handling left as an exercise for the reader
	    	}
	    
	    // write the wrong answers
	    try{
	    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Training_"+reportDate+".txt", true)));
	    	out.println(reportDate + "\t" + (estimatedTime/1000.0) + "\t" + answer_right + "\t" + answer_wrong);
	    	
	    	//3times wrong answers
	    	{
	    		out.println();
	    		out.println("3 times wrong answers");
	    		Iterator<Tuple<Integer,Integer> > crunchyIterator = list_answer_wrong.iterator();
	    		while (crunchyIterator.hasNext()) {
	    			Tuple<Integer,Integer> tuple = crunchyIterator.next();
	    			out.println(tuple.getX() + " " + tuple.getY());
			}
	    	}
	    	
	    	{
	    		out.println();
	    		out.println("2 times wrong answers");
	    		Iterator<Tuple<Integer,Integer> > crunchyIterator = list_answer_2ndwrong.iterator();
	    		while (crunchyIterator.hasNext()) {
	    			Tuple<Integer,Integer> tuple = crunchyIterator.next();
	    			if(!list_answer_wrong.contains(tuple))
	    				out.println(tuple.getX() + " " + tuple.getY());
			}
	    	}
	    	
	    	{
	    		out.println();
	    		out.println("1 time wrong answers");
	    		Iterator<Tuple<Integer,Integer> > crunchyIterator = list_answer_1stwrong.iterator();
	    		while (crunchyIterator.hasNext()) {
	    			Tuple<Integer,Integer> tuple = crunchyIterator.next();
	    			
	    			if(!list_answer_2ndwrong.contains(tuple))
	    				out.println(tuple.getX() + " " + tuple.getY());
			}
	    	}
	    	
			out.close();
	    	
	    	} catch (IOException e) {
	    		System.out.println("Exception oocured!");
	    	    //exception handling left as an exercise for the reader
	    	}
	    
	    
	}

}


