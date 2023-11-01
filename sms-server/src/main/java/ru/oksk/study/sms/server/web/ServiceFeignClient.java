package ru.oksk.study.sms.server.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.oksk.study.common.dto.MutableSessionMessageDto;

@FeignClient(url = "${service.sms-blacklist.url}", name = "sms-blacklist")
public interface ServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "${service.sms-blacklist.endpoint}")
    ResponseEntity<String> endPoint(@RequestBody MutableSessionMessageDto mutableSessionMessageDto);
}
