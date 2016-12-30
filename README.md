# clojure2flink

clojure2flink is, as the name suggests, a Clojure bridge to [Apache Flink](http://flink.apache.org/), an open source
platform for distributed stream and batch data processing. The intention of this library is to fill the gap between
the Clojure programming language and the Flink platform.

To see this bridge in action take a look at my example use case
[clojured-taxi-rides](https://github.com/thr0n/clojured-taxi-rides)!

Until now there are only a couple of features of Flink's
[DataStream API](https://ci.apache.org/projects/flink/flink-docs-release-1.1/apis/streaming/index.html) ready to be
used in a Clojure application. So, yes, there are still plenty of functionalities that could be considered
(not to mention the DataSet API and all those libraries).

So it should be no surprise that clojure2flink ist still under development.
But for testing reasons it is already available as a [Clojar](https://clojars.org/clojure2flink)!
The current version is `0.1.0-SNAPSHOT`.

## Prerequisites and remarks

clojure2flink uses the following software environment for development and testing:

- Java version `1.8.0_111-b14`
- Clojure version `1.8.0`
- Leiningen version `2.6.1`
- Kafka version `0.9.0.1`

Yet there is no guarantee that clojure2flink will work using any other versions.

## Available features

- Create and configure a Flink `StreamExecutionEnvironment`
- Add a custom `DataSource`, a text file, a `java.util.Collection` or Socket Text
Stream to your environment
- Print your Stream to `stdout` or into a text- or csv-file
- Apply transformations on your data stream like `MapFunction`, `FlatMapFunction`
or `FilterFunction`
- Key your stream using a `KeySelector`
- Make use of the `FlinkKafkaConsumer09` and the `FlinkKafkaProducer09`. Perform
read and write operations on Kafka clusters
- Execute your Flink jobs

## Usage
### General usage

1) Add the clojure2flink [Clojar](https://clojars.org/clojure2flink) to the `:dependencies` vector of your Leiningen
project file.

2) Add the functions you need using `:require` and `:refer` inside your namespace definition. Please see the example
below:

```clojure
(ns demo.import-clojure2flink-functions
  (:require [bridge.environment :refer [stream-execution-environment ]])
```

3) Use the imported function(s) in your Clojure application:

```clojure
(def exec-env (stream-execution-environment))
```

### How to use Flink's generic interfaces or classes

Due to Clojure's missing support of Java Generics we unfortunately need to fall back on the following Java detour
(for further details see e.g. this [post](http://stackoverflow.com/a/3770360/7047542) from Chas Emerick on
StackOverflow, the author of "Clojure programming"):

1) Create a new `.java` file and implement the desired interface (e.g. `FilterFunction<T>`).
Replace the Generic Type `T` with some concrete value such as `Integer`. It's highly recommended to store these and other 'dummy-interfaces' in the same package. The package should be
exclusively used for this purpose as well!

```java
package java_interfaces;

import org.apache.flink.api.common.functions.FilterFunction;

public interface I_FilterOddNumbers extends FilterFunction<Integer> { }
```

2) Implement this newly created Java interface using a Clojure namespace.
The usage of `:gen-class` (instead of `reify`, `deftype` etc.) is mandatory since your
namespace must be *Ahead-Of-Time*-compiled. Otherwise Flink won't find the necessary `.class` file and will throw
a `ClassNotFoundException`.
Use the `:name` key to assign an appropriate descriptor to the `.class` file
that will be generated:

```clojure
(ns transformations.Clojured-FilterOddNumbers
  (:gen-class
    :implements [java_interfaces.I_FilterOddNumbers]
    :name transformations.Clojured_OddFilter))

(defn -filter [this value]
  (even? value)) ; remove all the odd numbers!
```

3) Add the path to your Java source package (in this example it's `"src/java_interfaces"`) to the `:java-source-paths`
vector of your Leiningen project file.

4) Furthermore, add the descriptor of your Clojure namespace to your Leiningen project file's `:aot` vector
(in this example it's `transformations.Clojured-FilterOddNumbers`).

5) Run `lein compile`

6) Import `transformations.Clojured-FilterOddNumbers` into the namespace of your Flink job and use it to implement
your use case:

```clojure
(ns demo.import-clojure2flink-functions
  (:require [bridge.environment :refer [stream-execution-environment ]])
  (:import (transformations Clojured_OddFilter))

; create a stream execution environment here ...

; create an instance of your filter:
(def filter-instance (Clojured_OddFilter.))

; or pass an instance of it to a function call (of course the apply-filter function defined inside
;   the transformation namespace has to be imported!):
; (def filtered-values (apply-filter <execution-environment> (Clojured_OddFilter.)))

; your code goes here ...

; print your stream to stdout or write it into a sink
; start job execution
; ...
```

## License
Licensed under the [Apache Public License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
