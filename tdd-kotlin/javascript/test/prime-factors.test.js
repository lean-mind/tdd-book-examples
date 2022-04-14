import { getPrimeFactorsFor } from "../src/prime-factors";

it("knows what is a primer number", () => {
    expect(getPrimeFactorsFor(2)).toEqual([2]);
    expect(getPrimeFactorsFor(3)).toEqual([3]);
});

it("produces the same result to multiply the numbers in the output list", () => {
    expect(getPrimeFactorsFor(2 * 2 * 2)).toEqual([2, 2, 2]);
});

it("orders the prime factors from the smallest to the biggest", () => {
    expect(getPrimeFactorsFor(5 * 7 * 11 * 3)).toEqual([3, 5, 7, 11]);
});

it("knows that prime numbers start with 2", () => {
    expect(getPrimeFactorsFor(1)).toEqual([1]);
});
