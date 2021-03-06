(ns bridge.environment
  (:import (org.apache.flink.streaming.api.environment StreamExecutionEnvironment)
           (org.apache.flink.streaming.api TimeCharacteristic)))

(defn stream-execution-environment []
  "Returns an execution environment that represents the context in which the program is currently executed"
  (StreamExecutionEnvironment/getExecutionEnvironment))

(defn add-text-file
  ([environment path]
   "Reads text files, i.e. files that respect the TextInputFormat specification, line-by-line and returns them
   as Strings"
   (.readTextFile environment path))
  ([environment path input-file-format]
   "Reads (once) files as dictated by the specified file input format"
   (.readTextFile environment input-file-format path)))

(defn add-socket-text-stream
  "Creates a new DataStream that contains the strings received infinitely from socket"
  ([environment hostname port]
   (.socketTextStream environment hostname port))
  ([environment hostname port delimiter]
   (.socketTextStream environment hostname port delimiter)))

(defn from-collection [environment collection]
  "Adds a collection (java.util.Collection) to a specific environment"
  (.fromCollection environment collection))

(defn add-source [environment source]
  "Adds a data source to a specific environment"
  (.addSource environment source))

(defn set-time-characteristic [environment characteristic]
  "Configures the event time using a given time characteristic"
  (.setStreamTimeCharacteristic environment characteristic)
  environment)

(defn use-event-time [environment]
  "Sets the time characteristik of an environment to EventTime"
  (set-time-characteristic environment (TimeCharacteristic/EventTime))
  environment)

(defn use-processing-time [environment]
  "Sets the time characteristik of an environment to ProcessingTime"
  (set-time-characteristic environment (TimeCharacteristic/ProcessingTime))
  environment)

(defn use-ingestion-time [environment]
  "Sets the time characteristik of an environment to IngestionTime"
  (set-time-characteristic environment (TimeCharacteristic/IngestionTime))
  environment)

(defn execute
  "Starts the execution of the Flink job"
  ([environment]
   (.execute environment))
  ([environment job-name]
   (.execute environment job-name)))

(defn set-auto-watermark-interval [environment interval]
  "Sets the interval of the automatic watermark emission"
  (.setAutoWatermarkInterval (.getConfig environment) interval)
  environment)