package java_parts.defined_interfaces;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;

/**
 * Created by ht on 07.12.2016.
 */
public interface IWikiKeySelector extends KeySelector<WikipediaEditEvent, String>{
}
