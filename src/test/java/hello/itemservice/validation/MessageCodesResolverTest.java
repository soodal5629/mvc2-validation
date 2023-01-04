package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {
    
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    
    @Test
    // MessageCodesResolver - Object error 테스트
    void messageCodesResolverObject() {
        // 메시지 코드 반환
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    // MessageCodesResolver - Field error 테스트
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        // bindingResult.rejectValue("itemName", "required")
        // => rejectValue 가 new FieldError를 만들 때 저 메시지 코드를 넘김
        assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName"
                , "required.java.lang.String", "required");
    }
}
