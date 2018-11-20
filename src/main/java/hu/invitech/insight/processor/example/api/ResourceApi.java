package hu.invitech.insight.processor.example.api;

import hu.invitech.insight.processor.example.ManualInputExample;
import hu.invitech.insight.processor.example.data.Resource;
import hu.invitech.insight.processor.example.data.Server;
import hu.invitech.insight.processor.sdk.v1.api.ResourceInterface;
import hu.invitech.insight.processor.sdk.v1.data.JsonDataType;
import hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static hu.invitech.insight.processor.sdk.v1.resource.SensorTypes.*;
import static spark.Spark.halt;

public class ResourceApi implements ResourceInterface {
    private static final ResourceTypeMap resourceTypeMap = new ResourceTypeMap();

    static {
        ResourceTypeInfo resourceTypeInfo = new ResourceTypeInfo("MANUAL_INPUT");

        List<IncomingDataInfo> incomingDataInfoList = new LinkedList<>();
        incomingDataInfoList.add(new IncomingDataInfo("temp", JsonDataType.DOUBLE, TEMPERATURE, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("humidity", JsonDataType.DOUBLE, HUMIDITY, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("pressure", JsonDataType.DOUBLE, PRESSURE, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("battery", JsonDataType.DOUBLE, BATTERY_PERCENTAGE, null, null));

        resourceTypeInfo.getIncomingData().put("MANUAL_DATA", incomingDataInfoList);

        resourceTypeMap.getResourceTypes().add(resourceTypeInfo);
    }

    private final Server server;

    public ResourceApi(final Server server) {
        this.server = server;
    }

    @Override
    public ResourceTypeMap getResourceTypeMap() {
        return resourceTypeMap;
    }

    @Override
    public void addResource(final ResourceInfo resourceInfo) {
        if (resourceInfo == null || resourceInfo.getType() == null || resourceInfo.getType().isEmpty() || !resourceInfo.getType().equals("MANUAL_INPUT")) {
            halt(400);
        }
        String jxQuery = String.format("/.[serverToken='%s' and originalId='%d']", server.getToken(), resourceInfo.getId());
        if (!ManualInputExample.jsonDb.find(jxQuery, Resource.class).isEmpty()) {
            halt(409);
        }
        ManualInputExample.jsonDb.insert(new Resource(UUID.randomUUID().toString(), resourceInfo.getType(), server.getToken(), resourceInfo.getId()));
    }

    @Override
    public void removeResource(final Integer id) {
        String jxQuery = String.format("/.[serverToken='%s' and originalId='%d']", server.getToken(), id);
        List<Resource> resourceList = ManualInputExample.jsonDb.find(jxQuery, Resource.class);
        if (resourceList.isEmpty()) {
            halt(404);
        }
        ManualInputExample.jsonDb.remove(resourceList.get(0), Resource.class);
    }
}
