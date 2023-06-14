package peaksoft.gadgetstoresession.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import peaksoft.gadgetstoresession.enums.Category;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductRequest {

    private String name;
    private int price;
    private List<String> images;
    private String characteristic;
    private String madeIn;
    private Category category;
}
