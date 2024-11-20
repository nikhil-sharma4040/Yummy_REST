package org.myprojects.yummy_rest.service;

import lombok.RequiredArgsConstructor;
import org.myprojects.yummy_rest.dto.ProductRequest;
import org.myprojects.yummy_rest.dto.ProductResponse;
import org.myprojects.yummy_rest.entity.Product;
import org.myprojects.yummy_rest.exception.ProductNotFoundException;
import org.myprojects.yummy_rest.mapper.ProductMapper;
import org.myprojects.yummy_rest.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String createProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);
        repo.save(product);
        return "Product created";
    }

    public List<ProductResponse> getAllProducts() {
        return mapper.toProductListResponse(repo.findAll());
    }

    public Product getProduct(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(
                format("No product with provided id: %d", id)
            ));
    }

    public ProductResponse retrieveProduct(Long id) {
        Product product = getProduct(id);
        return mapper.toProductResponse(product);
    }

    public String deleteProduct(Long id) {
        repo.deleteById(id);
        return "Product deleted";
    }

    public String updateProduct(ProductRequest request, Long id) {
        Product product = mapper.toProduct(request);
        product.setId(id);
        repo.save(product);
        return "Product updated";
    }

    public List<ProductResponse> getTopNProductsInPriceRangeXY(Long numProducts, double priceFrom, double priceTill) {
        return mapper.toProductListResponse(repo.findTopNProductsInPriceRangeXY(numProducts, priceFrom, priceTill));
    }
}
