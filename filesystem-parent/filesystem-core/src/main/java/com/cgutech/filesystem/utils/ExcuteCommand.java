package com.cgutech.filesystem.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;

/**
 * java执行命令行工具类
 * 
 * @author Becan E-mail:becan@cgutech.com
 * @date 2016年9月14日17:11:27
 */
public class ExcuteCommand {

	/**
	 * windows添加普通用户账户
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:14:03
	 * @param user
	 *            用户名 不超过20个字符
	 * @param pass
	 *            用户密码 最多127个字符
	 * @retrun String
	 */
	public static String addUser(String user, String pass) {
		String command = new String("cmd /c net user " + user + " " + pass + " /add");
		return excute(command);
	}
	/**
	 * windows删除用户账户
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:51:27
	 * @param user
	 *            用户名 不超过20个字符 
	 * @retrun String
	 */
	public static String deleteUser(String user) {
		String command = new String("cmd /c net user " + user+ " /delete");
		return excute(command);
	}
	
	/**
	 * windows修改用户密码
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:57:10
	 * @param user
	 * @param pass 新密码
	 * @return
	 * String
	 */
	public static String modifyUserPassword(String user,String pass) {
		String command = new String("cmd /c net user " + user+" " + pass);
		return excute(command);
	}
	
	/**
	 * windows 修改系统账号密码
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:59:39
	 * @param pass 新密码
	 * @return
	 * String
	 */
	public static String modifyAdminPassword(String pass) {
		String command = new String("cmd /c net password " + pass);
		return excute(command);
	}
	
	/**
	 * windows 激活用户
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午6:05:25
	 * @param user
	 * @return
	 * String
	 */
	public static String activeUser(String user) {
		String command = new String("cmd /c net user " + user+" /active:yes");
		return excute(command);
	}
	
	/**
	 * windows 禁用用户
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午6:05:51
	 * @param user
	 * @return
	 * String
	 */
	public static String disableUser(String user) {
		String command = new String("cmd /c net user " + user+" /active:no");
		return excute(command);
	}
	
	/**
	 * windows提升普通为管理员
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:15:58
	 * @param String
	 *            user 用户名
	 * @retrun String
	 */
	public static String toAdministrator(String user) {
		String command = new String("cmd /c " + "net localgroup Administrators " + user + " /add");
		return excute(command);
	}
	
	/**
	 * 将管理员改为普通用户
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月29日 上午9:51:01
	 * @param user
	 * @return
	 * String
	 */
	public static String toUser(String user){
		String command = new String("cmd /c " + "net localgroup Administrators " + user + " /delete");
		return excute(command);
	}

	/**
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:17:08
	 * @param String
	 *            command windows命令
	 * @retrun String
	 */
	public static String cmdExcute(String command) {
		String cmd = new String("cmd /c " + command);
		return excute(cmd);
	}

	/**
	 * 通用java执行命令行方法
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月14日 下午5:18:41
	 * @param String
	 *            command 执行的命令
	 * @retrun String
	 */
	public static String excute(String command) {
		InputStreamReader in = null;
		BufferedReader reader = null;
		StringBuffer allResult = new StringBuffer();
		Process pro = null;
		try {
			pro = Runtime.getRuntime().exec(command);
			int code = pro.waitFor();
			SequenceInputStream sis = new SequenceInputStream(pro.getInputStream(), pro.getErrorStream());
			in = new InputStreamReader(sis, "GBK");
			reader = new BufferedReader(in);

			String result = null;
			while ((result = reader.readLine()) != null) {
				allResult.append(result + "\n");
			}

			System.out.println("Exited with error code " + code);

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			if (pro != null) {
				pro.destroy();
				pro = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return allResult.toString();

	}
}
