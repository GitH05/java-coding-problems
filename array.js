//Array: collection of item of a certain type
const a=["java","Php"];
const b=["javaScript","css"];
const c=["python","html"];
const concat=[...a, ...b, ...c] //using `...spread` operator to merge all the array
console.log(concat)
console.log("length:",concat.length)

//it gives the shallow copy of JS object
//shallow copy:keep clone of the objects/refrence address is copied
const obj1 = {x:3,y:4}
const obj2 = {z:5,a:6}
const obj3 = {...obj1, ...obj2}
console.log(obj3)