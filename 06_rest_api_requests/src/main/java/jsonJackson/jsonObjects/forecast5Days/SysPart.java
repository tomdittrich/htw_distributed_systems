package jsonJackson.jsonObjects.forecast5Days;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Author: tom
 * Created by: ModelGenerator on 30.12.17
 */
@JsonRootName("sys")
public class SysPart {
    private String pod;

    public String getPod() {
        return pod;
    }

    @Override
    public String toString() {
        return "SysPart{" +
                "pod='" + pod + '\'' +
                '}';
    }
}