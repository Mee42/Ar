

## Ar notes

each file is a collection of variables.
```
one = 1
two = 2
three = 3
```
types are optional
```
one --> Int 
one = 1
```
lambdas are assigned like this
```
(\lambda a b -> int.add a b)
```
`a` and `b` are the parameters, and `int:add a b` is the function body.
`int:add` is a function and takes in two values of type `int`.
This means that `a` and `b` are binded to `int`, 
making the type of this function `Int -> Int -> Int`.
Function identifiers can contain the `:` character, and this is used for namespacing (for now)