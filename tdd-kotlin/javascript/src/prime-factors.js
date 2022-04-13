function getPrimeFactorsFor(number) {
    let factor = 2;
    let factors = [factor];
    let remainder = number / factor;
    if (remainder > 1) {
        factors.push(factor);
    }
    return factors;
}

export { getPrimeFactorsFor };
