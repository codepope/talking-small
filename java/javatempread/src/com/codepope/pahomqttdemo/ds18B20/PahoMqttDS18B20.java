package com.codepope.pahomqttdemo.ds18B20;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;

import org.eclipse.paho.client.mqttv3.*;

public class PahoMqttDS18B20 implements MqttCallback {

	MqttClient client;

	public PahoMqttDS18B20() {
	}

	public static void main(String[] args) {
		new PahoMqttDS18B20().doDemo();
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		System.out.println(topic + " " + new String(message.getPayload()));
	}

	public void connectionLost(Throwable cause) {
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
	}

	public void doDemo() {
		Path device_dir_path = null;
		Path device_path = null;

		Runtime rt = Runtime.getRuntime();

		try {
			Process p1 = rt.exec("sudo -n modprobe w1-gpio");
			p1.waitFor();
			Process p2 = rt.exec("sudo -n modprobe w1-therm");
			p2.waitFor();
			if (p1.exitValue() != 0 || p2.exitValue() != 0) {
				System.err.println("Insufficient permission to setup");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		Path p = Paths.get("/sys/bus/w1/devices");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)) {
			for (Path entry : ds) {
				System.out.println(entry.getFileName());
				if (entry.getFileName().toString()
						.matches("[0-9][0-9]-[0-9a-f]+")) {
					device_dir_path = p.resolve(entry.getFileName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		if (device_dir_path == null) {
			System.out.println("Could not locate 1Wire interface");
			System.exit(1);
		}

		device_path = device_dir_path.resolve("w1_slave");

		try {
			client = new MqttClient("tcp://m2m.eclipse.org:1883",
					MqttClient.generateClientId());
			client.connect();

			while (true) {
				try {
					BufferedReader fr = Files.newBufferedReader(device_path,
							Charset.defaultCharset());
					String line1 = fr.readLine();
					String line2 = fr.readLine();
					fr.close();

					double val = Double.parseDouble(line2.substring(line2
							.indexOf("=") + 1)) / 1000.0;

					client.publish("bbbexample/ds18b20/c", Double.toString(val).getBytes(),1,true);

					System.out.println(line2+" give "+val);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
