# Java-8 extensions

## Synchronization

synchronizing the target each call of `Supplier#get`:
```java
Supplier<Integer> value = sync(() -> 1);
```

## Lazy Initializer

lazy initializing the value in common way:

```java
Supplier<Int> value = lazy(() -> 1); 
```

lazy initializing the value in multi-threads:

```java
Supplier<Int> value = lazy(() -> 1).lock();
Supplier<Int> value = lazy(() -> 1).lock(lock);
```

## Function Operations

fill the first argument by `curly`:

```java
BiFunction<Integer,Long,String> function2 = (left,right)-> left+"/"+right;

Function<T,R> function1 = curly(function2, 1);
Supplier<R>  supplier = function1.curly(3L); // "1/3"
```

fill the last argument by `push`:

```java
BiFunction<Integer,Long,String> function2 = (left,right)-> left+"/"+right;

Function<T,R> function1 = push(function2, 1L);
Supplier<R>  supplier = function1.push(3); // "3/1"
```

drop the first argument by `take`:

```java
BiFunction<Integer,Long,String> function2 = (left,right)-> left+"/"+right;

Function<T,R> function1 = drop(function2);

String value = function1.apply(3L);// "null/3"

Supplier<R>  supplier = function1.drop(); "null,null"
```

drop the last argument by `pop`:

```java
BiFunction<Integer,Long,String> function2 = (left,right)-> left+"/"+right;

Function<T,R> function1 = pop(function2);

String value = function1.apply(3);// "3/null"

Supplier<R>  supplier = function1.pop(); "null,null"
```



