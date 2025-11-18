// Задача 1
let messages = [
    "Пойдём гулять в парк?",
    "Кажется, дождь собирается. Лучше пойдем в кино!",
    "Давай, сегодня как раз вышел новый фильм.",
    "Встречаемся через час у кинотеатра."
];

console.log("Задача 1");
for (let i = 0; i < messages.length; i+=2) {
    console.log(`Вы: ${messages[i]}`);
    console.log(`Друг: ${messages[i + 1]}`);
}

// Задача 2

let result = new Array();

for (let word of messages) {
    if (word.includes("кино")) {
        result.push(word);
    }
}

console.log("Задача 2");
console.log(result);