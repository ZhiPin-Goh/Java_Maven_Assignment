package ModelsDTO;

import java.math.BigDecimal;
import java.util.List;

public class DashboardDTO {
    private BigDecimal totalRevenue;
    private int todayOrderCount;
    private List<OrderPreparingDTO> pendingCount;
    public DashboardDTO(BigDecimal totalRevenue, int todayOrderCount, List<OrderPreparingDTO> pendingCount){
        this.totalRevenue = totalRevenue;
        this.todayOrderCount = todayOrderCount;
        this.pendingCount = pendingCount;
    }
    public BigDecimal getTotalRevenue(){return totalRevenue;}
    public int getTodayOrderCount(){return todayOrderCount;}
    public List<OrderPreparingDTO> getPendingCount(){return pendingCount;}
}
