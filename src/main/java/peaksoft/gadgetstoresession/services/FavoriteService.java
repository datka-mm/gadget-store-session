package peaksoft.gadgetstoresession.services;

import peaksoft.gadgetstoresession.dto.response.SimpleResponse;

public interface FavoriteService {

    SimpleResponse likeDislike(Long productId);
}
