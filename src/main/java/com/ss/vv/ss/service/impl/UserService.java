package com.ss.vv.ss.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.mapper.IUserMapper;
import com.ss.vv.ss.service.IUserService;

@Service("userService")
public class UserService extends AbstractService<User, User> implements IUserService {
	/*设置表名叫user_form*/
	public UserService() {
		this.setTableName("user_form");
	}
	@Resource
	private IUserMapper userMapper;

	@Override
	protected IOperations<User, User> getMapper() {
		return userMapper;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName=tableName;
	}

	
	public User getOtherById(int uId)
	{
		User user = userMapper.getOtherById(uId);
		return user;
	}
	public int Update(User user){
        return userMapper.Update(user);
    }   
	@Override
	public User getByName(String uname, String upassword) {
		User user =  userMapper.getByName(uname, upassword);
		return user;
	}
	@Override
	public int deleteByNP(String name) {
		int del = userMapper.deleteByNP(name);
		return del;
	}

	@Override
	public User login(String uname, String upassword) {
		User user=userMapper.login(uname, upassword);
		return user;
	}

}
