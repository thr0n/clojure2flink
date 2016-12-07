(ns java_parts.impl.MapFunction
  (:gen-class
    :name java_parts.impl.ClojuredMapFunction
    :implements [org.apache.flink.api.common.functions.MapFunction]))

(defn -map [this value]
  (+ value 1))
