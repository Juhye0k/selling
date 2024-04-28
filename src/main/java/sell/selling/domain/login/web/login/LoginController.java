package sell.selling.domain.login.web.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sell.selling.domain.login.LoginService;
import sell.selling.domain.member.Member;
import sell.selling.domain.member.MemberRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String viewLogin(@ModelAttribute LoginForm form)
    {
        return "index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request
                        , Model model)
    {
        Member loginMember=loginService.login(form.getLoginId(),form.getPassword());
        log.info("login={}",loginMember);
        if(loginMember==null)
        {
            log.info("로그인 실패");
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/";

        }
        HttpSession session=request.getSession();
        session.setAttribute("loginMember",loginMember);


;
        log.info("로그인 성공");
        //로그인 성공

        return "redirect:/items";
    }

    @GetMapping("/items/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session!=null){
            session.invalidate();
        }

        List<Member> members=memberRepository.findAll();

        return "redirect:/";

    }


}
