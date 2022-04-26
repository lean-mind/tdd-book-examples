function isStrongPassword(password) {
    if (!/\d/.test(password)) return false;
    if (password.length < 6) return false;
    return true;
}

export { isStrongPassword };
