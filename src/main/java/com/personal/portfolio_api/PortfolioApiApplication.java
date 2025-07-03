package com.personal.portfolio_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Controller
public class  PortfolioApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortfolioApiApplication.class);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Portfolio API");
        String project = "Portfolio API (V1.0.0)";
        String dateTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' xxx yyyy"));
        model.addAttribute("project", project);
        model.addAttribute("dateTime", dateTime);
        return "index";
    }

}
