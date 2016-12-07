(ns testframes.wikipedia-test
  (:use bridge.environment
        bridge.windowing)
  (:import (org.apache.flink.streaming.connectors.wikiedits WikipediaEditsSource)
           (java_parts.impl ClojuredKeySelector ClojuredFoldFunction)
           (org.apache.flink.api.java.tuple Tuple2)))

(def exec-env (stream-execution-environment))

(def edits (add-source exec-env (WikipediaEditsSource.)))
(def keyed-edits (.keyBy edits (ClojuredKeySelector.)))
(def timed-edits (.timeWindow keyed-edits (seconds 5)))

(def results
  (.fold timed-edits (Tuple2. "" (Long. 0)) (ClojuredFoldFunction.)))

(.print results)
(.execute exec-env)

