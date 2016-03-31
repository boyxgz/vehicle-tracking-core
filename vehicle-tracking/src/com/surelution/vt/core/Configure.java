/**
 * 
 */
package com.surelution.vt.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:guangzong.syu@gmail.com">Guangzong</a>
 *
 */
public class Configure {
	
	private static Configure instance = new Configure();
	
	private static List<String> classes;

	public Iterable<String> getProcessorNames() {
		Iterable<String> iter = new Iterable<String>() {
			
			@Override
			public Iterator<String> iterator() {
				return classes.iterator();
			}
		};
		return iter;
	}
	
	private Configure() {
		try{
			try(InputStream is = getClass().getClassLoader().getResourceAsStream("actions.txt")) {

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();
				while(line != null) {
					System.out.println(line);
					if(classes == null) {
						classes = new ArrayList<>();
					}
					classes.add(line);
					line = br.readLine();
					System.out.println(line);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Configure getInstance() {
		return instance;
	}
}
