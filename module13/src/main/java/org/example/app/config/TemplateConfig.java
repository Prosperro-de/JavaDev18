package org.example.app.config;

import jakarta.servlet.ServletException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.IOException;

public class TemplateConfig {
    private final TemplateEngine templateEngine;

    public TemplateConfig() throws ServletException {
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
    }

    private ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public void process(String templateName, Context context, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process(templateName, context, response.getWriter());
    }
}
