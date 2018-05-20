import org.junit.*;

import static org.junit.Assert.*;

public class LogParserTest {
    @Test
    public void testValidation() throws Exception {
        //P2_COD
        String str = "22:59:40.191391;P2Proxy;;Seq 103472005, Error for id 0x989187ba8728bdb4, output process 0 mcs, reply time 11966333725351 mcs, type 100, P2ReplyCode 10004, msg \"Invalid message type.\", p2channel 9534052210846475. Only watchdog can send this msg type. Request msg: type 10001, from P2_COD_P2PROXY.cod_in_cod_p2proxy_p2proxy, binary body 00 00 00 00.;TID 11916";
        assertEquals(false, LogParser.validation(str));

        //SQLProxy
        String str1 = "22:59:27.590273;P2Proxy;;Seq 103469319, id 0x80000de53372a4f6, input process 4 mcs, arrival time 11966321124044 mcs, type 63, Message from tgC0rm_C000000_2.providerD, To SQLProxy2, SQLProxyMsgId 9346;TID 1080";
        assertEquals(false, LogParser.validation(str1));

        //True
        String str2 = "22:59:40.202358;P2Proxy;;Seq 103472007, reply for id 0xc8d547425190e1c8, output process 9 mcs, post 13 mcs, reply time 11966333736307 mcs, type 101, P2ReplyCode 332, p2channel 70df2b834f8edd, messageType 0xbad00, order_id 0;TID 1080";
        assertEquals(true, LogParser.validation(str2));
    }

}