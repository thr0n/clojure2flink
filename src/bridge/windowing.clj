(ns bridge.windowing
  (:import (org.apache.flink.streaming.api.windowing.time Time)))

(defn set-time-window
  "Windows keyed stream into  tumbling or sliding time windows"
  ([keyed-stream time-size]
   (.timeWindow keyed-stream time-size))                    ; tumbling windows
  ([keyed-stream time-size time-slide]
   (.timeWindow keyed-stream time-size time-slide)))        ; sliding window

(defn set-count-window
  "Windows keyed stream into tumbling or sliding count windows"
  ([keyed-stream size]
    (.countWindow keyed-stream size))                       ; tumbling window
  ([keyed-stream size slide]
    (.countWindow keyed-stream size slide)))                ; sliding window


