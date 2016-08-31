//in a constructor

//correct use
function Person(name, age) {
    this.name = name;
    this.age = age;
}

var person1 = new Person('Alice', 29);
console.log(person1.name);    // Alice


//a mistake
function Person(name, age) {
    this.name = name;
    this.age = age;
    return this;
}

var person1 = Person('Alice', 29);
console.log(person1.name);    // Alice
console.log(window.name);     // Alice
console.log(person1 === window);    // true

