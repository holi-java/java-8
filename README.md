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