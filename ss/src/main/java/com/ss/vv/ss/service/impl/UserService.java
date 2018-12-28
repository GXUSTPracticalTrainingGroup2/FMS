package com.ss.vv.ss.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.mapper.ITestMapper;
import com.ss.vv.ss.mapper.IUserMapper;
import com.ss.vv.ss.service.IUserService;
import com.ss.vv.ss.vo.UserVo;

@Service("userService")
public class UserService extends AbstractService<User, UserVo> implements IUserService {
	/*设置表名叫uer_form*/
	public UserService() {
		this.setTableName("user_form");
	}
	@Resource
	private IUserMapper userMapper;

	@Override
	protected IOperations<User, UserVo> getMapper() {
		return userMapper;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName=tableName;
		
	}

}
