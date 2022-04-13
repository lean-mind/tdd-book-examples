function getPrimeFactorsFor(number) {
    let factor = 2;
    if (number % factor != 0) {
        factor = 3;
    }
    let factors = [factor];
    let remainder = number / factor;
    if (remainder > 1) {
        factors = factors.concat(getPrimeFactorsFor(remainder));
    }
    return factors;
}

export { getPrimeFactorsFor };
