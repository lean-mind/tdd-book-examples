function getPrimeFactorsFor(number) {
    if (number < 1) {
        throw new Error("Only positive numbers are allowed");
    }
    let prime = findSmallestPrime(number);
    let remainder = number / prime;
    if (remainder <= 1) return [prime];

    return [prime].concat(getPrimeFactorsFor(remainder));
}

function findSmallestPrime(number) {
    if (number == 1) {
        return 1;
    }
    let factor = 2;
    while (number % factor != 0) {
        ++factor;
    }
    return factor;
}

export { getPrimeFactorsFor };
