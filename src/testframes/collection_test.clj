(ns testframes.collection_test
  (:use [bridge.environment])
  (:import (java_parts.impl ClojuredMapFunction)))

(def my-collection (list 1 2 3 4 5 6))
(println my-collection)

(def see (stream-execution-environment))
(def stream (from-collection see my-collection))

(def results
  (.returns (.map stream (ClojuredMapFunction.)) Long))

(.print results)
(.execute see)


