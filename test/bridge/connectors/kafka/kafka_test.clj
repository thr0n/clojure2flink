(ns bridge.connectors.kafka.kafka-test
  (:use clojure.test)
  (:require [bridge.connectors.kafka :refer [create-properties create-kafka-consumer assign-timestamp-and-watermarks]]
            [bridge.environment :refer [stream-execution-environment]])
  (:import (java.util Properties)))

(def key1 "language")
(def val1 "clojure")
(def key2 "year")
(def val2 "2016")

;(def local-zookeeper-host "localhost:2181")
;(def local-kafka-broker "localhost:9092")
;(def test-topic "topic")
;(def test-deserialization-schema (SimpleStringSchema.))
;(def test-properties (create-properties [["zookeeper.connect" local-zookeeper-host]
;                                          ["bootstrap.servers" local-kafka-broker]
;                                          ["group.id" "test-group"]
;                                          ["auto.offset.reset" "earliest"]]))

(def test-property (create-properties [[key1 val1] [key2 val2]]))

(deftest test-properties-creator
  (is (not (nil? test-property)))
  (is (= val1 (.getProperty test-property key1)))
  (is (= val2 (.getProperty test-property key2))))

(deftest test-empty-properties-creator
  (is (= (Properties.)) (create-properties))
  (is (= (Properties.) (create-properties nil))))

