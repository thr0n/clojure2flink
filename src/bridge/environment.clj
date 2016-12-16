(ns bridge.environment
  (:import (org.apache.flink.streaming.api.environment StreamExecutionEnvironment)))

(defn stream-execution-environment []
  "Returns an execution environment"
  (StreamExecutionEnvironment/getExecutionEnvironment))

(defn add-source [environment source]
  "Adds a data source to a specific environment"
  (.addSource environment source))

(defn from-collection [environment collection]
  "Adds a collction to a specific environment"
  (.fromCollection environment collection))

(defn set-time-characteristic [environment characteristic]
  (.setStreamTimeCharacteristic environment characteristic))