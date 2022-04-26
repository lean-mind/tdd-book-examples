function toCamelCase(text) {
    return text
        .split(/[ ,_-]/g)
        .map((word) => {
            return word.charAt(0).toUpperCase() + word.substr(1);
        })
        .join("");
}
export { toCamelCase };
