package java_parts.defined_interfaces;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;

/**
 * Created by ht on 07.12.2016.
 */
public interface IWikiFoldFunction extends FoldFunction<WikipediaEditEvent, Tuple2<String, Long>> {
}
