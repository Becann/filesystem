package com.cgutech.filesystem.utils;

import com.cgutech.filesystem.exception.CmdExcuteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JAVA执行命令行指令
 *
 * @author devis on 9/2/2016.
 */
public class CmdExcuteUtil {
    private static Logger logger = LoggerFactory.getLogger(CmdExcuteUtil.class);

    /**
     * 执行命令行指令，其命令行输出为GBK，约定WINDOWS下为GBK，linux下为UTF-8
     *
     * @param cmd 需要执行的指令
     * @return 执行结果（命令行输出）
     * @throws CmdExcuteException
     */
    public static String execGBK(String cmd) throws CmdExcuteException {
        return exec(cmd, "GBK");
    }

    /**
     * 执行命令行指令，其命令行输出为UTF-8，约定WINDOWS下为GBK，linux下为UTF-8
     *
     * @param cmd 需要执行的指令
     * @return 执行结果（命令行输出）
     * @throws CmdExcuteException
     */
    public static String execUTF8(String cmd) throws CmdExcuteException {
        return exec(cmd, "UTF-8");
    }

    private static String exec(String cmd, String encode) throws CmdExcuteException {
        String res = "";
        logger.info("执行命令行指令 [cmd]" + cmd + ", [encode]" + encode);
        BufferedReader input = null;
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmd);
            input = new BufferedReader(new InputStreamReader(process.getInputStream(), encode));
            String line;
            while ((line = input.readLine()) != null) {
                res += line + "\n";
            }
            logger.info("执行命令行指令返回：" + res);

        } catch (IOException e) {
            logger.error("执行命令行指令于IO操作发生异常 [cmd]" + cmd + ", [encode]" + encode, e);
            throw new CmdExcuteException("执行命令行指令于IO操作发生异常", e);
        } catch (Exception e) {
            logger.error("执行命令行指令发生异常 [cmd]" + cmd + ", [encode]" + encode, e);
            throw new CmdExcuteException("执行命令行指令发生异常", e);
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn("关闭输入流失败", e);
                }
            }
        }
        return res;
    }
}
