package com.surelution.vt;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class CompositeUploadAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.println(this);
		return null;
	}

	@Override
	public boolean accept() {
		return getCmdType() == 0x20 && getCmdId() == 0x84;
	}

}
