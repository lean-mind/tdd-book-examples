function getPrimeFactorsFor(number) {
    let factor = 2;
    while (number % factor != 0) {
        ++factor;
    }
    let remainder = number / factor;
    if (remainder <= 1) {
        return [factor];
    }
    return [factor].concat(getPrimeFactorsFor(remainder));
}

export { getPrimeFactorsFor };
