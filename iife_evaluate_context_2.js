var v, getValue;
v = 1;
getValue = (function (x) {
    return function () { return x; };
})(v);
v = 2;

getValue();  // 1
