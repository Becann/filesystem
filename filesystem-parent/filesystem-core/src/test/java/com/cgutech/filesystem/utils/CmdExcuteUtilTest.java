package com.cgutech.filesystem.utils;

import com.cgutech.filesystem.exception.CmdExcuteException;
import org.junit.Test;

/**
 * @author devis on 9/2/2016.
 */
public class CmdExcuteUtilTest {

    @Test
    public void ipconfigTest() throws CmdExcuteException {
        String res = CmdExcuteUtil.execGBK("ipconfig");
        System.out.println(res);
    }
}
