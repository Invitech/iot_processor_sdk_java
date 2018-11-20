package hu.invitech.insight.processor.example.api;

import hu.invitech.insight.processor.example.ManualInputExample;
import hu.invitech.insight.processor.sdk.core.api.VersionInterface;

public class VersionApi implements VersionInterface {
    @Override
    public Integer getVersion() {
        return ManualInputExample.protocolVersion;
    }
}
