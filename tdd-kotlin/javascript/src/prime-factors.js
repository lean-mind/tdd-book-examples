function getPrimeFactorsFor(number) {
    let factor = 2;
    while (number % factor != 0) {
        ++factor;
    }
    let factors = [factor];
    let remainder = number / factor;
    if (remainder > 1) {
        factors = factors.concat(getPrimeFactorsFor(remainder));
    }
    return factors;
}

export { getPrimeFactorsFor };
