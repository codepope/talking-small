import Adafruit_BBIO.ADC as ADC
import time
 
sensor_pin = 'P9_40'
  
ADC.setup()
   
while True:
  reading = ADC.read(sensor_pin)
  millivolts = reading * 1800  # 1.8V reference = 1800 mV
  temp_c = (millivolts - 500) / 10
  print('mv=%.2f C=%.2f' % (millivolts, temp_c))
  time.sleep(1)
