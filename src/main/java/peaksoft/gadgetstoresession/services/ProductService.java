package peaksoft.gadgetstoresession.services;

import peaksoft.gadgetstoresession.dto.request.ProductRequest;
import peaksoft.gadgetstoresession.dto.response.ProductResponse;
import peaksoft.gadgetstoresession.dto.response.SimpleResponse;

import java.util.List;

public interface ProductService {

    SimpleResponse saveProduct(Long brandId, ProductRequest request);

    ProductResponse findById(Long id);

    List<ProductResponse> getAll();

    SimpleResponse deleteById(Long id);
}
