function toCamelCase(text) {
    const words = text.split(/[ ,_-]/g);
    let word = words[0];
    word = word.charAt(0).toUpperCase() + word.substr(1);
    words[0] = word;
    return words.join("");
}
export { toCamelCase };
