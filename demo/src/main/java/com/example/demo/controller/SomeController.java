package com.example.demo.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.validator.SomeControllerValidator;
import com.example.demo.model.Something;
import com.example.demo.service.SomeService;

// REST API を作って、その引数にバリデーションを実装したいときは @Validated アノテーションをクラスに付ける
@Validated
@RestController
@RequestMapping("some")
public class SomeController {
    private final SomeService service;
    private final SomeControllerValidator validator;

    @Autowired
    public SomeController(SomeService service, SomeControllerValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("something/{id}")
    public Something creatSomething(@PathVariable @Positive int id) {
        // @PathVariable に対するバリデーションは、引数にアノテーションを付けて行う
        // 今回なら @Positive が該当
        return service.creaSomething(id);
    }

    @GetMapping("something")
    public Something getSomething(@RequestParam @Positive int id) {
        // @RequestParam に対するバリデーションも、引数にアノテーションを付けて行う
        // 今回なら @Positive が該当
        return service.creaSomething(id);
    }

    @PostMapping("weight")
    public double requestSomethingWeight(@RequestBody @Valid Something something) {
        // @RequestBody で受け取る Bean に対してバリデーションしたいときは、引数に @Valid を付ける
        // そして、 DTO のクラスに javax.validation.constraints のアノテーションを付与しておく
        return service.getSomethingWeight(something);
    }

    @GetMapping("fire")
    public String fireError() throws Exception {
        // 別の Controller から例外を発生させても RestControllerAdvice の例外処理が動くことを確認
        validator.validate();
        return "hoge";
    }
}
