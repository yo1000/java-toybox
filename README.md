Java Toybox
================================================================================

Toybox with assortment of Java practice exercises.


Requirements
--------------------------------------------------------------------------------

- Java 21


Maze
--------------------------------------------------------------------------------

Maze builder with left-hand rule and right-hand rule route explorers.

```bash
./mvnw clean package && java -jar maze/target/maze-1.0-SNAPSHOT.jar 40 20
```

Demo

![demo](./demo.gif)


Hit and Blow
--------------------------------------------------------------------------------

Hit and Blow hits the hidden 1-9 digit numbers.

```bash
./mvnw clean package && java -jar hitnblow/target/hitnblow-1.0-SNAPSHOT.jar 4
```

Demo

```
Secret code is 4-digits.
****

> 1234
turn | 1
hit  | 0
blow | 3

> 5678
turn | 2
hit  | 1
blow | 0

```