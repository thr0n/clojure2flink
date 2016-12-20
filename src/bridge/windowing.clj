(ns bridge.windowing
  (:import (org.apache.flink.streaming.api.windowing.time Time)))

(defn set-time-window
  "Windows a keyed stream into  tumbling or sliding time windows"
  ([keyed-stream time-size]
   (.timeWindow keyed-stream time-size))                    ; tumbling windows
  ([keyed-stream time-size time-slide]
   (.timeWindow keyed-stream time-size time-slide)))        ; sliding window

(defn set-count-window
  "Windows a keyed stream into tumbling or sliding count windows"
  ([keyed-stream size]
    (.countWindow keyed-stream size))                       ; tumbling window
  ([keyed-stream size slide]
    (.countWindow keyed-stream size slide)))                ; sliding window

(defn get-time-size [time-object]
  "Returns the size of a time-object"
  (.getSize time-object))

(defn get-unit [time-object]
  "Returns the unit of a time-object"
  (.getUnit time-object))

(defn to-millisec [time-object]
  "Converts a time object to milliseconds"
  (.toMilliseconds time-object))

(defn get-milliseconds [time-val]
  "Returns the given time-val in milliseconds"
  (Time/milliseconds time-val))

(defn get-seconds [time-val]
  "Returns the given time-val in seconds"
  (Time/seconds time-val))

(defn get-minutes [time-val]
  "Returns the given time-val in minutes"
  (Time/minutes time-val))

(defn get-hours [time-val]
  "Returns the given time-val in hours"
  (Time/hours time-val))

(defn get-days [time-val]
  "Returns the given time-val in days"
  (Time/days time-val))


