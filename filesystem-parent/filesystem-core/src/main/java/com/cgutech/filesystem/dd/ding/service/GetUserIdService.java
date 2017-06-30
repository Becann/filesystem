package com.cgutech.filesystem.dd.ding.service;

import com.cgutech.filesystem.dd.ding.bean.UserId;

public interface GetUserIdService {
	public UserId requestUserId(String access_token, String code);
	public String getUserId(String access_token, String code);
}
