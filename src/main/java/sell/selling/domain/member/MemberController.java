package sell.selling.domain.member;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

        return "create";
    }
    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {

            return "create";
        }
        if(memberRepository.findByLoginId(member.getLoginId()).isPresent())
        {
            bindingResult.rejectValue("loginId","exist");
            return "create";
        }

        memberRepository.save(member);

        return "redirect:/";
    }
}
