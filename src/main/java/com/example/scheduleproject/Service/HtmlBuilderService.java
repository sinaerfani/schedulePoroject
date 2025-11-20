package com.example.scheduleproject.Service;

import com.example.scheduleproject.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmlBuilderService {

    @Value("${app.base-url:http://localhost:8083}")
    private String baseUrl;

    public String buildProductHtml(List<Product> products) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html lang='fa' dir='rtl'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>لیست محصولات</title>");
        html.append("<style>");
        html.append("body { font-family: 'Tahoma', Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 20px; }");
        html.append(".product { background: white; border-radius: 10px; padding: 20px; margin: 15px 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }");
        html.append(".product h3 { color: #333; margin-bottom: 15px; }");
        html.append(".product img { max-width: 200px; height: auto; border-radius: 5px; border: 1px solid #ddd; }");
        html.append(".no-image { color: #d32f2f; background: #ffebee; padding: 10px; border-radius: 5px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<h1 style='text-align: center; color: #1976d2;'>لیست محصولات</h1>");

        for (Product p : products) {
            html.append("<div class='product'>");
            html.append("<h3>").append(escapeHtml(p.getName())).append("</h3>");

            if (p.getImage() != null && p.getId() != null) {
                // استفاده از URL واقعی به جای base64
                String imageUrl = baseUrl + "/product/" + p.getId() + "/image";
                html.append("<img src='").append(imageUrl).append("'")
                        .append(" alt='").append(escapeHtml(p.getName())).append("'")
                        .append(" title='").append(escapeHtml(p.getName())).append("'/>");
            } else {
                html.append("<div class='no-image'>تصویر موجود نیست</div>");
            }

            html.append("</div>");
        }

        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }

    // متد برای escape کردن متن برای HTML
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}