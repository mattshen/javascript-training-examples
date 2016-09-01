//http://www.adequatelygood.com/JavaScript-Module-Pattern-In-Depth.html


var MODULE = (function (my) {
	my.anotherMethod = function () {
		// added method...
	};

	return my;
}(MODULE));
