import org.eclipse.paho.client.mqttv3.*;
import com.pi4j.io.gpio.*;

public class PahoMqttSubLED implements MqttCallback {

	MqttClient client;

	final GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalOutput pina = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_07, "PinA");
	GpioPinDigitalOutput pinb = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_00, "PinB");
	GpioPinDigitalOutput pinc = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_03, "PinC");
	GpioPinDigitalOutput pind = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_12, "PinD");
	GpioPinDigitalOutput pine = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_13, "PinE");
	GpioPinDigitalOutput pinf = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_14, "PinF");

	public PahoMqttSubLED() {
	}

	public static void main(String[] args) {
		new PahoMqttSubLED().doDemo();
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		String msg = new String(message.getPayload());
		Double dval = Double.parseDouble(msg);
		int val = dval.intValue();
		pinf.setState(val >= 20);
		pine.setState(val >= 22);
		pind.setState(val >= 24);
		pinc.setState(val >= 26);
		pinb.setState(val >= 28);
		pina.setState(val >= 30);
	}

	public void connectionLost(Throwable cause) {
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
	}

	public void doDemo() {

		try {
			client = new MqttClient("tcp://m2m.eclipse.org:1883",
					MqttClient.generateClientId());
			client.connect();
			client.setCallback(this);

			client.subscribe("bbbexample/tmp36/c");

			while (true) {
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
