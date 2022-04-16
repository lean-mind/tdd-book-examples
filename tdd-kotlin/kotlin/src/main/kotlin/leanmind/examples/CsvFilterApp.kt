package leanmind.examples

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class CsvFilterApp {

    @GetMapping("/login")
    fun showLoginForm(): String {
        return "<h2>Login Form</h2>" +
            "<form>" +
            "<input type=\"text\" name=\"username\">" +
            "<input type=\"password\" name=\"password\">" +
            "<button id=\"submit\" type=\"submit\">Submit</button>" +
            "</form>"
    }

    @GetMapping("/csvform")
    fun showFileForm(): String {
        return "<h2>File Form</h2>" +
            "<form enctype=\"multipart/form-data\" method=\"post\" action=\"/csvform\">" +
            "<input type=\"file\" name=\"file\" id=\"file\">" +
            "<button id=\"submit\" type=\"submit\">Submit</button>" +
            "</form>"
    }


    @PostMapping("/csvform")
    @ResponseBody
    fun showResult(@RequestPart("file") file: MultipartFile): String {
        val lines: MutableList<String> = ArrayList()
        file.inputStream.bufferedReader().forEachLine {
            lines.add(it)
        }
        val csvFilter = CsvFilter()
        val filteredResult = csvFilter.apply(lines)
        return filteredResult.joinToString(separator = ",")
    }
}
