from time import sleep
from pymongo import MongoClient
import serial

try:
    commPort = 'ARDUINO COM PORT'
    ser = serial.Serial(commPort, baudrate = 9600, timeout = 1)
except:
    print("Serial connection failed.")

print("[+] Serial Connected!")

try:
    cluster = MongoClient(
        "YOUR MONGODB URL")
    db = cluster["DATABASE NAME"]["COLLECTION NAME"]
    print('[+] Mongo Connected!')
except:
    print('[!] Mongo Could Not Connect.')


db.delete_many({})
print("[-] Database Cleared.")
print("[!] On/Off Routine Started!")

def turnOn():
    ser.write(b'o')

def turnOff(): 
    ser.write(b'x')

turnOn()
print("on")
sleep(2)
turnOff()
print("off")
sleep(2)
turnOn()
print("on")
sleep(2)
turnOff()
print("off")
sleep(2)

print("[!] On/Off Routine Done!")

print('[!] Script Started!')

list = []
while True:
    things = db.find({})
    for thing in things:
        try:
            message = thing['message']
        except:
            pass
        if thing not in list:
            list.append(thing)
            try:
                if message == 'on':
                    turnOn()
                    print("[+] On!")
                elif message == 'off':
                    turnOff()
                    print("[+] Off!")
                else:
                    print("invalid")
            except:
                pass
