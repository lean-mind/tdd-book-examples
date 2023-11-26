package es.leanmind.app;

public record Views() {
    static String csvForm() {
        return "<h2>File Form</h2>" +
                "<form enctype=\"multipart/form-data\" method=\"post\" action=\"/csvform\">" +
                "<input type=\"file\" name=\"file\" id=\"file\">" +
                "<button id=\"submit\" type=\"submit\">Submit</button>" +
                "</form>";
    }
}
