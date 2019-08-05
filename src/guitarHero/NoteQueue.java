package guitarHero;

import java.util.LinkedList;
import java.util.Queue;

public class NoteQueue {
	
	private Queue<Integer> notes;
	private Queue<Double> delays;
	
	public NoteQueue()
	{
		notes = new LinkedList<Integer>();
		delays = new LinkedList<Double>();
	}
	
	public void add(Integer note, Double delay)
	{
		notes.add(note);
		delays.add(delay);
	}
	
	public boolean isEmpty()
	{
		if (notes.size() == 0 || delays.size() == 0)
		{
			return true;
		}
		else return false;
	}
	
	public void clear()
	{
		notes = new LinkedList<Integer>();
		delays = new LinkedList<Double>();
	}
	
	//The notes are stored in a double queue, 1 for the note color, and the other for
	//the delay from the last note.
	public int remove()
	{
		return dispense();
	}
	
	public double getDelay()
	{
		return delays.poll();
	}
	
	private Integer dispense()
	{
		//Thread.sleep((int) (delay.doubleValue()*1000),(((int)(delay.doubleValue()*10000 % 10))));
		//ABOVE determines how long the thread needs to wait before dispensing the note
		return notes.poll();
	}
	
}
