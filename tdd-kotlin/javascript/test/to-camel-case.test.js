const { toCamelCase } = require("../src/to-camel-case");

it("converts the first character of each word to uppercase", () => {
    expect(toCamelCase("foo")).toBe("Foo");
});
