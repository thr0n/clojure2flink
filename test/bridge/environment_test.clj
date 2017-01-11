(ns bridge.environment-test
  (:use clojure.test)
  (:use bridge.environment)
  (:require [bridge.datastreams :refer [print-stream]])
  (:import (org.apache.flink.streaming.api.environment StreamExecutionEnvironment LocalStreamEnvironment)
           (org.apache.flink.streaming.api.datastream DataStreamSource)
           (org.apache.flink.api.common JobExecutionResult)))

(def test-environment (stream-execution-environment))

(deftest test-create-exec-env
  (let [env test-environment]
    (is (= (instance? LocalStreamEnvironment env) true))))

(deftest test-add-text
  (let [env (add-text-file test-environment "util\\test_input.txt")]
    (is (= (instance? DataStreamSource env) true))))

(deftest test-add-socket
  (let [env (add-socket-text-stream test-environment "localhost" 9999)]
    (is (= (instance? DataStreamSource env) true)))
  (let [env (add-socket-text-stream test-environment "localhost" 9999 ";")]
    (is (= (instance? DataStreamSource env) true))))

(deftest test-add-collection
  (let [collection (list 1 2 3)
        env (from-collection test-environment collection)]
    (is (= (instance? DataStreamSource env) true))))

(deftest use-event-time-returns-not-nil
  (not (= nil (use-event-time test-environment))))

(deftest use-processing-time-returns-not-nil
  (not (= nil (use-processing-time test-environment))))

(deftest use-ingestion-time-returns-not-nil
  (not (= nil (use-ingestion-time test-environment))))

(deftest auto-watermark-returns-not-nil
  (not (= nil (set-auto-watermark-interval test-environment 1))))

(deftest test-execution
  (let [test-data-source (from-collection test-environment (list 1 2 3))]
    (print-stream test-data-source)
    (is (= (instance? JobExecutionResult (execute test-environment)) true))))

;TODO add-source()