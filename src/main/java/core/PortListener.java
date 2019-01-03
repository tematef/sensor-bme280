package core;

import data_objects.Telemetry;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class PortListener {

    private static final long LISTEN_PERIOD = 60000; //should be the same as on arduino
    private Stream<String> serialPorts;

    public PortListener() {
        serialPorts = Stream.of(getPortNames());
    }

    public void listen() {
        SerialPort serialPort = new SerialPort(getAvailableUsbPort());

        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            Thread.sleep(1000); //sensor warm up
            while (serialPort.isOpened()) {
                String serialPortData = serialPort.readString();
                if (JsonHelper.isJsonValid(serialPortData)) {
                    Telemetry telemetry = JsonHelper.fromJson(serialPortData, Telemetry.class);
                    System.out.println(telemetry.toString());
                }
                Thread.sleep(LISTEN_PERIOD);
            }

        } catch (SerialPortException | InterruptedException e) {

        } finally {
            try {
                serialPort.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }

    }

    private String getAvailableUsbPort() {
        return serialPorts.filter(port -> port.contains("usb")).findFirst()
                .orElseThrow(() -> new NoSuchElementException("No available serial usb ports were found"));
    }

    private String[] getPortNames() {
       return SerialPortList.getPortNames();
    }
}