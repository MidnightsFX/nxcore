package com.midnightsfx.nxcore.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by MidnightsFX on 7/27/17.
 */
@Controller
@RequestMapping("/text-templates")
public class thymeleafTextTemplates {

    private TemplateEngine textTemplateEngine;

    public thymeleafTextTemplates(TemplateEngine textTemplateEngine) {
        this.textTemplateEngine = textTemplateEngine;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute(new Form());
        return "th-form";
    }

    @PostMapping("/form")
    public String postForm(@ModelAttribute Form form, Model model) {

        Context context = new Context();
        context.setVariable("name", form.getName());
        context.setVariable("url", form.getUrl());
        context.setVariable("tags", form.getTags().split(" "));

        String text = textTemplateEngine.process("text-template", context);

        model.addAttribute("text", text);

        return "th-form";
    }

    @Configuration
    public static class ThymeleafConfig {

        @Bean(name = "textTemplateEngine")
        public TemplateEngine textTemplateEngine() {
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.addTemplateResolver(textTemplateResolver());
            return templateEngine;
        }

        private ITemplateResolver textTemplateResolver() {
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("/templates/text/");
            templateResolver.setSuffix(".txt");
            templateResolver.setTemplateMode("TEXT");
            templateResolver.setCharacterEncoding("UTF8");
            //templateResolver.setCheckExistence(true);
            templateResolver.setCacheable(false);
            return templateResolver;
        }
    }

    public static class Form {
        private String name = "spring.io";
        private String url = "http://spring.io";
        private String tags = "#spring #framework #java";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
    }

}
