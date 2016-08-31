//just within a function

function sayThis() {
    alert(this == window);    // true
}


//some type of trap

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
