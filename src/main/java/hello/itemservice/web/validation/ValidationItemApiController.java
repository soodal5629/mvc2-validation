package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /*
     * @RequestBody는 json 통으로 들어온 객체를 ItemSaveForm으로 바꿔줘야 하는데
     * 데이터 타입이 안맞는 등의 이유로 에러가 나면
     * json을 객체로 생성하는 것 자체가 실패하기 때문에 컨트롤러 자체도 호출이 안됨
     * => 따라서 검증로직도 실행 안됨
  됨  * */
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors(); // 필드에러, 오브젝트 에러 다 반환해줌
        }

        log.info("성공 로직 실행");
        return form;
    }
}
