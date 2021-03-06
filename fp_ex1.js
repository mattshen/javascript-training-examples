function Employee(name, title, age, onTheBench){
    this.name = name;
    this.title = title;
    this.age = age;
    this.onTheBench = onTheBench;
}

var employees = [];
employees.push(new Employee('Mike', 'Manager', 45, true));
employees.push(new Employee('Ted', 'Developer', 25, false));
employees.push(new Employee('Jason', 'Developer', 35, false));
employees.push(new Employee('Lucy', 'Developer', 45, false));
employees.push(new Employee('Rajeev', 'Analyst', 40, false));
employees.push(new Employee('John', 'Manager', 50, false));
console.log("all employees: \n", employees);

var developers = employees.filter(function(e){
                    return e.title == 'Developer'; 
                });
console.log("all developers: \n", developers);

var ages = employees.filter(function(e){
                return e.title == 'Developer';
            }).map(function(e){
                return e.age;
            });

var averageAge = ages.reduce(function(prev, curr){
                    return prev + curr;
                }, 0) / ages.length;

console.log("developer average age: \n", averageAge);

console.log("make sure no one on bench: \n", employees.every(function(e){ return !e.onTheBench }));

//reusable function
Array.prototype.groupBy = function(keyFunction) {
    var groups = {};
    this.forEach(function(el) {
        var key = keyFunction(el);
        if (key in groups == false) {
            groups[key] = [];
        }
        groups[key].push(el);
    });
    return Object.keys(groups).map(function(key) {
        return {
            key: key,
            values: groups[key]
        };
    });
};

var groupedEmployees = employees.groupBy(function(e){ return e.title });
//console.log("grouped employees:\n", groupedEmployees);
for(var key in groupedEmployees) {
    if (groupedEmployees.hasOwnProperty(key)) {
        console.log(groupedEmployees[key]);
    }
}

