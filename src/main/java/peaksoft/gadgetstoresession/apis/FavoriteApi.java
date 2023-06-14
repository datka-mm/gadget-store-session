package peaksoft.gadgetstoresession.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.gadgetstoresession.dto.response.SimpleResponse;
import peaksoft.gadgetstoresession.services.FavoriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteApi {

    private final FavoriteService favoriteService;

    @PostMapping("/like-dislike")
    public SimpleResponse likeDislike(@RequestParam Long id) {
        return favoriteService.likeDislike(id);
    }
}
