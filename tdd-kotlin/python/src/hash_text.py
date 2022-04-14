import hashlib


def hash(text: str) -> str:
    return hashlib.sha256(text.encode()).hexdigest()
