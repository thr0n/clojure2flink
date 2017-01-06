(ns bridge.environment-test
  (:use clojure.test)
  (:require [bridge.environment :refer [stream-execution-environment add-text-file add-socket-text-stream
                                        from-collection use-event-time set-auto-watermark-interval]])
  (:import (org.apache.flink.streaming.api.environment StreamExecutionEnvironment LocalStreamEnvironment)
           (org.apache.flink.streaming.api.datastream DataStreamSource)))

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

(deftest auto-watermark-returns-not-nil
  (not (= nil (set-auto-watermark-interval test-environment 1))))
