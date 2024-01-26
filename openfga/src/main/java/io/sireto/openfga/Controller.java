package io.sireto.openfga;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.openfga.sdk.errors.FgaInvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class Controller {

    @Autowired
    private OpenfgaService openfgaService;

    @GetMapping("/")
    public String initialize() throws FgaInvalidParameterException, ExecutionException, InterruptedException, JsonProcessingException {
        openfgaService.writeAuthorizationModel();
//        openfgaService.addRelationshipTuples();
        openfgaService.readRelationshipTuples();
        openfgaService.checkRelationshipTuples();
        return "success";
    }
}
