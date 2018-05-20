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

    @Test
    public void testTimeParse() throws Exception {

        String in_str = "22:59:15.999994;P2Proxy;;Seq 103466759, id 0xc8cb19ad26c9e1c8, input process 5 mcs, post 6 mcs, arrival time 11966309533593 mcs, type 3, msg_rev 0, messageType 2, msg_size 276, P2Login P2Robot, P2ReplyId -3978057614380899896, outType 3, MQAddress P2Robot.ROBOT_C_Sender3, p2proxyTime 38563263317380, p2channel 31770575371210461, BrokerCode FT03, BuySell 3, Nosystem 2, Code %%%, KodVcb %, ext_id 834599, fix_client_operation_id , Isin , LocalStamp 1900/01/01 00:00:00.000;TID 2500";
        String out_str = "22:59:16.000173;P2Proxy;;Seq 103466760, reply for id 0xc8cb19ad26c9e1c8, output process 7 mcs, post 11 mcs, reply time 11966309533773 mcs, type 103, P2ReplyCode 0, p2channel 70df2b834f8edd, messageType 0xbad18, num_orders 0;TID 11916";
        InputCommand in = new InputCommand(in_str);
        OutputCommand out = new OutputCommand(out_str);
        long mcs_in = in.getTime_mcs()- in.getTime().getTime();
        long mcs_out = out.getTime_mcs()- out.getTime().getTime();

        long total_in_mcs = in.getTime().getTime()*1000 + mcs_in;
        long total_out_mcs = out.getTime().getTime()*1000 + mcs_out;

        System.out.println("InTime(mcs): " + total_in_mcs);
        System.out.println("OutTime(mcs): " + total_out_mcs);
        System.out.println("----------------------");
        System.out.println("Deloy(mcs): " + (total_out_mcs - total_in_mcs));

    }

}