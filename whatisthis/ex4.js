//call, apply & bind

var name = 'Bob';
var person = {
    name: 'Alice',
    age: 29
}

function sayName() {
    console.log(this.name);
}
sayName.call(person);    // Alice

// Difference between call and apply
//func.call(context, arg1, arg2, ...)
//func.apply(context, [arg1, arg2, ...])


//bind returns a new function and its context (include "this") cannot be changed
var name = 'Bob';
var person = {
    name: 'Alice',
    age: 29
}

function sayName() {
    console.log(this.name);
}

var say = sayName.bind(person);
say();        // Alice
sayName();    // Bob
