package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
/*
// 아래 주석부분은 제약조건도 많고 그래서 권장하지 않음 => 차라리 자바코드로 작성성라
@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000"
                , message = "총합이 10000원 넘게 입력해주세요.")
*/
public class Item {

    @NotNull(groups = UpdateCheck.class) // 수정 요구사항 추가
    private Long id;
    @NotBlank(message = "공백X", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class}) // 수정시에는 해당 제약조건 X
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
