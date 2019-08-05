package guitarHero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

public class SyncEventTimer
{
	private Queue<ActionListener> events;
	private Queue<Long> delays;
	
	private int resolution;
	
	private Timer timer;
	private long lastTime;
	private long now;
	private long delta;
	
	//
	
	SyncEventTimer()
	{
		events = new LinkedList<ActionListener>();
		delays = new LinkedList<Long>();
		
		resolution = 1;
		
		lastTime = System.nanoTime();
		delta = 0;
		
		initTimer();
	}
	
	private void initTimer()
	{
		timer = new Timer(resolution, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				now = System.nanoTime();
				delta += now - lastTime;
				lastTime = now;
				
				while(!delays.isEmpty() && delta >= delays.peek())
				{
					events.poll().actionPerformed(e);;
					
					delta -= (delays.poll());
				}
			}
		});
	}
	
	//
	
	public void addEvent(ActionListener e, long delay)
	{
		events.add(e);
		delays.add(delay);
	}
	
	//
	
	public void start()
	{
		lastTime = System.nanoTime();
		delta = 0;
		
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
	}
}
