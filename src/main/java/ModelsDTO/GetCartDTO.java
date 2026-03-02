package ModelsDTO;

import java.math.BigDecimal;
import java.util.List;

public class GetCartDTO {
    private BigDecimal TotalAmount;
    private List<CartDTO> items;
    public GetCartDTO(BigDecimal TotalAmount, List<CartDTO> items){
        this.TotalAmount = TotalAmount;
        this.items = items;
    }
    public BigDecimal getTotalAmount(){return TotalAmount;}
    public List<CartDTO> getItems(){return items;}
}
