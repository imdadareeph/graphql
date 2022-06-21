package com.imdadareeph.graphql.controller;

import com.imdadareeph.graphql.entity.Data;
import com.imdadareeph.graphql.repository.DataRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class GraphController {

    @Autowired
    private DataRepository repository;

    @Value("classpath:data.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Data>> fetcher1 = data -> {
            return (List<Data>) repository.findAll();
        };

        DataFetcher<Data> fetcher2 = data -> {
            return repository.findByEmail(data.getArgument("email"));
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                typeWriting -> typeWriting.dataFetcher("getAllData", fetcher1).dataFetcher("findData", fetcher2))
                .build();

    }

    @PostMapping("/addData")
    public String addData(@RequestBody List<Data> dataList) {
        repository.saveAll(dataList);
        return "record inserted " + dataList.size();
    }

    @GetMapping("/findAllData")
    public List<Data> getData() {
        return (List<Data>) repository.findAll();
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping("/getDataByEmail")
    public ResponseEntity<Object> getDataByEmail(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
