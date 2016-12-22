(defproject clojure2flink "0.1.0-SNAPSHOT"
  :description "A Clojure bridge to Apache Flink."
  :url "https://github.com/thr0n/clojure2flink"
  :license {:name "Apache License, Version 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 ;[org.apache.flink/flink-java "1.1.3"]
                 ;[org.apache.flink/flink-clients_2.11 "1.1.3"]
                 [org.apache.flink/flink-streaming-java_2.11 "1.1.3"]
                 [org.apache.flink/flink-connector-kafka-0.9_2.11 "1.1.3"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
