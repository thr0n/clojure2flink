(ns testframes.taxi-test
  (:use bridge.environment)
  (:import (org.apache.flink.streaming.api TimeCharacteristic)
           (com.dataartisans.flinktraining.exercises.datastream_java.sources TaxiRideSource)))

(def max-event-delay 60)                                    ; events are out of order by max 60 seconds
(def serving-speed-factor 600)                              ; events of 10 minutes are served in 1 second

(def exec-env (stream-execution-environment))

(set-time-characteristic exec-env (TimeCharacteristic/EventTime))

(def rides (add-source exec-env (TaxiRideSource. "../../resources/datasets/nycTaxiRides.gz" max-event-delay serving-speed-factor)))

(.print rides)

(.execute exec-env)


