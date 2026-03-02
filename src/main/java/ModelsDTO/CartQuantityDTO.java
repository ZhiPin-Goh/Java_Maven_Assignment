package ModelsDTO;

public class CartQuantityDTO {
    private int UserID;
    private int CartID;
    private int ReqQuantity;
    public CartQuantityDTO(int UserID, int CartID, int ReqQuantity){
        this.UserID = UserID;
        this.CartID = CartID;
        this.ReqQuantity = ReqQuantity;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getReqQuantity() {
        return ReqQuantity;
    }

    public void setReqQuantity(int reqQuantity) {
        ReqQuantity = reqQuantity;
    }
}
