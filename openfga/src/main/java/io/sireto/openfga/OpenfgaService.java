package io.sireto.openfga;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.openfga.sdk.api.client.*;
import dev.openfga.sdk.api.configuration.*;
import dev.openfga.sdk.api.model.*;
import dev.openfga.sdk.api.model.Metadata;
import dev.openfga.sdk.errors.FgaInvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.openfga.sdk.api.model.TypeDefinition;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class OpenfgaService {

    @Autowired
    OpenFgaClient openFgaClient;

    public void readRelationshipTuples() throws FgaInvalidParameterException, ExecutionException, InterruptedException {
        var options = new ClientReadChangesOptions()
                .type("document")
                .pageSize(10);
        var response = openFgaClient.readChanges(options).get();
        System.out.println("response: " + response.toString());
    }

    public void addRelationshipTuples() throws FgaInvalidParameterException, ExecutionException, InterruptedException, JsonProcessingException {
        ClientWriteOptions clientWriteOptions = new ClientWriteOptions();
        clientWriteOptions.authorizationModelId("01HFNJYFGJA6QHNE3JQVKGWX8M");

        ClientWriteRequest body = new ClientWriteRequest();
        List<ClientTupleKey> writes = new ArrayList<>();
        ClientTupleKey clienttuple = new ClientTupleKey();
        clienttuple.user("user:anne");
        clienttuple.relation("reader");
        clienttuple._object("document:R");
        writes.add(clienttuple);
        body.writes(writes);

        openFgaClient.write(body, clientWriteOptions).get();
    }

    public void checkRelationshipTuples() throws FgaInvalidParameterException, ExecutionException, InterruptedException {
        var request = new ClientCheckRequest()
                .user("user:anne")
                .relation("reader")
                ._object("document:Z");

        var options = new ClientCheckOptions()
                // You can rely on the model id set in the configuration or override it for this specific request
                .authorizationModelId("01HFNJYFGJA6QHNE3JQVKGWX8M");

        var response = openFgaClient.check(request, options).get();

        System.out.println("check relationship: " + response.toString());
    }

    public void writeAuthorizationModel() throws FgaInvalidParameterException, ExecutionException, InterruptedException, JsonProcessingException {
        String body_string = "{\"schema_version\":\"1.1\",\"type_definitions\":[{\"type\":\"user\"},{\"type\":\"document\",\"relations\":{\"reader\":{\"this\":{}},\"writer\":{\"this\":{}},\"owner\":{\"this\":{}}},\"metadata\":{\"relations\":{\"reader\":{\"directly_related_user_types\":[{\"type\":\"user\"}]},\"writer\":{\"directly_related_user_types\":[{\"type\":\"user\"}]},\"owner\":{\"directly_related_user_types\":[{\"type\":\"user\"}]}}}}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        WriteAuthorizationModelRequest writeAuthorizationModelResponse = objectMapper.readValue(body_string, WriteAuthorizationModelRequest.class);
        ClientWriteAuthorizationModelResponse response = openFgaClient.writeAuthorizationModel(writeAuthorizationModelResponse).get();
    }
}
