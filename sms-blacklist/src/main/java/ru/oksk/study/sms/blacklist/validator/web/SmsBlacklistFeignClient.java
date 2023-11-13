package ru.oksk.study.sms.blacklist.validator.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.oksk.study.common.dto.EmulatorOutputDto;
import ru.oksk.study.common.dto.StatusDto;
import ru.oksk.study.common.model.Status;

import java.net.URI;

@FeignClient(url = "${service.emulate-services.url}", name = "emulate-services")
public interface SmsBlacklistFeignClient {

        @RequestMapping(method = RequestMethod.POST, value = "${service.emulate-services.endpoint}")
        public ResponseEntity<StatusDto> startEmulatorPoint(URI baseUrl, @RequestBody EmulatorOutputDto emulatorOutputDto);

}
