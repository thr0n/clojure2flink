# clojure2flink


*This project is still under construction. Please be patient!*

A Clojure library designed to ... well, that part is up to you.

## Prerequisites

- Leiningen version 2.6.1
- Java version ...
- Clojure version ...

*(tbc)*

## Usage
### General usage

1) Download .jar and add it to the classpath of your project. (Later on there will be a Clojar available at <https://clojars.org/>)

2) Add the functions you need using `:require` and `:refer` inside your namespace definition. Please see the example below:

```lisp
(ns demo.import-clojure2flink-functions
  (:require [bridge.environment :refer [stream-execution-environment ]])
```

3) Use the imported function(s) in your Clojure application:

```lisp
(def exec-env (stream-execution-environment))
```

### How to use Flink's generic interfaces or classes

1) Create a new `.java` file and implement the desired interface (e.g. `FilterFunction<T>`).
Replace the Generic Type `t` with some concrete value like `Integer`:

```java
    package java_interfaces;

    import org.apache.flink.api.common.functions.FilterFunction;

    public interface I_FilterOddNumbers extends FilterFunction<Integer>{
    }
```

2) Implement this newly created Java interface using a Clojure namespace. Use the `:name` key to assign an appropriate name to the `.class` file that will be created.

```lisp
(ns transformations.Clojured-FilterOddNumbers
  (:gen-class
    :implements [java_interfaces.I_FilterOddNumbers]
    :name transformations.Clojured_OddFilter))

(defn -filter [this value]
  (even? value))
```

3) Add the path to your Java source package (in this example it's `"src/java_interfaces"`) to the `:java-source-paths` vector of your Leiningen project file.

4) Add the descriptor of your Clojure namespace to the Leiningen project file's `:aot` vector as well (in this example it's `transformations.Clojured-FilterOddNumbers`).

5) Run `lein compile`

6) Import `Clojured-FilterOddNumbers` into your namespace and use it to implement your use case.:

```lisp
(ns demo.import-clojure2flink-functions
  (:require [bridge.environment :refer [stream-execution-environment ]])
  (:import (transformations Clojured_OddFilter))

; your code goes here ...

  (defn filter-instance (Clojured_OddFilter.))

; your code goes here ....
```

*(tbc)*

## License

*(todo)*

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
