package com.example.crudapi_client.controller;


import com.example.crudapi_client.modal.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class ProductController {

    private final String REST_API_LIST = "http://localhost:8080/product";
    private final String REST_API_CREATE = "http://localhost:8080/product";
    private final String REST_API_UPDATE = "http://localhost:8080/prodct";
    private final String REST_API_GET_ID = "http://localhost:8080/prodct";



    private static Client createJerseyRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        // Config logging for client side
        clientConfig.register( //
                new LoggingFeature( //
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), //
                        Level.INFO, //
                        LoggingFeature.Verbosity.PAYLOAD_ANY, //
                        10000));

        return ClientBuilder.newClient(clientConfig);
    }

    @GetMapping(value = "/")
    public String index(Model model){

        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_LIST);
        List<Product> ls = target.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);
        model.addAttribute("lsProduct",ls);
        return "index";
    }

    @GetMapping(value = "createnewproduct")
    public String createNewUser(){
        return "createnewproduct";
    }

    @PostMapping("saveproduct")
    public String saveUser(@RequestParam String name,
                           @RequestParam String price,
                           @RequestParam Integer quantity) {
        Product u = new Product();
        u.setName(name);
        u.setPrice(price);
        u.setQuantity(quantity);

        String jsonUser = convertToJson(u);

        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_CREATE);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jsonUser, MediaType.APPLICATION_JSON));
        return "redirect:/";
    }

    private static String convertToJson(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("sellProduct")
    public String sellProduct(Integer id, Model model){
        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_GET_ID +id);
        Product product = target.request(MediaType.APPLICATION_JSON_PATCH_JSON_TYPE).get(Product.class);
        model.addAttribute("product",product);
        return "sellProduct";
    }
//
    @PostMapping("updateProductDone")
    public String sellProductDone(Integer id,
                                 @RequestParam Integer quantity
                                 )
    {
        Product u = new Product();
        u.setQuantity(quantity);
        String jsonUser = convertToJson(u);

        Client client = createJerseyRestClient();
        WebTarget target = client.target(REST_API_UPDATE +"?id="+id);
        Response response = target.request(MediaType.APPLICATION_JSON_PATCH_JSON_TYPE)
                .put(Entity.entity(jsonUser, MediaType.APPLICATION_JSON));
        return "redirect:/";
    }
}
