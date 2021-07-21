package com.example.sms_gate;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;

@Component
public class ModemCnnt {

    byte newLine = 0x0D;
    byte endofline = 0x1A;

    SerialPort serialPort;

    public ModemCnnt() throws SerialPortException, InterruptedException {
        this.serialPort = new SerialPort("COM5");
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        //sendSMS("+48513152545", "Test1 !!!");

    }

    public void sendSMS(String telefonNumber, String message) {
        try {
            serialPort.writeString("AT+CMGF=1");
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeString("AT+CMGS=\"" + telefonNumber + "\"");
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeString(message);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeByte(endofline);

        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    ;

}
