package gui;

import java.util.ArrayList;
import javax.swing.*;

public class AnimatorPanel extends JPanel implements Runnable{

	private static final int SLEEP_TIME = 150;
	private ArrayList<Drawable> elementsToDraw = new ArrayList<Drawable>();
	private boolean animatorStopped = true, firstTime = true;

	public AnimatorPanel() {
	}

	public void setVisible(boolean display) {
		if (display == true) {
			if (firstTime) {
				firstTime = false;
				// Show the animator.  This starts the GUI thread.
				//animFrame.setVisible(true);
				//this.setOpaque(false);
				// Put the animator in another thread so that the
				// calling object can continue.
				(new Thread(this)).start();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.out.println("Animator Interrupted");
			}
			refreshElements();
		}
	}

	private void refreshElements() {
		for(Drawable d: elementsToDraw) {
			d.draw();
		}
	} 

	public void addDrawable(Drawable d) {
		elementsToDraw.add(d);
		add(((JLabel) d));
	}

	public void removeDrawable(Drawable d) {
		elementsToDraw.remove(d);
		remove(((JLabel) d));
	}

}
