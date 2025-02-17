package org.saveole.chat;

import org.saveole.reader.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ChatRestApi {
    @Autowired
    private ChatService chatService;
    @Autowired
    LoadService loadService;

    @GetMapping("/chat")
    public Map chat(@RequestParam(name = "query") String query) {
        return Map.of("answer", chatService.chat(query));
    }

    @PostMapping("/load")
    public String load(@RequestParam("file") MultipartFile file) {
        loadService.load(file);
        return "ok";
    }
}
