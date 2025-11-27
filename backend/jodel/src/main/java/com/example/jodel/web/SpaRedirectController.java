package com.example.jodel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SpaRedirectController {

    // Forward root and any path without a dot (no file extension) to index.html.
    // More specific mappings (z. B. /jodel/api/**) werden von Spring bevorzugt und bleiben funktionsfähig.
    @RequestMapping(value = { "/", "/**/{path:[^\\.]*}" }, method = RequestMethod.GET)
    public String forwardToIndex(HttpServletRequest request) {
        return "forward:/index.html";
    }
}
