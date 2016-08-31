//with object

var person = {
    name: 'Alice',
    sayName: function() {
        alert('welcome ' + this.name);
    }
}

person.sayName();    // this == person, alert: 'welcome Alice'

var name = 'Bob';

var say = person.sayName;    // this == window || undefined
say();    // 'welcome Bob' || throw an error: Cannot read property 'name' of undefined(...)
