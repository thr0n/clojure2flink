(ns bridge.connectors.kafka-test
  (:use clojure.test
        bridge.connectors.kafka)
  (:import (java.util Properties)))

(def key1 "language")
(def val1 "clojure")
(def key2 "year")
(def val2 "2016")

(def test-property (create-properties [[key1 val1] [key2 val2]]))

(deftest test-properties-creator
  (not (nil? test-property))
  (is (= val1 (.getProperty test-property key1)))
  (is (= val2 (.getProperty test-property key2))))



