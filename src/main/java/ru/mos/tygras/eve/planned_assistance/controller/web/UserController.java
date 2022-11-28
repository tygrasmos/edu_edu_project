package ru.mos.tygras.eve.planned_assistance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mos.tygras.eve.planned_assistance.dto.UserDto;
import ru.mos.tygras.eve.planned_assistance.dto.mapper.UserDtoMapper;
import ru.mos.tygras.eve.planned_assistance.service.repository.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService,
                          UserDtoMapper userDtoMapper){
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users")
    public String pageUsers(){
        return "userPage";
    }

    @GetMapping("/user/add")
    public String addPageComment(Model model){
        model.addAttribute("userDto", new UserDto());
        return "addUser";
    }

    @Validated
    @PostMapping("/user/add")
    public String addComment(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userService.addUser(userDtoMapper.dtoToEntity(userDto));
        return "redirect:/main";
    }
}
