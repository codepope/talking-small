import time
import Adafruit_BBIO.ADC as ADC
import paho.mqtt.client as mqtt

def on_message(mqttc, obj, msg):
  if msg.topic == "bbbexample/tmp36/test":
    print("Test has been pressed")

sensor_pin = 'P9_40'
ADC.setup()
   
mqttc = mqtt.Client()
mqttc.on_message = on_message
mqttc.connect("m2m.eclipse.org", 1883, 60)
mqttc.subscribe("bbbexample/tmp36/#",1)
mqttc.loop_start()

while True:
  reading = ADC.read(sensor_pin)
  millivolts = reading * 1800  # 1.8V reference = 1800 mV
  temp_c = (millivolts - 500) / 10
  print('mv=%.2f C=%.2f' % (millivolts, temp_c))
  mqttc.publish("bbbexample/tmp36/mv","%.2f" % millivolts,retain=1);
  mqttc.publish("bbbexample/tmp36/c","%.2f" % temp_c,retain=1);
  time.sleep(1)
