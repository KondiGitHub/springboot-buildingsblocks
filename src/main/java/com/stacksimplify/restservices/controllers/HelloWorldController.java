package com.stacksimplify.restservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/hello-int")
    public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Language",required = false) String locale) {
        return messageSource.getMessage("label.hello", null, new Locale(locale));
    }

}
