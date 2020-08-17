package main;

import core.WebEngine;
import core.other.Settings;

public class Main
{
	public static void main(String[] args)
	{
		WebEngine engine = WebEngine.create(new Settings());
		if (engine != null)
		{
			// write your code here
			engine.close();
		}
	}
}
