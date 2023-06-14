package peaksoft.gadgetstoresession.apis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.gadgetstoresession.dto.request.ProductRequest;
import peaksoft.gadgetstoresession.dto.response.ProductResponse;
import peaksoft.gadgetstoresession.dto.response.SimpleResponse;
import peaksoft.gadgetstoresession.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductApi {

    private final ProductService productService;

    @PostMapping("/save-product/{id}")
    public SimpleResponse save(@PathVariable Long id,
                               @RequestBody ProductRequest request) {

        return productService.saveProduct(id, request);
    }

    @GetMapping("/find/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.getAll();
    }
}
