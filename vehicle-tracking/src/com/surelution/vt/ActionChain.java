package com.surelution.vt;

import java.util.ArrayList;

public class ActionChain {

	private static ActionChain instance = new ActionChain(Main.class.getClassLoader());
	private ClassLoader classLoader;

	private ArrayList<Class<BaseAction>> processors = new ArrayList<Class<BaseAction>>();

	private ActionChain(ClassLoader classLoader) {
		if(this.classLoader == null) {
			this.classLoader = getClass().getClassLoader();
		}
		Configure config = Configure.getInstance();
		for(String processorName : config.getProcessorNames()) {
			System.out.println(processorName);
			if(processorName != null) {
				try {
					@SuppressWarnings("unchecked")
					Class<BaseAction> c = (Class<BaseAction>)this.classLoader.loadClass(processorName);
					processors.add(c);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("load chain");
	}

	public int[] getContent(int[] inputNums) {
		for(Class<BaseAction> cProcessor : processors) {
			//TODO how to handle ?
			BaseAction processor = null;
			try {
				processor = cProcessor.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			System.out.print("try ");
			System.out.println(processor.getClass().getName());
			processor.feed(inputNums);
			boolean accept = false;
			try{
				accept = processor.accept();
			} catch(Exception e){}
			if(accept) {
				System.out.print("processor ");
				System.out.print(processor.getClass().getName());
				System.out.println(" goal....");
				return processor.execute();
			}
		}

		return null;
	}

	public static synchronized ActionChain getInstance(ClassLoader classLoader) {
		return instance;
	}

}
