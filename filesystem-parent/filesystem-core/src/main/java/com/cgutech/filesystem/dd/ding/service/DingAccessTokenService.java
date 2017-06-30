package com.cgutech.filesystem.dd.ding.service;

import com.cgutech.filesystem.dd.ding.bean.DingAccessToken;

public interface DingAccessTokenService {
	public String getAccessToken();
	
	public DingAccessToken requestAccessToken();
	
}
