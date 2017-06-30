package com.cgutech.filesystem.utils;

import org.junit.Test;

public class TeaUtilTest {
	
	public static void main(String[] args) {
		String corpid=ReadProperties.read("corpsecret");
		String encorpid=TeaUtil.encrypt(corpid);
		System.out.println(encorpid);
		String decorpid=TeaUtil.decrypt(encorpid);
		System.out.println(decorpid.equals(corpid));
	}
	@Test
	public void tea(){
		
	}
}

