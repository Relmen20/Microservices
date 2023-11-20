package ru.oksk.study.sms.server.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.oksk.study.common.model.SmsDto;

@FeignClient(url = "${service.sms-blacklist.url}", name = "sms-blacklist")
public interface SmsServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "${service.sms-blacklist.endpoint}")
    void sendToBlacklist(@RequestBody SmsDto smsDto);
}
