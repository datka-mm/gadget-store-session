package peaksoft.gadgetstoresession.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.gadgetstoresession.enums.Category;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;
}
