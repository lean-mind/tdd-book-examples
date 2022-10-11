import hashlib


def calculate_hash(text: str) -> str:
    return hashlib.sha256(text.encode()).hexdigest()
