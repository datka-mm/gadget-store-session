package peaksoft.gadgetstoresession.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.gadgetstoresession.dto.response.SimpleResponse;
import peaksoft.gadgetstoresession.exceptions.NotFoundException;
import peaksoft.gadgetstoresession.models.Favorite;
import peaksoft.gadgetstoresession.models.Product;
import peaksoft.gadgetstoresession.models.User;
import peaksoft.gadgetstoresession.repositories.FavoriteRepository;
import peaksoft.gadgetstoresession.repositories.ProductRepository;
import peaksoft.gadgetstoresession.repositories.UserRepository;
import peaksoft.gadgetstoresession.services.FavoriteService;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUserByEmail(authentication.getName()).orElseThrow(
                () -> new NotFoundException("User not found!")
        );
    }


    @Override
    public SimpleResponse likeDislike(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product with id: " + productId + " nor found")
        );

        User user = getUser();

        if (user.getFavorites() != null) {
            for (Favorite f : user.getFavorites()) {
                if (f.getProduct().equals(product)) {
                    user.getFavorites().remove(f);
                    product.getFavorites().remove(f);
                    favoriteRepository.deleteById(f.getId());
                    return new SimpleResponse("Dislike", HttpStatus.OK);
                }
            }
        }

        Favorite favorite = new Favorite();
        favorite.setProduct(product);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
        user.getFavorites().add(favorite);
        return new SimpleResponse("like", HttpStatus.OK);

    }
}
