package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    // 이걸 분석하는것이 좋은것일까?
    //이게 핵심일까 뭐가 뭔지 모르겠다. 일단 편하긴 한데 무거워.. 그래서 안됄것 같아. 그냥
    // 있는거 사용하는것이 나을것 같고.. 깃허브를 활용하면 더 좋을것 같다.₩
    //기계식 키보드가 편하긴 한데 이것도 적응하니깐 좀 괜찮아. 공간만 필요하면 좋을것 같긴 하지만. 그래도 나름
    //나쁘지 않은것 같아.

    /*stock증가*/


    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*//stock 감소*/
    public void removeStock(int quantity) {
        if(stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;


    }

    //이제 어떤 장점이 있는지 모르겠네요.. ㅋㅋ 짐이 느는것 같은 느낌...
    //이게 이제 편한것 같은데.. 그냥 휴대용 모니터가 가벼우면 됄것 같은데..그럼 끝.


}
