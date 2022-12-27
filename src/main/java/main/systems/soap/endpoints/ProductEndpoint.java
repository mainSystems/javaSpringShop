package main.systems.soap.endpoints;

import lombok.RequiredArgsConstructor;
import main.systems.persistence.services.ServiceProduct;
import main.systems.soap.products.GetProductsRequest;
import main.systems.soap.products.GetProductsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.main.systems/persistence/entity/model/products";
    private final ServiceProduct serviceProduct;

    /*
        Пример запроса: POST http://localhost:8180/app/ws
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.main.systems/persistence/entity/model/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getAllProductsSoap(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        serviceProduct.getAllProductsSoap().forEach(response.getProducts()::add);
        return response;
    }
}
