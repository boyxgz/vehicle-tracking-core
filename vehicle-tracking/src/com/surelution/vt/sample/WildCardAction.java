package com.surelution.vt.sample;

import com.surelution.vt.core.BaseAction;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class WildCardAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.println(getMessage());
		return null;
	}

	@Override
	public boolean accept() {
		return true;
	}

}
