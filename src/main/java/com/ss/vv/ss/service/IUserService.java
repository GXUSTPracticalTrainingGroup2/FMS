package com.ss.vv.ss.service;

import com.ss.vv.common.IServiceOperations;
import com.ss.vv.ss.domain.User;


public interface IUserService extends IServiceOperations<User, User> {
	public User getOtherById(int uId);

	public int Update(User user);

	public int deleteByNP(String uname);

	public User getByName(String uname, String upassword);
	
	public User login(String uname,String upassword);
	
	
}
