import random

number_of_keys = 250
key_length = 10
symbols = "ABCDEFGHJKLMNPQRSTUVWXYZ"

for i in range(number_of_keys):
    key = ''.join(random.choice(symbols) for _ in range(0, key_length))
    print(key)
