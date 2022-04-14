function getPrimeFactorsFor(number) {
    if (number < 2) return [number];

    let prime = findSmallestPrime(number);
    let remainder = number / prime;
    if (remainder <= 1) return [prime];

    return [prime].concat(getPrimeFactorsFor(remainder));
}

function findSmallestPrime(number) {
    let factor = 2;
    while (number % factor != 0) {
        ++factor;
    }
    return factor;
}

export { getPrimeFactorsFor };
