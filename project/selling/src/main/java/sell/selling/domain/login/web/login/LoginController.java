package sell.selling.domain.login.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sell.selling.domain.login.LoginService;
import sell.selling.domain.member.Member;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @GetMapping("/")
    public String viewLogin(@ModelAttribute LoginForm form)
    {
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult)
    {
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        log.info("login={}",loginMember);
        if(loginMember==null)
        {
            log.info("로그인 실패");
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/";

        }
        log.info("로그인 성공");
        //로그인 성공
        return "items";
    }
}
