package sell.selling.domain.member;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sell.selling.domain.member.Member;
import sell.selling.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String showCreateForm(@ModelAttribute("member") Member member) {
        log.info("go to create.html");
        return "create";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute("member") Member member)
    {
        memberRepository.save(member);
        log.info("회원가입 성공");
        return "redirect:/";
    }
}
