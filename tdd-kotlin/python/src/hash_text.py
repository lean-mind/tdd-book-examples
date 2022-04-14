import hashlib


def hash_given(text: str) -> str:
    return hashlib.sha256(text.encode()).hexdigest()[0:10]
