import random

number_of_codes = 250
code_length = 5
symbols = "ABCDEFGHJKLMNPQRSTUVWXYZ"

for i in range(number_of_codes):
    code = ''.join(random.choice(symbols) for _ in range(code_length))
    print(code)
