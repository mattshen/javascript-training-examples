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


//solution 1
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


//solution 2
var name = 'Bob',
    person = {
        name: 'Alice',
        sayName: function() {
            console.log(this === person);    // true
            var that = this;
            return function() {
                console.log(that === person);    // true
                console.log(that === window);    // false
                console.log(that.name);          // Alice
            };
        }
    };

person.sayName()();


