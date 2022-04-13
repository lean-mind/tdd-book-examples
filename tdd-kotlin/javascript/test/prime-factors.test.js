import { getPrimeFactorsFor } from "../src/prime-factors";

it("finds the prime composition of the given number", () => {
    expect(getPrimeFactorsFor(2)).toEqual([2]);
    expect(getPrimeFactorsFor(2 * 2)).toEqual([2, 2]);
    expect(getPrimeFactorsFor(2 * 2 * 2)).toEqual([2, 2, 2]);
    expect(getPrimeFactorsFor(3)).toEqual([3]);
    expect(getPrimeFactorsFor(5 * 5)).toEqual([5, 5]);
    expect(getPrimeFactorsFor(5 * 7 * 11 * 3)).toEqual([3, 5, 7, 11]);
});
