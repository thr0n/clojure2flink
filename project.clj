(defproject flink-transformations "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.flink/flink-clients_2.11 "1.0.3"]
                 [org.apache.flink/flink-streaming-java_2.11 "1.0.3"]
                 [org.apache.flink/flink-connector-wikiedits_2.11 "1.0.3"]]
  :aot [java_parts.impl.MapFunction
        java_parts.impl.KeySelector
        java_parts.impl.FoldFunction]
  :java-source-paths ["src/java_parts/interfaces"]
  :main test_frames.wikipedia_test)
