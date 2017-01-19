package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * Created by ryankielty on 1/4/17.
 */
@Controller
public class MicroblogSpringController {

    public ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);

        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) throws Exception {

        if (userName == null) {
            throw new Exception("Enter Name");
        } else {
            session.setAttribute("userName", userName);
        }

        return "redirect:/";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String message(String addMessage) {

        Message mess = new Message ((messages.size() + 1), addMessage);
        messages.add(mess);

        return "redirect:/";
    }
    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id) {

        messages.remove(id - 1);

        return "redirect:/";
    }
}
