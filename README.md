# NitroBot
### 2020/02/07 
* Networktable容易斷線
* AimDrive Command迴圈無法跳出
    * Fixed：第一次呼叫PID.atSetpoint都是回傳True，造成問題，所以最後直接讀取第二次以後解決