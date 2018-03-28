package com.panhong.dao;

import java.util.List;

import com.panhong.model.NB.Command;

public interface WelllidDao {

	/**
	 * 更新指令
	 * @param type
	 * @param num
	 * @param s
	 * @param sHex
	 */
	public void updateCommand(int type, int num, String s, String sHex);

	/**
	 * 更新指令名字
	 * @param type
	 * @param num
	 * @param name
	 */
	public void updateCommandName(int type, int num, String name);

	public List<Command> getCommandInfo();

	public Command getCommandById(int id);

	public void updateDelayTime(int delayTime);

	public int getDelayTime();

}
