import random


def hash_given(text: str) -> str:
    if len(text) < 10:
        hash = text + str(random.random())
        return hash[0:10]
    else:
        return text[0:10]
