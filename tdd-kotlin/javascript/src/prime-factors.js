function getPrimeFactorsFor(number) {
    let factor = 2;
    while (number % factor != 0) {
        ++factor;
    }
    let remainder = number / factor;
    if (remainder > 1) {
        return [factor].concat(getPrimeFactorsFor(remainder));
    }
    return [factor];
}

export { getPrimeFactorsFor };
