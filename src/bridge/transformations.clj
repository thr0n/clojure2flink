(ns bridge.transformations)

(defn key-by [stream key-selector-object]
  "Creates a keyed stream that uses the given key-selector to partition the data stream."
  (.keyBy stream key-selector-object))

(defn apply-map [stream map-object]
  "Applies a map function on the data stream. An object that implements org.apache.flink.api.common.functions.MapFunction is required."
  (.map stream map-object))

(defn apply-flat-map [stream flat-map-object]
  (.flatMap stream flat-map-object))

(defn apply-filter [stream filter-object]
  (.filter stream filter-object))

(defn apply-window [stream window-object]
  (.apply stream window-object))
