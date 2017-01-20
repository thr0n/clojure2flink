(ns bridge.datastreams-test
  (:use clojure.test)
  (:require [bridge.environment :refer [stream-execution-environment from-collection execute]])
  (:use bridge.datastreams)
  (:import (org.apache.flink.streaming.api.datastream DataStreamSink)))

(def test-environment (stream-execution-environment))
(def test-text-file "file:\\\\C\\datastream_test.txt")
(def test-csv-file "file:\\\\C\\datastream_test.csv")


; (deftest test-add-sink) ; TODO find a way to mock kafka

(deftest test-print-stream
  (let [test-data-source (from-collection test-environment (list 1 2 3))]
    (is (= (instance? DataStreamSink (print-stream test-data-source)) true))))

(deftest test-write-as-text
  (let [test-data-source (from-collection test-environment (list 1 2 3))]
    (is (= (instance? DataStreamSink (write-as-text test-data-source test-text-file)) true))))

; (deftest test-wirte-as-csv) ; TODO test the tuple stuff... in clojure.
