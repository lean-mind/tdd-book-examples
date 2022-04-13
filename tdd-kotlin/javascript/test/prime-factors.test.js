import { getPrimeFactorsFor } from "../src/prime-factors";

it("finds the prime composition of the given number", () => {
    expect(getPrimeFactorsFor(2)).toEqual([2]);
});
