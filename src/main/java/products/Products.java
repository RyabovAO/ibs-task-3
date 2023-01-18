package products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Products {
    protected int price;
    protected int priceWithGuarantee;
}
