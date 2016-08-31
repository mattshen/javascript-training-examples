//just within a function

function sayThis() {
    alert(this == window);    // true
}


//a problem
var name = 'Bob',
    person = {
        name: 'Alice',
        sayName: function() {
            console.log(this === person);    // true
            return function() {
                console.log(this === person);    // false
                console.log(this === window);    // true
                console.log(this.name);          // Bob
            };
        }
    };


//solution
var name = 'Bob',
    person = {
        name: 'Alice',
        sayName: function() {
            console.log(this === person);    // true
            return function() {
                console.log(this === person);    // true
                console.log(this === window);    // false
                console.log(this.name);          // Alice
            }.bind(this);
        }
    };

person.sayName()();