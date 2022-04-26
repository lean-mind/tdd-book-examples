function getPrimeFactorsFor(number) {
    checkForPositiveNumber(number);
    return primeFactors(number);

    function checkForPositiveNumber(number) {
        if (number < 1) {
            throw new Error("Only positive numbers are allowed");
        }
    }

    function primeFactors(positiveNumber) {
        let prime = findSmallestPrime(positiveNumber);
        let remainder = positiveNumber / prime;
        if (remainder <= 1) {
            return [prime];
        }
        return [prime].concat(primeFactors(remainder));
    }

    function findSmallestPrime(number) {
        if (number === 1) {
            return 1;
        }
        let factor = 2;
        while (number % factor !== 0) {
            ++factor;
        }
        return factor;
    }
}

export { getPrimeFactorsFor };
