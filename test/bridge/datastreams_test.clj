(ns bridge.datastreams-test
  (:use clojure.test)
  (:require [bridge.environment :refer [stream-execution-environment from-collection execute]])
  (:use bridge.datastreams)
  (:import (org.apache.flink.streaming.api.datastream DataStreamSink)))

(def test-environment (stream-execution-environment))

;TODO
(deftest test-add-sink)

(deftest test-print-stream
  (let [test-data-source (from-collection test-environment (list 1 2 3))]
    (is (= (instance? DataStreamSink (print-stream test-data-source)) true))))

;TODO
(deftest test-write-as-text)

;TODO
(deftest test-wirte-as-csv)

