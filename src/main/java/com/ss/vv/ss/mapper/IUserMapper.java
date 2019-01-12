package com.ss.vv.ss.mapper;

import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.User;

public interface IUserMapper extends IOperations<User, User> {
	public User login(User user);
	
	public User getOtherById(int uId);

	public int Update(User user);

	public int deleteByNP(String name);

	public User getByName(String uname, String upassword);

	public User login(String uname, String upassword);
}
