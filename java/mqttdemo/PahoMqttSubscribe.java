import org.eclipse.paho.client.mqttv3.*;

public class PahoMqttSubscribe implements MqttCallback {

	MqttClient client;

	public PahoMqttSubscribe() {
	}

	public static void main(String[] args) {
		new PahoMqttSubscribe().doDemo();
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
