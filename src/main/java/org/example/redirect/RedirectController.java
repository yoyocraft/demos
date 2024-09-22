package org.example.redirect;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * @author yoyocraft
 * @date 2024/09/22
 */
@RestController
@RequestMapping("/redirect")
public class RedirectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);
    private static final Map<String, String> REDIRECT_MAPPING = Maps.newHashMap();

    static {
        REDIRECT_MAPPING.put("default", "https://www.baidu.com");
        REDIRECT_MAPPING.put("dp", "https://item.jd.com/14187350.html");
        REDIRECT_MAPPING.put("blog", "https://codejuzi.icu");
    }

    @GetMapping("/{shortLink}")
    public RedirectView redirectView(@PathVariable("shortLink") String shortLink) {
        String redirectUrl = REDIRECT_MAPPING.computeIfAbsent(shortLink, key -> REDIRECT_MAPPING.get("default"));
        LOGGER.info("{} redirect to {}", shortLink, redirectUrl);
        return new RedirectView(redirectUrl);
    }
}
