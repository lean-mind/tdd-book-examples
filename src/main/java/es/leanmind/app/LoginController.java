package es.leanmind.app;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    String showForm() {
        return "<h2>Login Form</h2>" +
                "<form>" +
                "<input type=\"text\" name=\"username\">" +
                "<input type=\"password\" name=\"password\">" +
                "<button id=\"submit\" type=\"submit\">Submit</button>" +
                "</form>";
    }



}