Java and Raspberry Pi Example code
==================================

PahoMqttSubscribe.java
----------------------

**Description**: 
	Subscribes to temp sensor messages and prints results

**Requires**:
- Paho MQTT client for Java. To obtain use 
```
curl -O https://repo.eclipse.org/content/repositories/paho-releases/org/eclipse/paho/mqtt-client/0.4.0/mqtt-client-0.4.0.jar
```

**Build**:
	javac -cp mqtt-client-0.4.0.jar:. PahoMqttSubscribe.java

**Run**:
	java -cp mqtt-client-0.4.0.jar:. PahoMqttSubscribe


PahoMqttSubLED.java
----------------------

**Description**: 
	Subscribes to temp sensor messages and displays results on
	BerryClip LEDs

**Requires**:
- Paho MQTT client for Java. To obtain use
```
curl -O https://repo.eclipse.org/content/repositories/paho-releases/org/eclipse/paho/mqtt-client/0.4.0/mqtt-client-0.4.0.jar
```
- Pi4j Library. Consult http://pi4j.com/ for details and download the library from https://code.google.com/p/pi4j/downloads/list as a deb archive


**Build**:
	javac -cp mqtt-client-0.4.0.jar:/opt/pi4j/lib/'*':. PahoMqttSubLED.java

**Run**:
	sudo java -cp mqtt-client-0.4.0.jar:/opt/pi4j/lib/'*':. PahoMqttSubLED


PahoMqttSubLEDButton.java
----------------------

**Description**: 
	Subscribes to temp sensor messages and displays results on
	BerryClip LEDs. Also sends a test message to the sensor when
	the BerryClip button is pressed.

**Requires**:
- Paho MQTT client for Java. To obtain use
```
curl -O https://repo.eclipse.org/content/repositories/paho-releases/org/eclipse/paho/mqtt-client/0.4.0/mqtt-client-0.4.0.jar
```
- Pi4j Library. Consult http://pi4j.com/ for details and download the library from https://code.google.com/p/pi4j/downloads/list as a deb archive

**Build**:
	javac -cp mqtt-client-0.4.0.jar:/opt/pi4j/lib/'*':. PahoMqttSubLEDButton.java

**Run**:
	sudo java -cp mqtt-client-0.4.0.jar:/opt/pi4j/lib/'*':. PahoMqttSubLEDButton




