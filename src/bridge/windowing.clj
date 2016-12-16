(ns bridge.windowing
  (:import (org.apache.flink.streaming.api.windowing.time Time)))

(defn set-time-window
  ([keyed-stream time-size]
   (.timeWindow keyed-stream time-size))
  ([keyed-stream time-size time-slide]
   (.timeWindow keyed-stream time-size time-slide)))

(defn get-time-size [time-object]
  (.getSize time-object))

(defn get-unit [time-object]
  (.getUnit time-object))

(defn to-millisec [time-object]
  (.toMilliseconds time-object))

(defn milliseconds [time-val]
  (Time/milliseconds time-val))

(defn seconds [time-val]
  (Time/seconds time-val))

(defn minutes [time-val]
  (Time/minutes time-val))

(defn hours [time-val]
  (Time/hours time-val))

(defn days [time-val]
  (Time/days time-val))


