package hu.invitech.insight.processor.example.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.invitech.insight.processor.example.ManualInputExample;
import hu.invitech.insight.processor.example.data.Resource;
import hu.invitech.insight.processor.example.data.Server;
import hu.invitech.insight.processor.sdk.v1.api.FunctionInterface;
import hu.invitech.insight.processor.sdk.v1.data.DataType;
import hu.invitech.insight.processor.sdk.v1.data.IncomingData;
import hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue;
import hu.invitech.insight.processor.sdk.v1.function.FunctionCall;
import hu.invitech.insight.processor.sdk.v1.function.FunctionInfo;
import hu.invitech.insight.processor.sdk.v1.function.FunctionMap;
import hu.invitech.insight.processor.sdk.v1.function.FunctionReturn;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static hu.invitech.insight.processor.example.ManualInputExample.getPlatformApi;
import static spark.Spark.halt;

public class FunctionApi implements FunctionInterface {
    private static final FunctionMap functionMap = new FunctionMap();

    static {
        List<FunctionInfo> functionInfoList = new LinkedList<>();

        FunctionInfo dataInput = new FunctionInfo("DATA_INPUT");
        dataInput.addParameter("temp", new TypedDefaultValue(DataType.FLOAT, null));
        dataInput.addParameter("humidity", new TypedDefaultValue(DataType.FLOAT, null));
        dataInput.addParameter("pressure", new TypedDefaultValue(DataType.FLOAT, null));
        dataInput.addParameter("battery", new TypedDefaultValue(DataType.FLOAT, null));

        functionInfoList.add(dataInput);

        functionMap.getResourceTypeFunctions().put("MANUAL_INPUT", functionInfoList);
    }

    private final Server server;

    public FunctionApi(Server server) {
        this.server = server;
    }

    @Override
    public FunctionMap getFunctionMap() {
        return functionMap;
    }

    @Override
    public FunctionReturn callFunction(final FunctionCall functionCall) {
        if (functionCall == null || functionCall.getFunctionName() == null || functionCall.getFunctionName().isEmpty() || !functionCall.getFunctionName().equals("DATA_INPUT")) {
            halt(400);
        }
        String jxQuery = String.format("/.[serverToken='%s' and originalId='%d']", server.getToken(), functionCall.getResourceId());
        if (ManualInputExample.jsonDb.find(jxQuery, Resource.class).isEmpty()) {
            halt(404);
        }

        ObjectNode data = ManualInputExample.objectMapper.createObjectNode();
        if (functionCall.getParameters().get("temp") != null) {
            data.put("temp", functionCall.getParameters().get("temp"));
        }
        if (functionCall.getParameters().get("humidity") != null) {
            data.put("humidity", functionCall.getParameters().get("humidity"));
        }
        if (functionCall.getParameters().get("pressure") != null) {
            data.put("pressure", functionCall.getParameters().get("pressure"));
        }
        if (functionCall.getParameters().get("battery") != null) {
            data.put("battery", functionCall.getParameters().get("battery"));
        }

        final PlatformApi platform = getPlatformApi(server);
        try {
            platform.sendData(new IncomingData(UUID.randomUUID().toString(), functionCall.getResourceId(), "MANUAL_INPUT", data)).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new FunctionReturn();
    }
}
