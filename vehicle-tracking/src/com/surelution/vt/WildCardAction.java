package com.surelution.vt;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class WildCardAction extends BaseAction {

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
