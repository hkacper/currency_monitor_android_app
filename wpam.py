from flask import Flask
from pusher_push_notifications import PushNotifications
import pyrebase
import json
import requests
import threading
import time
import sys

app = Flask(__name__)

firebaseConfig = {
    "apiKey": "AIzaSyCHbOdi9js5cA-W7od5c-Qxs-Xi0PC6zrI",
    "authDomain": "apka2-fb0f1.firebaseapp.com",
    "databaseURL": "https://apka2-fb0f1.firebaseio.com",
    "projectId": "apka2-fb0f1",
    "storageBucket": "apka2-fb0f1.appspot.com",
    "messagingSenderId": "251532498079",
    "appId": "1:251532498079:web:1585cbdb7486fe4e1efeaa",
    "measurementId": "G-B45RYJFZ3X"
  }

firebase = pyrebase.initialize_app(firebaseConfig)

db = firebase.database()


def get_currencies_values():
  while True:
    contnent = requests.get('https://api.exchangeratesapi.io/latest?base=PLN').text
    a = json.loads(contnent)
    c = a["rates"]

    for k, v in c.items():
      db.child("Currencies").child(k).update({"real" : round(1/v, 4)})

      if db.child("Currencies").child(k).child("min").get().val() == None or db.child("Currencies").child(k).child("max").get().val() == None:
        continue
      minValue = float(db.child("Currencies").child(k).child("min").get().val())
      maxValue = float(db.child("Currencies").child(k).child("max").get().val())
      realValue = float(db.child("Currencies").child(k).child("real").get().val())
      if minValue > realValue or maxValue < realValue:
        if db.child("Currencies").child(k).child("sent").get().val() == 1:
          continue
        push_notification(k, str(realValue))
        db.child("Currencies").child(k).update({"sent" : 1})

    time.sleep(30)
    print('Hello world!', file=sys.stderr)

def check_sent():
  while True:
    x = requests.get('https://api.exchangeratesapi.io/latest?base=PLN').text
    y = json.loads(x)
    z = y["rates"]
    for k, v in z.items():
      if db.child("Currencies").child(k).child("sent").get().val() == None or db.child("Currencies").child(k).child("sent").get().val() == 1:
        db.child("Currencies").child(k).update({"sent" : 0})
    time.sleep(86400)



    

def push_notification(currency, value):
  pn_client = PushNotifications(
      instance_id = '776e8433-c7b8-4aa9-bf62-5c23650b37c7',
      secret_key = 'C15FB9EFB61976F1F09BC463F3265EBD3A2A801E612CE717257A576472F9FEAE',
  )

  response = pn_client.publish(
      interests=['hello'],
      publish_body={
                  'fcm': {'notification': {
      'body': currency + ' ma nowy kurs: ' + value,
    },},},)

  print(response['publishId'])

thread1 = threading.Thread(target=get_currencies_values)
thread1.start()
thread2 = threading.Thread(target=check_sent)
thread2.start()

def stream_handler():
  get_currencies_values()    

if __name__ == "__main__":
  app.run(threaded=False, processes=1)
  db.stream(stream_handler) 
    

