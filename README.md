#  Log Parser

The program, which is based on the incoming log per second delay statistics. Input parameters are input file and output file.

How to start(for run it is required JDK):
 * Download the repository
 * Launch file **bin/start.bat**
 * After starting Enter in the console path to the log file
 * The output of the program will be the output file **bin/output.csv**

A text log consisting of entries of the form:


1) Incoming team:
```
<time> ... <command id> ... input process ... <command type> ... <command parameters>
``` 
2) Response to the team:
```
<time> ... <command id> ... output process ... <response type> ... <response parameters>
```
Input command and output command to it have the same Id, for example:
```
22:59:58.279565;P2Proxy;;Seq 103476178, id 0xc8dd5fb262b2e1c8, input process 5 mcs, post 7 mcs, arrival time 11966351813781 mcs, type 1, msg_rev 0, messageType 0, msg_size 356, P2Login P2Robot, P2ReplyId -3972914076506201656, outType 1, MQAddress P2Robot.ROBOT_C_Sender3, p2proxyTime 38563399571758, p2channel 31770575371210461, BrokerCode FT02, Isin Eu-6.17, ClientCode 009, CotirContr 1, OrderType 2, Amount 1, StrPrice 63650.00000, Comment MEWG000000002a6ea855, Login , ext_id 836935, fix_client_operation_id , du 0, is_check_limit 0, StrDateExp 1900/01/01 00:00:00.000, Hedge 0, DontCheckMoney 0, LocalStamp 1900/01/01 00:00:00.000, OrderId 0, MatchRef ;TID 2500

22:59:58.279771;P2Proxy;;Seq 103476179, reply for id 0xc8dd5fb262b2e1c8, output process 7 mcs, post 10 mcs, reply time 11966351813989 mcs, type 101, P2ReplyCode 32, p2channel 70df2b834f8edd, messageType 0xbad00, order_id 0;TID 11916
```

#### What is he doing?

The program calculates every second delay statistics separately for each type of response.

Calculate:
* average delay
* the median,
* the 90th percentile
* the 99th percentile

Output **CSV** file with the following columns:

* time
* type of answer
* the number of incoming messages per second
* average
* median
* 90th percentile
* The 99th percentile
* maximum

Example **output.csv**:
```
Time,Type,Count,Average,Median,Percentile90,Percentile99,Maximum 
22:00:00,101,40,247.675,247.0,276.9,291.0,291.0
22:00:00,103,38,219.07894736842104,212.0,289.2,332.0,332.0
22:00:00,109,15,278.53333333333336,272.0,319.2,324.0,324.0
22:00:00,111,14,227.85714285714286,220.5,284.0,311.0,311.0
22:00:01,101,44,251.45454545454547,249.0,298.0,315.0,315.0
22:00:01,103,54,210.1851851851852,209.0,236.0,290.0,290.0
```

