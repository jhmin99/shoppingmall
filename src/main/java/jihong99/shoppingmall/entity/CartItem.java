package jihong99.shoppingmall.entity;

import jakarta.persistence.*;
import jihong99.shoppingmall.entity.base.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Represents an item in the shopping cart.
 *
 * <p>The CartItem entity stores information about a specific item in a user's shopping cart,
 * including the item itself, the associated cart, the quantity of the item, and its price.</p>
 */
@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CartItem extends BaseEntity {

    /**
     * Unique identifier for the cart item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /**
     * The cart to which this cart item belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * The quantity of the item in the cart.
     */
    private Integer quantity;

    /**
     * The price of the item in the cart.
     */
    private Long price;

    /**
     * Updates the quantity of the item in the cart.
     *
     * @param quantity the new quantity of the item
     */
    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Updates the price of the item in the cart.
     *
     * @param price the new price of the item
     */
    public void updatePrice(Long price) {
        this.price = price;
    }

    /**
     * Calculates the total price of this cart item.
     *
     * @return the total price (quantity * price)
     */
    public Long getTotalPrice() {
        return this.quantity * this.price;
    }

    public static CartItem of(Cart cart, Item item, Integer quantity, long price){
        return CartItem.builder()
                .cart(cart)
                .item(item)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }
}
