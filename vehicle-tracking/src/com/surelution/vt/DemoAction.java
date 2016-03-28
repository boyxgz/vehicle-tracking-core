package com.surelution.vt;

public class DemoAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.println(this);
		return null;
	}

	@Override
	public boolean accept() {
		return true;
	}

}
