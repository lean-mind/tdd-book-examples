function toCamelCase(text) {
    const words = text.split(/[ ,_-]/g);
    return words.join("");
}

export { toCamelCase };
