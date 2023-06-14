package peaksoft.gadgetstoresession.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.gadgetstoresession.dto.request.ProductRequest;
import peaksoft.gadgetstoresession.dto.response.ProductResponse;
import peaksoft.gadgetstoresession.dto.response.SimpleResponse;
import peaksoft.gadgetstoresession.exceptions.NotFoundException;
import peaksoft.gadgetstoresession.models.Brand;
import peaksoft.gadgetstoresession.models.Favorite;
import peaksoft.gadgetstoresession.models.Product;
import peaksoft.gadgetstoresession.models.User;
import peaksoft.gadgetstoresession.repositories.BrandRepository;
import peaksoft.gadgetstoresession.repositories.ProductRepository;
import peaksoft.gadgetstoresession.repositories.UserRepository;
import peaksoft.gadgetstoresession.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUserByEmail(authentication.getName()).orElseThrow(
                () -> new NotFoundException("User not found!")
        );
    }

    @Override
    public SimpleResponse saveProduct(Long brandId, ProductRequest request) {

        Brand brand = brandRepository.findById(brandId).orElseThrow(
                () -> new NotFoundException("Brand with id: " + brandId + " not found!")
        );

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .characteristic(request.getCharacteristic())
                .images(request.getImages())
                .madeIn(request.getMadeIn())
                .category(request.getCategory())
                .isFavorite(false)
                .brand(brand)
                .build();
        productRepository.save(product);

        return new SimpleResponse("Product successfully saved", HttpStatus.OK);
    }

    @Override
    public ProductResponse findById(Long id) {
        User user = getUser();

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product with id: " + id + " not found!")
        );

        if (user.getFavorites() != null) {
            for (Favorite f : user.getFavorites()) {
                if (f.getProduct().equals(product)) {
                    return ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .images(product.getImages())
                            .characteristic(product.getCharacteristic())
                            .madeIn(product.getMadeIn())
                            .category(product.getCategory())
                            .isFavorite(true)
                            .build();
                }
            }
        }
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .images(product.getImages())
                .characteristic(product.getCharacteristic())
                .madeIn(product.getMadeIn())
                .category(product.getCategory())
                .isFavorite(false)
                .build();

    }

    @Override
    public List<ProductResponse> getAll() {
        User user = getUser();
//        List<ProductResponse> productResponses = productRepository.findAllProductResponses();
        List<Product> all = productRepository.findAll();
        List<ProductResponse> userProducts = new ArrayList<>();//productRepository.findAllProductResponses()
        List<Product> getAll = new ArrayList<>();

        if (user.getFavorites() != null) {
            for (Favorite f : user.getFavorites()) {
                for (Product p : all) {
                    if (f.getProduct().equals(p)) {
                        userProducts.add(new ProductResponse(
                                        p.getId(),
                                        p.getName(),
                                        p.getPrice(),
                                        p.getImages(),
                                        p.getCharacteristic(),
                                        true,
                                        p.getMadeIn(),
                                        p.getCategory()
                                )
                        );
                    } else {
                        userProducts.add(new ProductResponse(
                                        p.getId(),
                                        p.getName(),
                                        p.getPrice(),
                                        p.getImages(),
                                        p.getCharacteristic(),
                                        false,
                                        p.getMadeIn(),
                                        p.getCategory()
                                )
                        );
                    }
                }
            }
        }
        return userProducts;
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        return null;
    }
}
