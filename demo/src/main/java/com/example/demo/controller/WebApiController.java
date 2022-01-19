package com.example.demo.controller;

import com.example.demo.model.ErrorResponse;
import com.example.demo.model.Human;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
// リクエストヘッダなどを詳細に設定したい場合は org.springframework.http.RequestEntity を使うといいらしい。
//import org.springframework.http.RequestEntity;
// レスポンスヘッダなどを詳細に設定したい場合は org.springframework.http.ResponseEntity を使うといいらしい。
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.text.StringEscapeUtils;

/**
 * API 用コントローラ
 */
@RestController
@RequestMapping("api")
public class WebApiController {
    private final Logger logger = LogManager.getLogger();

    /**
     * シンプルな end pt.
     * 
     * @return 出力文字列
     */
    @GetMapping("hello")
    private String hello() {
        return "SpringBoot!";
    }

    /**
     * パスパラメータの end pt.
     * 
     * @param param パラメータ。 @PathVariable でパスパラメータと仮引数を紐づけられる。
     *              パスパラメータと仮引数の名前が違う時は @PathVariable("arg") String hoge のように書く。
     * @return
     */
    @GetMapping("test/{param}")
    private String testPathVariable(@PathVariable String param) {
        return "受け取ったパラメータ (String): " + param;
    }

    /**
     * パスパラメータの型は String 以外も指定できる。
     * 
     * @param num Integer 型のパスパラメータ。
     * @return
     */
    @GetMapping("num/{num}")
    private String testPathVariable2(@PathVariable Integer num) {
        return "受け取ったパラメータ (Integer): " + num;
    }

    /**
     * クエリパラメータの end pt.
     * 
     * @param param クエリパラメータ。 @RequestParam でクエリパラメータと仮引数を紐づけられる。
     *              クエリパラメータと仮引数の名前が違う時は @RequestParam("arg") String hoge のように書く。
     * @return
     */
    @GetMapping("test")
    private String testRequestParam(@RequestParam String param) {
        return "受け取ったパラメータ (String): " + param;
    }

    /**
     * クエリパラメータの型は String 以外も指定できる。
     * 
     * @param num Double 型のクエリパラメータ
     * @return
     */
    @GetMapping("num")
    private String testRequestParam2(@RequestParam Double num) {
        return "受け取ったパラメータ (Double): " + num;
    }

    /**
     * POST 送信の end pt. リクエストボディを受け取る。
     * 
     * @param param リクエストボディ。 @RequestBody をつけることによってリクエストボディのデータを引数で受け取れる。
     * @return
     */
    @PostMapping("test")
    private String testRequestBody(@RequestBody String param) {
        return "受け取ったボディ (String): " + param;
    }

    /**
     * モデルの JSON データを返す end pt.
     * 
     * @return モデルの JSON データ。
     */
    @GetMapping("human")
    private Human getHuman() {
        return new Human("hoge", Integer.valueOf(20));
    }

    /**
     * Map オブジェクトでも JSON 形式のデータを返せる。
     * 設計的によくない気がするので、なるべくやめましょう…。
     * 
     * @return Map オブジェクト
     */
    @GetMapping("mapping")
    private Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("hoge", "neko");
        map.put("fuga", 99.23);

        return map;
    }

    /**
     * バイナリファイルを返す end pt.
     * ここでは画像ファイルを返している。
     * 
     * @return
     */
    @GetMapping(value = "img", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    private Resource img() {
        return new FileSystemResource(new File("demo\\src\\main\\resources\\img\\アイコン.png"));
    }

    /**
     * このコントローラ内の end pt. 全体の集約例外ハンドラ。
     * C# の AppDomain.UnhandledException みたいなやつ。最高。
     * 
     * @param ex
     * @return
     */
    // @ExceptionHandler
    // private ResponseEntity<ErrorResponse> onError(Exception ex) {
    // logger.error(ex.getMessage() + "on console", ex);

    // HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    // ErrorResponse response = new ErrorResponse("例外発生", ex.getMessage(),
    // status.value());

    // return new ResponseEntity<ErrorResponse>(response, status);
    // }

    /**
     * 例外発生用のテスト用 end pt.
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("test/ex")
    private String testException() throws Exception {
        throw new RuntimeException("this is error.");
    }

    /**
     * 外部 API を叩いてみるお試し end pt.
     * 
     * @return 郵便番号 api の response json
     */
    @GetMapping(value = "zip", produces = MediaType.APPLICATION_JSON_VALUE)
    private String zip() {
        RestTemplate template = new RestTemplate();

        // 東京らしい
        final String zipCode = "2140001";
        final String endpoint = "https://zipcloud.ibsnet.co.jp/api/search";
        final String url = endpoint + "?zipcode=" + zipCode;

        ResponseEntity<String> entity = template.getForEntity(url, String.class);
        String json = entity.getBody();

        return decodeUnicodeEscape(json);
    }

    /**
     * decode unicode escape.
     * 
     * @param val
     * @return
     */
    private static String decodeUnicodeEscape(String val) {
        // Unicode escape を decode するなら org.apache.commons.text.StringEscapeUtils
        return StringEscapeUtils.unescapeJava(val);
    }
}
